package com.psbc.service;

import com.psbc.pojo.PartnerUser;

import java.util.List;

public interface PartnerUserService {
    int deleteByPrimaryKey(Long[] partnerIds);

    int insert(PartnerUser record);

    int updateByPrimaryKey(PartnerUser record);

    PartnerUser selectByPrimaryKey(Long partnerId);

    List<PartnerUser> selectByList(int start, int length);

    int selectByCnt();
}