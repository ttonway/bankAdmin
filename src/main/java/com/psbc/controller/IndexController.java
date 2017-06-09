package com.psbc.controller;

import com.psbc.pojo.AdminUserDetails;
import com.psbc.util.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("/login")
    public ModelAndView login(String error, String logout) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "用户名或密码错误。");
        }
        if (logout != null) {
            model.addObject("msg", "您已登出系统。");
        }
        model.setViewName("login");
        return model;
    }

    @RequestMapping("/index")
    public String main() {
        AdminUserDetails userDetails = (AdminUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if (AuthorityUtils.hasAuthority(userDetails, "ROLE_ADMIN")) {
            return "redirect:/userlist";
        } else {
            return "redirect:/list1";
        }
    }

    @RequestMapping("/userlist")
    public String userlist() {
        return "/userlist";
    }

    // 邮信贷
    @RequestMapping("/list1")
    public String list1() {
        return "/list1";
    }

    // 商易贷
    @RequestMapping("/list2")
    public String list2() {
        return "/list2";
    }

    // 海报
    @RequestMapping("/poster")
    public String poster() {
        return "/poster";
    }

}
