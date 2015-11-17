package com.risk.plan.dao;

import java.util.List;
import java.util.Map;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.Emergency;
@MyBatisRepository
public interface EmergencyMapper  extends BaseMapper<Emergency>{
    
	public List<Emergency> selectByUserid(String userid);
	public List<Emergency> selectByEmerId(Map<String, Object> params);
	public List<Emergency> selectByEmerTypeName(Map<String, Object> params);
	public List<Emergency> selectByEmerName(Map<String, Object> params);
}