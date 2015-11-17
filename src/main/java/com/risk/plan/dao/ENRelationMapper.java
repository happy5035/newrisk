package com.risk.plan.dao;

import java.util.List;
import java.util.Map;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.ENRelation;
@MyBatisRepository
public interface ENRelationMapper extends BaseMapper<ENRelation> {
    
	public List<ENRelation> selectByWareHouseParmas(Map<String, Object> params);
	
	public int selectCountByWareHouseParams(Map<String, Object> params);
}