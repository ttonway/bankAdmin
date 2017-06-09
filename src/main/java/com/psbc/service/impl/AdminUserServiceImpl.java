package com.psbc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.psbc.dao.AdminUserMapper;
import com.psbc.pojo.AdminUser;
import com.psbc.service.AdminUserService;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Resource
	private AdminUserMapper adminUserMapper;
	
	@Override
	public int deleteByPrimaryKey(Long[] userid) {
		return adminUserMapper.deleteByPrimaryKey(userid);
	}

	@Override
	public int insert(AdminUser record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(AdminUser record) {
		return adminUserMapper.insertSelective(record);
	}

	@Override
	public AdminUser selectByPrimaryKey(Long userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(AdminUser record) {
		return adminUserMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AdminUser record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public AdminUser selectByCode(String usercode){
		return adminUserMapper.selectByCode(usercode);
	};
	
	@Override
	public List<AdminUser> selectByList(int start, int length){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("length", length);
		return adminUserMapper.selectByList(map);
	}
	@Override
	public int selectByCnt(){
		int i = adminUserMapper.selectByCnt();
		return i;
	}	
}
