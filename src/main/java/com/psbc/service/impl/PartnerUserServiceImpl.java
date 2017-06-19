package com.psbc.service.impl;

import com.psbc.dao.PartnerUserMapper;
import com.psbc.pojo.PartnerUser;
import com.psbc.service.PartnerUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PartnerUserServiceImpl implements PartnerUserService {

    @Resource
    private PartnerUserMapper partnerUserMapper;

    @Override
    public int deleteByPrimaryKey(Long[] partnerIds) {
        return partnerUserMapper.deleteByPrimaryKey(partnerIds);
    }

    @Override
    public int insert(PartnerUser record) {
        return partnerUserMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKey(PartnerUser record) {
        return partnerUserMapper.updateByPrimaryKey(record);
    }

    @Override
    public PartnerUser selectByPrimaryKey(Long partnerId) {
        return partnerUserMapper.selectByPrimaryKey(partnerId);
    }

    @Override
    public PartnerUser selectByPhoneNumber(String phoneNumber) {
        return partnerUserMapper.selectByPhoneNumber(phoneNumber);
    }

    @Override
    public List<PartnerUser> selectByList(Map<String,Object> map) {
        return partnerUserMapper.selectByList(map);
    }

    @Override
    public int selectByCnt(Map<String,Object> map) {
        return partnerUserMapper.selectByCnt(map);
    }
}
