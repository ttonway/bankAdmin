package com.psbc.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by ttonway on 2017/6/8.
 */
public class AuthorityUtils {

    public static boolean hasAuthority(UserDetails user, String role) {
        for (GrantedAuthority grantedAuthority : user.getAuthorities()) {
            if (grantedAuthority.getAuthority().equals(role)) {
                return true;
            }
        }
        return false;
    }
}
