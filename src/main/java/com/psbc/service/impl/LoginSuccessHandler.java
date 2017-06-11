package com.psbc.service.impl;

import com.psbc.pojo.AdminLogin;
import com.psbc.pojo.AdminUserDetails;
import com.psbc.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ttonway on 2017/6/11.
 */
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private AdminLoginService adminLoginService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        AdminUserDetails userDetails = (AdminUserDetails) authentication.getPrincipal();

        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyyMMdd hhmmss");
        AdminLogin adminLogin = new AdminLogin();
        adminLogin.setUsercode(userDetails.getUsername());
        adminLogin.setLogintime(dateFormater.format(new Date()));
        adminLogin.setLoginstatus("1");
        adminLoginService.insertSelective(adminLogin);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
