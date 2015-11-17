package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.TransLive;
@MyBatisRepository
public interface TransLiveMapper extends BaseMapper<TransLive>{
    int deleteByPrimaryKey(String transliveid);

    int insert(TransLive record);

    int insertSelective(TransLive record);

    TransLive selectByPrimaryKey(String transliveid);

    int updateByPrimaryKeySelective(TransLive record);

    int updateByPrimaryKey(TransLive record);
}