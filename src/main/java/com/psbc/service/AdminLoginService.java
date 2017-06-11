package com.psbc.service;

import com.psbc.pojo.AdminLogin;

public interface AdminLoginService {
    int deleteByPrimaryKey(Long id);

    int insert(AdminLogin record);

    int insertSelective(AdminLogin record);

    AdminLogin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminLogin record);

    int updateByPrimaryKey(AdminLogin record);
}