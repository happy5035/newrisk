package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.DisasterNode;
@MyBatisRepository
public interface DisasterNodeMapper extends BaseMapper<DisasterNode>{
    int deleteByPrimaryKey(String dnodeid);

    int insert(DisasterNode record);

    int insertSelective(DisasterNode record);

    DisasterNode selectByPrimaryKey(String dnodeid);

    int updateByPrimaryKeySelective(DisasterNode record);

    int updateByPrimaryKey(DisasterNode record);
}