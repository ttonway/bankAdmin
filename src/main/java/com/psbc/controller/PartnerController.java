package com.psbc.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.psbc.pojo.PosterImage;
import com.psbc.service.PosterService;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping("/index")
    public String userlist() {
        return "/partner/index";
    }

    @RequestMapping("/select")
    public String select() {
        return "/partner/select";
    }

    @RequestMapping("/form/{type}")
    public ModelAndView form(@PathVariable String type) {
        return new ModelAndView("partner/form", "type", type);
    }

    @RequestMapping("/area")
    public String area() {
        return "/partner/area";
    }

    @RequestMapping("/poster")
    public ModelAndView poster() {
        List<PosterImage> list = posterService.selectAll();
        return new ModelAndView("partner/poster", "posters", list);
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<PosterImage> list = posterService.selectAll();
            map.put("code", 0);
            map.put("data", list);
            return map;
        } catch (Exception e) {
            logger.error("query poster list fail.", e);
            map.put("code", 1);
            return map;
        }
    }

    @RequestMapping(value = "/image/{posterId}")
    public void file(HttpServletResponse response, @PathVariable Long posterId) throws IOException {
        PosterImage poster = posterService.selectByPrimaryKey(posterId);
        if (poster == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        File file = new File(fileRootPath, poster.getFileName());
        logger.debug("read file " + file);

        FileInputStream fis = null;
        response.setContentType(poster.getContentType());
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

    @RequestMapping(value = "/generate/{userCode}/{loanType}/{fileName}")
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