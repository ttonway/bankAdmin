package com.psbc.service;

import com.psbc.pojo.PartnerUser;

import java.util.List;
import java.util.Map;

public interface PartnerUserService {
    int deleteByPrimaryKey(Long[] partnerIds);

    int insert(PartnerUser record);

    int updateByPrimaryKey(PartnerUser record);

    PartnerUser selectByPrimaryKey(Long partnerId);

    PartnerUser selectByPhoneNumber(String phoneNumber);

    List<PartnerUser> selectByList(Map<String,Object> map);

    int selectByCnt(Map<String,Object> map);
}