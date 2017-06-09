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

import com.psbc.pojo.AdminUserDetails;
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


    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(String loanType, String status, Integer draw, Integer start, Integer length) {
        AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Map<String, Object> pmap = new HashMap<String, Object>();
            if ("֧��Ӫ����".equals(userDetails.getUserRole())) {
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

    @RequestMapping("/export")
    public void export(HttpServletRequest req, HttpServletResponse response, String status, String loanType) {
        AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        Map<String, Object> pmap = new HashMap<String, Object>();
        if ("֧��Ӫ����".equals(userDetails.getUserRole())) {
            pmap.put("bank", userDetails.getUserBank());
        } else {
            pmap.put("bank", null);
        }
        pmap.put("status", status);
        pmap.put("type", loanType);


        // ��ȡ�ٷֱ�
        NumberFormat nf = NumberFormat.getPercentInstance();
        // С��������ٱ�����λС��
        nf.setMinimumFractionDigits(2);

        // ����response����
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=\"export.xls\"");
        // ���������
        OutputStream excelStream;
        try {
            logger.info("export excel " + loanType);
            ExportExcelUtils<LoanUser> outExcel = new ExportExcelUtils<LoanUser>();
            // ����sheetҳ����
            String title = "����";
            // �����ͷ
            if ("���Ŵ�".equals(loanType)) {
                excelStream = new BufferedOutputStream(response.getOutputStream());
                Collection<LoanUser> dataset = loanUserService.export(pmap);
                String[] headers = {"������ID", "����������", "��ϵ�ֻ�", "����", "֧��", "�Ƽ���", "ID", "������", "������λ", "����ʱ��", "״̬", "��ע", "����ʱ��", "��Դ"};
                String[] fields = {"loanid", "usernm", "phonenum", "area", "bank", "referrals", "id", "loanNum", "workunit", "createtime", "status", "remark", "updatetime", "utmsrc"};
                outExcel.exportExcel(title, headers, fields, dataset, excelStream);
            } else if ("���״�".equals(loanType)) {
                excelStream = new BufferedOutputStream(response.getOutputStream());
                Collection<LoanUser> dataset = loanUserService.export(pmap);
                String[] headers = {"������ID", "����������", "��ϵ�ֻ�", "����", "֧��", "�Ƽ���", "ID", "������", "��ҵ��Ϣ��һ����", "��ҵ��Ϣ��������", "�Ƿ�Ϊ������", "�����Ƿ��з���", "������ʽ", "����ʱ��", "״̬", "��ע", "����ʱ��", "��Դ"};
                String[] fields = {"loanid", "usernm", "phonenum", "area", "bank", "referrals", "id", "loanNum", "workunit", "workunit2", "localPerson", "house", "guaranteeType", "createtime", "status", "remark", "updatetime", "utmsrc"};
                outExcel.exportExcel(title, headers, fields, dataset, excelStream);
            }
        } catch (Exception e) {
            logger.error("export fail.", e);
        }

    }

}
