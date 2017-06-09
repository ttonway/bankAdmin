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
            model.addObject("error", "�û������������");
        }
        if (logout != null) {
            model.addObject("msg", "���ѵǳ�ϵͳ��");
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

    // ���Ŵ�
    @RequestMapping("/list1")
    public String list1() {
        return "/list1";
    }

    // ���״�
    @RequestMapping("/list2")
    public String list2() {
        return "/list2";
    }

    // ����
    @RequestMapping("/poster")
    public String poster() {
        return "/poster";
    }

}
