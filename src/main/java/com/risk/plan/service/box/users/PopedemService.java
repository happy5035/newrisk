package com.risk.plan.service.box.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.plan.common.BaseService;
import com.risk.plan.dao.PopedemMapper;
import com.risk.plan.entity.Popedem;

@Service
public class PopedemService extends BaseService<Popedem>{

	@Autowired
	PopedemMapper popedemmapper;
	
	 public  List<Popedem> selectByRoleId (String roleid){
		return popedemmapper.selectByRoleId(roleid);
		
	}
}
