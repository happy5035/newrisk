package com.risk.plan.service.box.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.plan.common.BaseService;
import com.risk.plan.dao.UsersMapper;
import com.risk.plan.entity.Users;

@Service
public class UsersService  extends BaseService<Users>{
	@Autowired
	UsersMapper usersmapper;
	 public List<Users> selectByLogname(String logname){
		 return usersmapper.selectByLogname(logname);
	 }
}
