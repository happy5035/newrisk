package com.risk.plan.dao;

import java.util.List;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.Popedem;

@MyBatisRepository
public interface PopedemMapper extends BaseMapper<Popedem>{
    int deleteByPrimaryKey(String popedemid);

    int insert(Popedem record);

    int insertSelective(Popedem record);

    Popedem selectByPrimaryKey(String popedemid);

    int updateByPrimaryKeySelective(Popedem record);

    int updateByPrimaryKey(Popedem record);
    
    List<Popedem> selectByRoleId(String roleid);
}