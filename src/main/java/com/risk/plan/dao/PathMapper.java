package com.risk.plan.dao;

import java.util.List;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.Path;
@MyBatisRepository
public interface PathMapper extends BaseMapper<Path>{
    
	public List<Path> findByTaskId(String taskid);
}