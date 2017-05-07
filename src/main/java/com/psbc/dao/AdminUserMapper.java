package com.psbc.dao;

import java.util.List;
import java.util.Map;

import com.psbc.pojo.AdminUser;

public interface AdminUserMapper {
    int deleteByPrimaryKey(Long[] userid);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Long userid);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);
    
    AdminUser selectByCode(String usercode);

    List<AdminUser> selectByList(Map<String, Integer> map);

    int selectByCnt();
}