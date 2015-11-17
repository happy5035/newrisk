package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.WareHouse;
@MyBatisRepository
public interface WareHouseMapper extends BaseMapper<WareHouse> {
    int deleteByPrimaryKey(String wnodeid);

    int insert(WareHouse record);

    int insertSelective(WareHouse record);

    WareHouse selectByPrimaryKey(String wnodeid);

    int updateByPrimaryKeySelective(WareHouse record);

    int updateByPrimaryKey(WareHouse record);
}