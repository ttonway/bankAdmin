package com.psbc.service;

import java.util.List;

import com.psbc.pojo.AdminUser;

public interface AdminUserService {
    int deleteByPrimaryKey(Long[] userid);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Long userid);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);
    
    AdminUser selectByCode(String usercode);
    
    public List<AdminUser> selectByList(int start,int length);
    
    public int selectByCnt();
}