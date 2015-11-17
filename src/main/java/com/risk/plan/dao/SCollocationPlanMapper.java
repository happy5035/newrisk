package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.SCollocationPlan;
@MyBatisRepository
public interface SCollocationPlanMapper extends BaseMapper<SCollocationPlan>{
    int deleteByPrimaryKey(String scollocationplanid);

    int insert(SCollocationPlan record);

    int insertSelective(SCollocationPlan record);

    SCollocationPlan selectByPrimaryKey(String scollocationplanid);

    int updateByPrimaryKeySelective(SCollocationPlan record);

    int updateByPrimaryKey(SCollocationPlan record);
}