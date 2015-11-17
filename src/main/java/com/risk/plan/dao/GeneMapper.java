package com.risk.plan.dao;


import java.io.Serializable;
import java.util.List;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.Gene;
@MyBatisRepository
public interface GeneMapper extends BaseMapper<Gene>{
	public void deleteByTransid (String transid);
	
	public List<Gene> selectByTransid(String transid);
}