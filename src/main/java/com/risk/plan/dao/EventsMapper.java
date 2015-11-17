package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.Events;
@MyBatisRepository
public interface EventsMapper extends BaseMapper<Events>{
    int deleteByPrimaryKey(String eventid);

    int insert(Events record);

    int insertSelective(Events record);

    Events selectByPrimaryKey(String eventid);

    int updateByPrimaryKeySelective(Events record);

    int updateByPrimaryKey(Events record);
}