package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.VirtualNode;
@MyBatisRepository
public interface VirtualNodeMapper extends BaseMapper<VirtualNode>{
    int deleteByPrimaryKey(String vnodeid);

    int insert(VirtualNode record);

    int insertSelective(VirtualNode record);

    VirtualNode selectByPrimaryKey(String vnodeid);

    int updateByPrimaryKeySelective(VirtualNode record);

    int updateByPrimaryKey(VirtualNode record);
}