package com.psbc.controller;

import com.psbc.pojo.AdminUser;
import com.psbc.service.AdminUserService;
import com.psbc.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/poster")
public class PosterController {

	@Value("${fileserver.path}")
	private String fileRootPath;

	@Value("${fileserver.httpurl}")
	private String fileRootUrl;

	@Autowired
	private AdminUserService adminUserService;

	@RequestMapping("/all")
	@ResponseBody
	public Map<String, Object> getUserList(Integer startrow,Integer endrow) {
		return null;
//		Map<String, Object> map = new HashMap<String, Object>();
//		try {
//			int cnt = adminUserService.selectByCnt();
//			List<AdminUser> list = adminUserService.selectByList(startrow,endrow);
//			map.put("code", 0);
//			map.put("cnt", cnt);
//			map.put("result", list);
//			return map;
//		} catch (Exception e) {
//			map.put("code", 1);
//			return map;
//		}
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
			map.put("code", 1);
			return map;
		}
	}
	
	
	
	@RequestMapping("/submitReset")
	@ResponseBody
	public Map<String, Object> submitReset(Long userid,String usercode,String userPw) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			AdminUser adminUser  = new AdminUser();
			adminUser.setUserid(userid);	
			if(StringUtils.isEmpty(userPw)){
				adminUser.setUserpw(MD5Util.getMD5(usercode));
			}else{
				adminUser.setUserpw(MD5Util.getMD5(userPw));
			}
			adminUserService.updateByPrimaryKeySelective(adminUser);
			map.put("code", 0);
			return map;
		} catch (Exception e) {
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
			map.put("code", 1);
			return map;
		}
	}	
}
