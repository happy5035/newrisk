package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.RealCollocation;
@MyBatisRepository
public interface RealCollocationMapper extends BaseMapper<RealCollocation>{
    int deleteByPrimaryKey(String realcollocationid);

    int insert(RealCollocation record);

    int insertSelective(RealCollocation record);

    RealCollocation selectByPrimaryKey(String realcollocationid);

    int updateByPrimaryKeySelective(RealCollocation record);

    int updateByPrimaryKey(RealCollocation record);
}