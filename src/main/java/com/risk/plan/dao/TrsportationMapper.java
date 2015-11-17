package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.Trsportation;
@MyBatisRepository
public interface TrsportationMapper extends BaseMapper<Trsportation>{
    int deleteByPrimaryKey(String transid);

    int insert(Trsportation record);

    int insertSelective(Trsportation record);

    Trsportation selectByPrimaryKey(String transid);

    int updateByPrimaryKeySelective(Trsportation record);

    int updateByPrimaryKey(Trsportation record);
}