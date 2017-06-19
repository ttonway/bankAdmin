package com.psbc.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.psbc.pojo.AdminUser;
import com.psbc.pojo.PartnerUser;
import com.psbc.pojo.PosterImage;
import com.psbc.service.AdminUserService;
import com.psbc.service.PartnerUserService;
import com.psbc.service.PosterService;
import com.psbc.util.Utils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/partner")
public class PartnerController {
    private static Logger logger = Logger.getLogger(PartnerController.class);

    private static final int RED = 0xffff0000;
    private static final int QRCODE_WIDTH = 200;
    private static final int QRCODE_HEIGHT = 200;

    @Value("${fileserver.path}")
    private String fileRootPath;
    @Value("${service.loan0.url}")
    private String loan0Url;
    @Value("${service.loan1.url}")
    private String loan1Url;


    @Autowired
    private PosterService posterService;

    @Autowired
    private PartnerUserService partnerUserService;

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping("/index")
    public String index() {
        return "/partner/index";
    }

    @RequestMapping("/select")
    public String select() {
        return "/partner/select";
    }

    @RequestMapping("/form/whitecollar")
    public ModelAndView formWhiteCollar(HttpSession session) {
        PartnerUser partner = new PartnerUser();
        partner.setPartnerType("whitecollar");
        partner.setStatus("0");
        session.setAttribute("partner", partner);
        return new ModelAndView("partner/form", "type", "whitecollar");
    }

    @RequestMapping("/form/shop")
    public ModelAndView formShop(HttpSession session) {
        PartnerUser partner = new PartnerUser();
        partner.setPartnerType("shop");
        partner.setStatus("0");
        session.setAttribute("partner", partner);
        return new ModelAndView("partner/form", "type", "shop");
    }

    @RequestMapping("/area")
    public String area(HttpSession session, String oldCustomer, String workUnitType, String workUnitName, String userName, String phoneNumber, String shopName, String shopAddress) {
        PartnerUser partner = (PartnerUser) session.getAttribute("partner");
        if (partner == null) {
            return "redirect:index";
        }

        partner.setOldCustomer(oldCustomer);
        if (partner.getPartnerType().equals("whitecollar")) {
            partner.setWorkUnitType(workUnitType);
            partner.setWorkUnitName(workUnitName);
        }
        partner.setUserName(userName);
        partner.setPhoneNumber(phoneNumber);
        if (partner.getPartnerType().equals("shop")) {
            partner.setShopName(shopName);
            partner.setShopAddress(shopAddress);
        }
        return "/partner/area";
    }

    @RequestMapping("/poster")
    public String poster(HttpSession session, String area) {
        PartnerUser partner = (PartnerUser) session.getAttribute("partner");
        if (partner == null) {
            return "redirect:index";
        }

        partner.setBank(area + "支行");

        return "/partner/poster";
    }

    @Transactional
    @RequestMapping("/result")
    public String result(HttpSession session, String posterType, String posterFileName) {
        PartnerUser partner = (PartnerUser) session.getAttribute("partner");
        if (partner == null) {
            return "redirect:index";
        }

        partner.setPosterType(posterType);
        partner.setPosterFileName(posterFileName);
        String time = Utils.currentTimeStr();
        partner.setCreateTime(time);
        partner.setUpdateTime(time);

        if (partner.getPartnerId() == null) {
            partner.setNeedMaterial("否");

            PartnerUser exist = partnerUserService.selectByPhoneNumber(partner.getPhoneNumber());
            if (exist != null) {
                partner.setPartnerId(exist.getPartnerId());
                partnerUserService.updateByPrimaryKey(partner);
            } else {
                partnerUserService.insert(partner);

                AdminUser adminUser = new AdminUser();
                adminUser.setUserCode("partner-" + partner.getPartnerId());
                adminUser.setUserName(partner.getUserName());
                adminUser.setRole("推广员");
                adminUserService.insert(adminUser);
            }
        } else {
            partnerUserService.updateByPrimaryKey(partner);
        }

        return "/partner/result";
    }

    @RequestMapping("/material")
    public String material(HttpSession session) {
        PartnerUser partner = (PartnerUser) session.getAttribute("partner");
        if (partner == null) {
            return "redirect:index";
        }

        return "/partner/material";
    }

    @RequestMapping("/needMaterial")
    @ResponseBody
    public Map<String, Object> needMaterial(HttpSession session, String materials, String receiver, String receiverPhoneNumber, String receiverArea, String receiverAddress) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 1);

        PartnerUser partner = (PartnerUser) session.getAttribute("partner");
        if (partner == null) {
            return map;
        }

        partner.setNeedMaterial("是");
        partner.setMaterials(materials);
        partner.setReceiver(receiver);
        partner.setReceiverPhoneNumber(receiverPhoneNumber);
        partner.setReceiverArea(receiverArea);
        partner.setReceiverAddress(receiverAddress);

        try {
            partnerUserService.updateByPrimaryKey(partner);
            map.put("code", 0);
        } catch (Exception e) {
            logger.error("update partner fail.", e);
        }
        return map;
    }

    @RequestMapping("/posterlist")
    @ResponseBody
    public Map<String, Object> posterlist(String loanType) {
        Map<String, Object> result = new HashMap<>();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("loanType", loanType);
            List<Map<String, Object>> cntlist = posterService.selectByTypeCnt(map);
            result.put("cntlist", cntlist);
            List<PosterImage> list = posterService.selectByList(map);
            result.put("data", list);
            result.put("code", 0);
            return result;
        } catch (Exception e) {
            logger.error("query poster list fail.", e);
            result.put("code", 1);
            return result;
        }
    }

    @RequestMapping(value = "/image/{fileName:.+}")
    public void file(HttpServletResponse response, @PathVariable String fileName) throws IOException {


        File file = new File(fileRootPath, fileName);
        logger.debug("read file " + file);
        if (!file.exists()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        FileInputStream fis = null;
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        try {
            OutputStream out = response.getOutputStream();
            fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            out.flush();
        } catch (FileNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            logger.error("read " + file + " fail.", e);
        } finally {
            IOUtils.closeQuietly(fis);
        }
    }

    String getLoanUrl(String userCode, String loanType) {
        String url;
        if (loanType.equals("0")) {
            url = loan0Url;
        } else if (loanType.equals("1")) {
            url = loan1Url;
        } else {
            return null;
        }
        return url + "?fucode=" + userCode;
    }

    @RequestMapping(value = "/qrcode/{loanType}/{userCode}")
    public void qrcode(HttpServletResponse response, @PathVariable String userCode, @PathVariable String loanType, Integer w, Integer h) {
        int width = w == null ? QRCODE_WIDTH : w.intValue();
        int height = h == null ? QRCODE_HEIGHT : h.intValue();

        String url = getLoanUrl(userCode, loanType);
        if (url == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType(MediaType.IMAGE_PNG.toString());
        try {
            OutputStream stream = response.getOutputStream();
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix m = writer.encode(url, BarcodeFormat.QR_CODE, width, height);
            MatrixToImageWriter.writeToStream(m, "png", stream);
            stream.flush();
        } catch (Exception e) {
            logger.error("qrcode fail.", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/generate/{userCode}/{loanType}/{fileName:.+}")
    public void generate(HttpServletResponse response, @PathVariable String userCode, @PathVariable String loanType, @PathVariable String fileName) {

        String url = getLoanUrl(userCode, loanType);
        if (url == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType(MediaType.IMAGE_PNG.toString());
        File file = new File(fileRootPath, fileName);
        logger.debug("read file " + file);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Rectangle rect = findSquare(bufferedImage);

            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix m = writer.encode(url, BarcodeFormat.QR_CODE, rect.width, rect.height);
            BufferedImage qrcode = MatrixToImageWriter.toBufferedImage(m);

            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.drawImage(qrcode, rect.x, rect.y, rect.width, rect.height, null);
            g2d.dispose();

            OutputStream stream = response.getOutputStream();
            ImageIO.write(bufferedImage, "png", stream);
            stream.flush();
        } catch (Exception e) {
            logger.error("generate poster fail.", e);
        }
    }

    Rectangle findSquare(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int minx = bufferedImage.getMinX();
        int miny = bufferedImage.getMinY();
        for (int i = minx; i < width; i++) {
            for (int j = miny; j < height; j++) {
                int pixel = bufferedImage.getRGB(i, j);
                if (pixel == RED) {
                    Point rightBottom = findSquare(bufferedImage, i, j);
                    if (rightBottom != null) {
                        Rectangle rect = new Rectangle(i, j, rightBottom.x - i, rightBottom.y - j);
                        logger.debug("found square " + rect);
                        return rect;
                    }
                }
            }
        }

        int w = Math.min(QRCODE_WIDTH, width - minx);
        int h = Math.min(QRCODE_HEIGHT, height - miny);
        int left = (minx + width) / 2 - w / 2;
        int top = (miny + height) / 2 - h / 2;
        return new Rectangle(left, top, w, h);
    }

    Point findSquare(BufferedImage bufferedImage, int left, int top) {
        int right = left;
        int bottom = top;
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        while (right < width && bufferedImage.getRGB(right, top) == RED) right++;
        while (bottom < height && bufferedImage.getRGB(left, bottom) == RED) bottom++;
        if (right - left > 50 && bottom - top > 50) {
            for (int i = left; i < right; i++) {
                for (int j = top; j < bottom; j++) {
                    if (bufferedImage.getRGB(i, j) != RED) {
                        return null;
                    }
                }
            }

            return new Point(right, bottom);
        } else {

            return null;
        }
    }
}