package com.psbc.controller;

import com.psbc.pojo.AdminUserDetails;
import com.psbc.pojo.PartnerUser;
import com.psbc.service.LoanUserService;
import com.psbc.service.PartnerUserService;
import com.psbc.util.ExportExcelUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/partnermgr")
public class PartnerMgrController {
    private static Logger logger = Logger.getLogger(PartnerMgrController.class);

    @Autowired
    private PartnerUserService partnerUserService;

    @Autowired
    private LoanUserService loanUserService;

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(Integer draw, Integer start, Integer length) {
        AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Map<String, Object> pmap = new HashMap<String, Object>();
            if ("支行营销岗".equals(userDetails.getUserRole())) {
                pmap.put("bank", userDetails.getUserBank());
            } else {
                pmap.put("bank", null);
            }
            pmap.put("start", start);
            pmap.put("length", length);
            int cnt = partnerUserService.selectByCnt(pmap);
            List<PartnerUser> list = partnerUserService.selectByList(pmap);
            for (PartnerUser partner : list) {
                String str = partner.getBank();
                if (str != null) {
                    str = str.replace("支行", "");
                    partner.setBank(str);
                }
            }

            map.put("draw", draw);
            map.put("recordsTotal", cnt);
            map.put("recordsFiltered", cnt);
            map.put("data", list);
            map.put("code", 0);
            return map;
        } catch (Exception e) {
            logger.error("list all partners fail.", e);
            map.put("code", 1);
            return map;
        }
    }

    void fillPartnerCount(PartnerUser partner) {
        Map<String, Object> pmap = new HashMap<String, Object>();
        pmap.put("fromUserCode", "partner-" + partner.getPartnerId());
        List<Map<String, Object>> cntList = loanUserService.selectByStatusCnt(pmap);
        for (Map<String, Object> cntMap : cntList) {
            Long cnt = (Long) cntMap.get("cnt");
            int count = cnt == null ? 0 : cnt.intValue();
            if (cntMap.get("status").equals("2")) {//已审核
                partner.setPassLoanCount(count);
            }
            partner.setTotalLoanCount(partner.getTotalLoanCount() + count);
        }
    }

    @RequestMapping("/get")
    @ResponseBody
    public Map<String, Object> get(Long partnerId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            PartnerUser partner = partnerUserService.selectByPrimaryKey(partnerId);
            fillPartnerCount(partner);

            String str = partner.getBank();
            if (str != null) {
                str = str.replace("支行", "");
                partner.setBank(str);
            }

            map.put("code", 0);
            map.put("result", partner);
            return map;
        } catch (Exception e) {
            logger.error("query partner fail.", e);
            map.put("code", 1);
            return map;
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(Long[] partnerIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            partnerUserService.deleteByPrimaryKey(partnerIds);
            map.put("code", 0);
            return map;
        } catch (Exception e) {
            logger.error("delete partner fail.", e);
            map.put("code", 1);
            return map;
        }
    }

    @RequestMapping("/export")
    public void export(HttpServletResponse response) {
        AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Map<String, Object> pmap = new HashMap<String, Object>();
        if ("支行营销岗".equals(userDetails.getUserRole())) {
            pmap.put("bank", userDetails.getUserBank());
        } else {
            pmap.put("bank", null);
        }

        // 定义response类型
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=\"export.xls\"");
        // 定义输出流
        OutputStream excelStream;
        try {
            logger.info("export partner list excel");
            ExportExcelUtils<PartnerUser> outExcel = new ExportExcelUtils<PartnerUser>();
            // 定义sheet页标题
            String title = "下载";
            // 定义表头
            excelStream = new BufferedOutputStream(response.getOutputStream());
            List<PartnerUser> dataset = partnerUserService.selectByList(new HashMap<>());
            for (PartnerUser partner : dataset) {
                fillPartnerCount(partner);

                if ("whitecollar".equals(partner.getPartnerType())) {
                    partner.setPartnerType("白领");
                } else if ("shop".equals(partner.getPartnerType())) {
                    partner.setPartnerType("商家");
                }
                String str = partner.getBank();
                if (str != null) {
                    str = str.replace("支行", "");
                    partner.setBank(str);
                }
            }

            String[] headers = {"类型", "是否为我行老客户", "姓名", "联系方式", "单位名称", "单位性质", "店面名称", "店面位置",
                    "合作区域", "是否需要宣传材料", "实体宣传材料", "收货人", "手机号码", "所在区或县", "详细地址",
                    "贷款通过总数", "贷款申请总数", "申请时间"};
            String[] fields = {"partnerType", "oldCustomer", "userName", "phoneNumber", "workUnitType", "workUnitName", "shopName", "shopAddress",
                    "bank", "needMaterial", "materials", "receiver", "receiverPhoneNumber", "receiverArea", "receiverAddress",
                    "passLoanCount", "totalLoanCount", "createTime"};
            outExcel.exportExcel(title, headers, fields, dataset, excelStream);
        } catch (Exception e) {
            logger.error("export fail.", e);
        }

    }

}
