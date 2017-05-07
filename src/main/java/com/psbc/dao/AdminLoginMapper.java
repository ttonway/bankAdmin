package com.psbc.dao;

import com.psbc.pojo.AdminLogin;

public interface AdminLoginMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdminLogin record);

    int insertSelective(AdminLogin record);

    AdminLogin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminLogin record);

    int updateByPrimaryKey(AdminLogin record);
}