package com.risk.plan.dao;

import java.util.List;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.Points;
@MyBatisRepository
public interface PointsMapper extends BaseMapper<Points>{
    public List<Points> selectByEdgeId(String edgeid);
}