package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.RealCollo;
@MyBatisRepository
public interface RealColloMapper extends BaseMapper<RealCollo>{
    int deleteByPrimaryKey(String realcolloid);

    int insert(RealCollo record);

    int insertSelective(RealCollo record);

    RealCollo selectByPrimaryKey(String realcolloid);

    int updateByPrimaryKeySelective(RealCollo record);

    int updateByPrimaryKey(RealCollo record);
}