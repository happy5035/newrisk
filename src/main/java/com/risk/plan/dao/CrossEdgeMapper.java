package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.CrossEdge;
@MyBatisRepository
public interface CrossEdgeMapper extends BaseMapper<CrossEdge>{
    int deleteByPrimaryKey(String crossedgeid);

    int insert(CrossEdge record);

    int insertSelective(CrossEdge record);

    CrossEdge selectByPrimaryKey(String crossedgeid);

    int updateByPrimaryKeySelective(CrossEdge record);

    int updateByPrimaryKey(CrossEdge record);
}