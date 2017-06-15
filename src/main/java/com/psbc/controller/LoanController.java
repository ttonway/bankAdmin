package com.psbc.controller;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.psbc.pojo.AdminUser;
import com.psbc.pojo.AdminUserDetails;
import com.psbc.pojo.PartnerUser;
import com.psbc.service.AdminUserService;
import com.psbc.service.PartnerUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.psbc.pojo.LoanUser;
import com.psbc.service.LoanUserService;
import com.psbc.util.ExportExcelUtils;

@Controller
@RequestMapping("/loan")
public class LoanController {
    private static Logger logger = Logger.getLogger(LoanController.class);

    @Autowired
    private LoanUserService loanUserService;

    @Autowired
    private PartnerUserService partnerUserService;

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(String loanType, String status, Integer draw, Integer start, Integer length) {
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
            pmap.put("type", loanType);
            pmap.put("status", status);
            pmap.put("start", start);
            pmap.put("length", length);
            List<Map<String, Object>> cntlist = loanUserService.selectByStatusCnt(pmap);
            map.put("cntlist", cntlist);
            int cnt = loanUserService.selectByCnt(pmap);
            List<LoanUser> list = loanUserService.selectByList(pmap);

            map.put("draw", draw);
            map.put("recordsTotal", cnt);
            map.put("recordsFiltered", cnt);
            map.put("data", list);
            map.put("code", 0);
            return map;
        } catch (Exception e) {
            logger.error("getLoanList fail.", e);
            map.put("code", 1);
            return map;
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(Long[] loanid) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            loanUserService.deleteByPrimaryKey(loanid);
            map.put("code", 0);
            return map;
        } catch (Exception e) {
            logger.error("deleteLoan fail.", e);
            map.put("code", 1);
            return map;
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Object> update(String status, String remark, Long loanid, Integer loanNum) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            LoanUser loanUser = new LoanUser();
            loanUser.setLoanid(loanid);
            loanUser.setStatus(status);
            loanUser.setRemark(remark);
            loanUser.setLoanNum(loanNum);
            loanUserService.updateByPrimaryKeySelective(loanUser);
            map.put("code", 0);
            return map;
        } catch (Exception e) {
            logger.error("updateLoan fail.", e);
            map.put("code", 1);
            return map;
        }
    }

    @RequestMapping("/get")
    @ResponseBody
    public Map<String, Object> get(Long loanid) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Map<String, Long> pmap = new HashMap<String, Long>();
            pmap.put("loanid", loanid);
            Map<String, String> map2 = loanUserService.selectByDetial(pmap);
            map.put("result", map2);
            map.put("code", 0);
            return map;
        } catch (Exception e) {
            logger.error("selecDetial fail.", e);
            map.put("code", 1);
            return map;
        }
    }

    @RequestMapping("/getFromUser")
    @ResponseBody
    public Map<String, Object> getFromUserInfo(String userCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        final String prefix = "partner-";
        try {
            if (userCode.startsWith(prefix)) {
                long partnerId = Long.parseLong(userCode.substring(prefix.length()));
                PartnerUser user = partnerUserService.selectByPrimaryKey(partnerId);
                map.put("partner", user);
            } else {
                AdminUser user = adminUserService.selectByCode(userCode);
                map.put("admin", user);
            }

            map.put("code", 0);
            return map;
        } catch (Exception e) {
            logger.error("getFromUserInfo fail.", e);
            map.put("code", 1);
            return map;
        }
    }

    @RequestMapping("/export")
    public void export(HttpServletResponse response, String loanType, String status) {
        AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Map<String, Object> pmap = new HashMap<String, Object>();
        if ("支行营销岗".equals(userDetails.getUserRole())) {
            pmap.put("bank", userDetails.getUserBank());
        } else {
            pmap.put("bank", null);
        }
        pmap.put("status", status);
        pmap.put("type", loanType);


        // 获取百分比
        NumberFormat nf = NumberFormat.getPercentInstance();
        // 小数点后最少保留两位小数
        nf.setMinimumFractionDigits(2);

        // 定义response类型
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=\"export.xls\"");
        // 定义输出流
        OutputStream excelStream;
        try {
            logger.info("export excel " + loanType);
            ExportExcelUtils<LoanUser> outExcel = new ExportExcelUtils<LoanUser>();
            // 定义sheet页标题
            String title = "下载";
            // 定义表头
            if ("邮信贷".equals(loanType)) {
                excelStream = new BufferedOutputStream(response.getOutputStream());
                Collection<LoanUser> dataset = loanUserService.export(pmap);
                String[] headers = {"申请人ID", "申请人姓名", "联系手机", "区域", "支行", "推荐人", "ID", "申请金额", "工作单位", "申请时间", "状态", "备注", "跟新时间", "来源"};
                String[] fields = {"loanid", "usernm", "phonenum", "area", "bank", "referrals", "id", "loanNum", "workunit", "createtime", "status", "remark", "updatetime", "fromUserCode"};
                outExcel.exportExcel(title, headers, fields, dataset, excelStream);
            } else if ("商易贷".equals(loanType)) {
                excelStream = new BufferedOutputStream(response.getOutputStream());
                Collection<LoanUser> dataset = loanUserService.export(pmap);
                String[] headers = {"申请人ID", "申请人姓名", "联系手机", "区域", "支行", "推荐人", "ID", "申请金额", "行业信息（一级）", "行业信息（二级）", "是否为本地人", "本地是否有房产", "担保方式", "申请时间", "状态", "备注", "跟新时间", "来源"};
                String[] fields = {"loanid", "usernm", "phonenum", "area", "bank", "referrals", "id", "loanNum", "workunit", "workunit2", "localPerson", "house", "guaranteeType", "createtime", "status", "remark", "updatetime", "fromUserCode"};
                outExcel.exportExcel(title, headers, fields, dataset, excelStream);
            }
        } catch (Exception e) {
            logger.error("export fail.", e);
        }

    }

}
