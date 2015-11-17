package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.Area;
@MyBatisRepository
public interface AreaMapper  extends BaseMapper<Area>{
    int deleteByPrimaryKey(String areaid);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(String areaid);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
}