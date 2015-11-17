package com.risk.plan.dao;

import java.util.List;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.Nodes;
@MyBatisRepository
public interface NodesMapper extends BaseMapper<Nodes>{
	
	public List<Nodes> selectByUserid(String userid);
	
	public List<Nodes> selectByEmerid(String emerid);
	
}