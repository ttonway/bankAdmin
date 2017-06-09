package com.psbc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.psbc.pojo.AdminUserDetails;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.psbc.pojo.AdminUser;
import com.psbc.service.AdminUserService;
import com.psbc.util.MD5Util;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static Logger logger = Logger.getLogger(AdminController.class);

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
		AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal();

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			AdminUser adminUser  = new AdminUser();
			adminUser.setUserid(userDetails.getUserId());
			if(StringUtils.isEmpty(userPw)){
				adminUser.setUserpw(MD5Util.getMD5(userDetails.getUsername()));
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
}
