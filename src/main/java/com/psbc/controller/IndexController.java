package com.psbc.controller;

import com.psbc.pojo.AdminUserDetails;
import com.psbc.util.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView list1() {
        return new ModelAndView("loanlist", "loanType", "���Ŵ�");
    }

    // ���״�
    @RequestMapping("/list2")
    public ModelAndView list2() {
        return new ModelAndView("loanlist", "loanType", "���״�");
    }

    // ����
    @RequestMapping("/poster")
    public String poster() {
        return "postergrid";
    }

}
