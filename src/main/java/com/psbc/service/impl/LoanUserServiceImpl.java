package com.psbc.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.psbc.dao.LoanUserMapper;
import com.psbc.pojo.LoanUser;
import com.psbc.service.LoanUserService;

@Service
public class LoanUserServiceImpl implements LoanUserService {
	@Resource
	LoanUserMapper daoClent = null;

	@Override
	public int deleteByPrimaryKey(Long[] loanid) {
		return daoClent.deleteByPrimaryKey(loanid);
	}

	@Override
	public int insert(LoanUser record) {
		return 0;
	}

	@Override
	public int insertSelective(LoanUser record) {
		return daoClent.insertSelective(record);
	}

	@Override
	public LoanUser selectByPrimaryKey(Long loanid) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(LoanUser record) {
		return daoClent.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(LoanUser record) {
		return 0;
	}
	@Override
    public int selectByCnt(Map<String,Object> map){
		return daoClent.selectByCnt(map);
	};

	@Override
    public List<LoanUser> selectByList(Map<String,Object> map){
		return daoClent.selectByList(map);
	};

	@Override
	public List<Map<String,Object>> selectByStatusCnt(Map<String,Object> map){
		return daoClent.selectByStatusCnt(map);
	};

	@Override
	public Map<String,String> selectByDetial(Map<String,Long> map){
		return daoClent.selectByDetial(map);
	}

	@Override
	public Collection<LoanUser> export(Map<String, Object> map) {
		return daoClent.export(map);
	}
	
	
}
