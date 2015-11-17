package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.Manufacturer;
@MyBatisRepository
public interface ManufacturerMapper extends BaseMapper<Manufacturer>{
    int deleteByPrimaryKey(String manufacturerid);

    int insert(Manufacturer record);

    int insertSelective(Manufacturer record);

    Manufacturer selectByPrimaryKey(String manufacturerid);

    int updateByPrimaryKeySelective(Manufacturer record);

    int updateByPrimaryKey(Manufacturer record);
}