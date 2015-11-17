package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.Roles;
@MyBatisRepository
public interface RolesMapper extends BaseMapper<Roles>{
    int deleteByPrimaryKey(String roleid);

    int insert(Roles record);

    int insertSelective(Roles record);

    Roles selectByPrimaryKey(String roleid);

    int updateByPrimaryKeySelective(Roles record);

    int updateByPrimaryKey(Roles record);
}