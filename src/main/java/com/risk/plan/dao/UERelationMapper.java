package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.UERelation;

@MyBatisRepository
public interface UERelationMapper extends BaseMapper<UERelation> {
    int deleteByPrimaryKey(String uerelationid);

    int insert(UERelation record);

    int insertSelective(UERelation record);

    UERelation selectByPrimaryKey(String uerelationid);

    int updateByPrimaryKeySelective(UERelation record);

    int updateByPrimaryKey(UERelation record);
}