package com.risk.plan.dao;

import java.util.List;
import java.util.Map;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.Emergency;
import com.risk.plan.entity.Sub;
@MyBatisRepository
public interface SubMapper extends BaseMapper<Sub>{
    int deleteByPrimaryKey(String subid);

    int insert(Sub record);

    int insertSelective(Sub record);

    Sub selectByPrimaryKey(String subid);

    int updateByPrimaryKeySelective(Sub record);

    int updateByPrimaryKey(Sub record);
    
    
    public List<Sub> selectByEmerId(Map<String, Object> params);
    
    public List<Sub> findArSubBySubids(Map<String,Object> map);
}