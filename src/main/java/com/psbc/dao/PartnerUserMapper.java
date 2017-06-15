package com.psbc.dao;

import com.psbc.pojo.PartnerUser;

import java.util.List;
import java.util.Map;

public interface PartnerUserMapper {
    int deleteByPrimaryKey(Long[] partnerIds);

    int insert(PartnerUser record);

    int updateByPrimaryKey(PartnerUser record);

    PartnerUser selectByPrimaryKey(Long partnerId);

    PartnerUser selectByPhoneNumber(String phoneNumber);

    List<PartnerUser> selectByList(Map<String, Integer> map);

    int selectByCnt();
}