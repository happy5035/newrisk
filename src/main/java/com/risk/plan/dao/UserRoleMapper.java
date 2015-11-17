package com.risk.plan.dao;

import java.util.List;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.UserRole;
@MyBatisRepository
public interface UserRoleMapper extends BaseMapper<UserRole>{
    int deleteByPrimaryKey(String crid);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(String crid);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
    List<UserRole> selectByUserId (String userid);
}