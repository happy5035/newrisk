package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.ProducRelation;
@MyBatisRepository
public interface ProducRelationMapper extends BaseMapper<ProducRelation>{
    int deleteByPrimaryKey(String producrelationid);

    int insert(ProducRelation record);

    int insertSelective(ProducRelation record);

    ProducRelation selectByPrimaryKey(String producrelationid);

    int updateByPrimaryKeySelective(ProducRelation record);

    int updateByPrimaryKey(ProducRelation record);
}