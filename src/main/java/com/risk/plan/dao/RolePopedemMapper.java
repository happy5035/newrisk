package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.RolePopedem;
@MyBatisRepository
public interface RolePopedemMapper extends BaseMapper<RolePopedem>{
    int deleteByPrimaryKey(String rpid);

    int insert(RolePopedem record);

    int insertSelective(RolePopedem record);

    RolePopedem selectByPrimaryKey(String rpid);

    int updateByPrimaryKeySelective(RolePopedem record);

    int updateByPrimaryKey(RolePopedem record);
}