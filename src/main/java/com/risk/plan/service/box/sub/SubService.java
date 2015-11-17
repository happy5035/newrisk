package com.risk.plan.service.box.sub;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.plan.common.BaseService;
import com.risk.plan.dao.SubMapper;
import com.risk.plan.entity.Emergency;
import com.risk.plan.entity.Sub;

@Service	
public class SubService extends BaseService<Sub>{
	@Autowired
	SubMapper subMapper;
	public List<Sub> selectByEmerId(Map<String, Object> params){
		return subMapper.selectByEmerId(params);
			
}
	}
