package com.psbc.service.impl;

import com.psbc.pojo.AdminUser;
import com.psbc.pojo.AdminUserDetails;
import com.psbc.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ttonway on 2017/6/8.
 */
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private AdminUserService adminUserService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        AdminUser adminUser = adminUserService.selectByCode(username);
        if (adminUser == null) {
            return null;
        } else {
            List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
            if (adminUser.getRole().equals("系统管理员")) {
                auths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else {
                auths.add(new SimpleGrantedAuthority("ROLE_USER"));
            }

            AdminUserDetails user = new AdminUserDetails(adminUser.getUserCode(), adminUser.getPassword(), true, true, true, true, auths);
            user.setUserId(adminUser.getUserId());
            user.setShowname(adminUser.getUserName());
            user.setUserRole(adminUser.getRole());
            user.setUserBank(adminUser.getBank());
            return user;
        }
    }
}
