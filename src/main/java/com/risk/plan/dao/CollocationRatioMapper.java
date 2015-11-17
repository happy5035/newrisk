package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.CollocationRatio;
@MyBatisRepository
public interface CollocationRatioMapper extends BaseMapper<CollocationRatio> {
    int deleteByPrimaryKey(String colloratioid);

    int insert(CollocationRatio record);

    int insertSelective(CollocationRatio record);

    CollocationRatio selectByPrimaryKey(String colloratioid);

    int updateByPrimaryKeySelective(CollocationRatio record);

    int updateByPrimaryKey(CollocationRatio record);
}