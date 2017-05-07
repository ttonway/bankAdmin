package com.psbc.controller;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.psbc.pojo.LoanUser;
import com.psbc.service.LoanUserService;
import com.psbc.util.ExportExcelUtils;

@Controller
@RequestMapping("/loan")
public class AdminLoanController {
    private static Logger logger = Logger.getLogger(AdminLoanController.class);

    @Autowired
    private LoanUserService loanUserService;


    @RequestMapping("/getLoanList")
    @ResponseBody
    public Map<String, Object> getLoanList(String loanType, String userrole, String bank, String status, Integer startrow, Integer endrow) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Map<String, Object> pmap = new HashMap<String, Object>();
            if ("֧��Ӫ����".equals(userrole)) {
                pmap.put("bank", bank);
            } else {
                pmap.put("bank", null);
            }
            pmap.put("type", loanType);
            pmap.put("status", status);
            pmap.put("startrow", startrow);
            pmap.put("endrow", endrow - startrow);
            List<Map<String, Object>> cntlist = loanUserService.selectByStatusCnt(pmap);
            map.put("cntlist", cntlist);
            int cnt = loanUserService.selectByCnt(pmap);
            map.put("cnt", cnt);
            List<LoanUser> list = loanUserService.selectByList(pmap);
            map.put("result", list);
            map.put("code", 0);
            return map;
        } catch (Exception e) {
            logger.error("getLoanList fail.", e);
            map.put("code", 1);
            return map;
        }
    }

    @RequestMapping("/deleteLoan")
    @ResponseBody
    public Map<String, Object> deleteLoan(Long[] loanid) {
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

    @RequestMapping("/updateLoan")
    @ResponseBody
    public Map<String, Object> updateLoan(String status, String remark, Long loanid, Integer loanNum) {
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

    @RequestMapping("/selecDetial")
    @ResponseBody
    public Map<String, Object> selecDetial(Long loanid) {
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
    public void export(HttpServletResponse response, String userrole, String bank, String status) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> pmap = new HashMap<String, Object>();
        if ("֧��Ӫ����".equals(userrole)) {
            pmap.put("bank", bank);
        } else {
            pmap.put("bank", null);
        }
        pmap.put("status", status);


        // ��ȡ�ٷֱ�
        NumberFormat nf = NumberFormat.getPercentInstance();
        // С��������ٱ�����λС��
        nf.setMinimumFractionDigits(2);

        // ����response����
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition;charset=UTF-8", "attachment;filename=����.xls");
        // ���������
        OutputStream excelStream;
        try {
            excelStream = new BufferedOutputStream(response.getOutputStream());
            Collection<LoanUser> dataset = loanUserService.export(pmap);
            ExportExcelUtils<LoanUser> outExcel = new ExportExcelUtils<LoanUser>();
            // ����sheetҳ����
            String title = "����";
            // �����ͷ
            String[] headers = {"������ID", "����������", "��ϵ�ֻ�", "����", "֧��", "�Ƽ���", "ID", "������", "������λ", "����ʱ��", "״̬", "��ע", "����ʱ��", "��Դ"};
            outExcel.exportExcel(title, headers, dataset, excelStream);
        } catch (Exception e) {
            logger.error("export fail.", e);
        }

    }

}
