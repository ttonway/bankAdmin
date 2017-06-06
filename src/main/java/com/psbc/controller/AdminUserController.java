package com.psbc.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.psbc.pojo.AdminUser;
import com.psbc.service.AdminUserService;
import com.psbc.util.MD5Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/adminUser")
public class AdminUserController {
	private static Logger logger = Logger.getLogger(AdminUserController.class);

	@Value("${service.loan0.url}")
	private String loan0Url;
	@Value("${service.loan1.url}")
	private String loan1Url;

	@Autowired
	private AdminUserService adminUserService;

	@RequestMapping("/getUserList")
	@ResponseBody
	public Map<String, Object> getUserList(Integer draw, Integer start, Integer length) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int cnt = adminUserService.selectByCnt();
			List<AdminUser> list = adminUserService.selectByList(start, length);

			map.put("draw", draw);
			map.put("recordsTotal", cnt);
			map.put("recordsFiltered", cnt);
			map.put("data", list);
			map.put("code", 0);
			return map;
		} catch (Exception e) {
			logger.error("getUserList fail.", e);
			map.put("code", 1);
			return map;
		}
	}
	
	
	@RequestMapping("/submitUser")
	@ResponseBody
	public Map<String, Object> submitUser(String usernm,String usercode,String bank,String userrole) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			AdminUser adminUser = adminUserService.selectByCode(usercode);
			if(adminUser == null){
				adminUser = new AdminUser();
				adminUser.setBank(bank);
				adminUser.setUsercode(usercode);
				adminUser.setUsernm(usernm);
				adminUser.setUserrole(userrole);
				adminUser.setUserpw(MD5Util.getMD5(usercode));
				int i = adminUserService.insertSelective(adminUser);
				if(i == 1){
					map.put("code", 0);
				}else{
					map.put("code", 1);
				}
			}else{
				map.put("code", 2);
			}
			return map;
		} catch (Exception e) {
			logger.error("submitUser fail.", e);
			map.put("code", 1);
			return map;
		}
	}
	
	
	
	@RequestMapping("/submitReset")
	@ResponseBody
	public Map<String, Object> submitReset(HttpServletRequest req, String userPw) {
		AdminUser user = (AdminUser) req.getSession().getAttribute("user");

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			AdminUser adminUser  = new AdminUser();
			adminUser.setUserid(user.getUserid());
			if(StringUtils.isEmpty(userPw)){
				adminUser.setUserpw(MD5Util.getMD5(user.getUsercode()));
			}else{
				adminUser.setUserpw(MD5Util.getMD5(userPw));
			}
			adminUserService.updateByPrimaryKeySelective(adminUser);
			map.put("code", 0);
			return map;
		} catch (Exception e) {
			logger.error("submitReset fail.", e);
			map.put("code", 1);
			return map;
		}
	}
	
	@RequestMapping("/deleteUser")
	@ResponseBody
	public Map<String, Object> deleteUser(Long[] userid) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			adminUserService.deleteByPrimaryKey(userid);
			map.put("code", 0);
			return map;
		} catch (Exception e) {
			logger.error("deleteUser fail.", e);
			map.put("code", 1);
			return map;
		}
	}

	@RequestMapping(value = "/qrcode/{userCode}/{loanType}")
	public void qrcode(HttpServletResponse response, @PathVariable String userCode, @PathVariable String loanType, Integer w, Integer h) {
		int width = w == null ? 200 : w.intValue();
		int height = h == null ? 200 : h.intValue();

		String url = null;
		loanType.equals("0")

		try {
			OutputStream stream = response.getOutputStream();
			QRCodeWriter writer = new QRCodeWriter();
			BitMatrix m = writer.encode(url, BarcodeFormat.QR_CODE, height, width);
			MatrixToImageWriter.writeToStream(m, "png", stream);
			stream.flush();
		} catch (IOException e) {
			logger.error("qrcode fail.", e);
		}
	}
}
