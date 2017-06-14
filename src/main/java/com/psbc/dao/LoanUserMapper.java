package com.psbc.dao;

import java.util.List;
import java.util.Map;

import com.psbc.pojo.LoanUser;

public interface LoanUserMapper {
    int deleteByPrimaryKey(Long[] loanid);

    int insert(LoanUser record);

    int insertSelective(LoanUser record);

    LoanUser selectByPrimaryKey(Integer loanid);

    int updateByPrimaryKeySelective(LoanUser record);

    int updateByPrimaryKey(LoanUser record);
    
    int selectByCnt(Map<String,Object> map);

    List<LoanUser> selectByList(Map<String,Object> map);
    
    List<Map<String,Object>> selectByStatusCnt(Map<String,Object> map);
    
    Map<String,String> selectByDetial(Map<String,Long> map);
    
    List<LoanUser> export(Map<String, Object> map);
}