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

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static Logger logger = Logger.getLogger(AdminController.class);

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping("/users")
    @ResponseBody
    public Map<String, Object> users(Integer draw, Integer start, Integer length) {
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
            logger.error("list all users fail.", e);
            map.put("code", 1);
            return map;
        }
    }

    @RequestMapping("/create")
    @ResponseBody
    public Map<String, Object> create(String userName, String userCode, String bank, String role) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            AdminUser adminUser = adminUserService.selectByCode(userCode);
            if (adminUser == null) {
                adminUser = new AdminUser();
                adminUser.setBank(bank);
                adminUser.setUserCode(userCode);
                adminUser.setUserName(userName);
                adminUser.setRole(role);
                adminUser.setPassword(MD5Util.getMD5(userCode));
                int i = adminUserService.insert(adminUser);
                if (i == 1) {
                    map.put("code", 0);
                } else {
                    map.put("code", 1);
                }
            } else {
                map.put("code", 2);
            }
            return map;
        } catch (Exception e) {
            logger.error("create user fail.", e);
            map.put("code", 1);
            return map;
        }
    }


    @RequestMapping("/resetPwd")
    @ResponseBody
    public Map<String, Object> resetPwd(Long userId, String password) {


        Map<String, Object> map = new HashMap<String, Object>();
        try {
            AdminUser adminUser = new AdminUser();
            adminUser.setUserId(userId);
            if (StringUtils.isEmpty(password)) {
                adminUser = adminUserService.selectByPrimaryKey(userId);
                adminUser.setPassword(MD5Util.getMD5(adminUser.getUserCode()));
            } else {
                adminUser.setPassword(MD5Util.getMD5(password));
            }
            adminUserService.updateByPrimaryKeySelective(adminUser);
            map.put("code", 0);
            return map;
        } catch (Exception e) {
            logger.error("reset password fail.", e);
            map.put("code", 1);
            return map;
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(Long[] userIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            adminUserService.deleteByPrimaryKey(userIds);
            map.put("code", 0);
            return map;
        } catch (Exception e) {
            logger.error("delete user fail.", e);
            map.put("code", 1);
            return map;
        }
    }
}
