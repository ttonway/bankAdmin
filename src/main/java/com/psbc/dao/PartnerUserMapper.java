package com.psbc.dao;

import com.psbc.pojo.AdminUser;
import com.psbc.pojo.PartnerUser;

import java.util.List;
import java.util.Map;

public interface PartnerUserMapper {
    int deleteByPrimaryKey(Long[] partnerIds);

    int insert(PartnerUser record);

    PartnerUser selectByPrimaryKey(Long partnerId);

    List<AdminUser> selectByList(Map<String, Integer> map);

    int selectByCnt();
}