package com.psbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.psbc.pojo.AdminLogin;
import com.psbc.pojo.AdminUser;
import com.psbc.service.AdminLoginService;
import com.psbc.service.AdminUserService;
import com.psbc.util.MD5Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static Map<String, Double> jobmap = new HashMap<String, Double>();

    static {
        jobmap.put("select4A", 0.55);
        jobmap.put("select4B", 0.75);
        jobmap.put("select4C", 0.85);
    }

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private AdminLoginService adminLoginService;

    @RequestMapping("/getAdminUser")
    @ResponseBody
    public Map<String, String> getAdminUser(String usercode, String userpw, HttpServletRequest req) {
        Map<String, String> map = new HashMap<String, String>();
        AdminUser adminUser = adminUserService.selectByCode(usercode);
        if (adminUser == null) {
            map.put("code", "000");
        }
        if (adminUser.getUserpw().equals(MD5Util.getMD5(userpw))) {
            HttpSession session = req.getSession();
            session.setAttribute("user", adminUser);

            SimpleDateFormat dateFormater = new SimpleDateFormat("yyyyMMdd hhmmss");
            AdminLogin adminLogin = new AdminLogin();
            adminLogin.setUsercode(usercode);
            adminLogin.setLogintime(dateFormater.format(new Date()));
            adminLogin.setLoginstatus("1");
            adminLoginService.insertSelective(adminLogin);

            map.put("code", "001");
        } else {
            map.put("code", "002");
        }
        return map;
    }

    @RequestMapping("/main")
    public String main(HttpServletRequest req) {
        AdminUser user = (AdminUser) req.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login.htm";
        }

        if (user.getUserrole().equals("系统管理员")) {
            return "/userlist";
        } else {
            return "/list1";
        }
    }

    @RequestMapping("/userlist")
    public String userlist(HttpServletRequest req) {
        AdminUser user = (AdminUser) req.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login.htm";
        }

        return "/userlist";
    }

    // 邮信贷
    @RequestMapping("/list1")
    public String list1(HttpServletRequest req) {
        AdminUser user = (AdminUser) req.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login.htm";
        }

        return "/list1";
    }

    // 商易贷
    @RequestMapping("/list2")
    public String list2(HttpServletRequest req) {
        AdminUser user = (AdminUser) req.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login.htm";
        }

        return "/list2";
    }

}
