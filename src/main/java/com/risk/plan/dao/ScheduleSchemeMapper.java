package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.ScheduleScheme;
@MyBatisRepository
public interface ScheduleSchemeMapper extends BaseMapper<ScheduleScheme>{
    int deleteByPrimaryKey(String schemeid);

    int insert(ScheduleScheme record);

    int insertSelective(ScheduleScheme record);

    ScheduleScheme selectByPrimaryKey(String schemeid);

    int updateByPrimaryKeySelective(ScheduleScheme record);

    int updateByPrimaryKey(ScheduleScheme record);
}