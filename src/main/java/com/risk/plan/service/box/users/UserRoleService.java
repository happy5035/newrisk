package com.risk.plan.service.box.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.plan.common.BaseService;
import com.risk.plan.dao.UserRoleMapper;
import com.risk.plan.entity.UserRole;

@Service

public class UserRoleService extends BaseService<UserRole>{
	@Autowired
	UserRoleMapper userRoleMapper;
	public List<UserRole> selectByUserId (String userid){
		return userRoleMapper.selectByUserId(userid);
	}
}
