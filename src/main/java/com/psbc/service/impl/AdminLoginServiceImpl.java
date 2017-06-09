package com.psbc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.psbc.dao.AdminLoginMapper;
import com.psbc.pojo.AdminLogin;
import com.psbc.service.AdminLoginService;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {
	@Resource
	AdminLoginMapper adminLoginMapper;
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(AdminLogin record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(AdminLogin record) {
		return adminLoginMapper.insertSelective(record);
	}

	@Override
	public AdminLogin selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(AdminLogin record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(AdminLogin record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
