package com.risk.plan.dao;

import java.util.List;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.Edge;
@MyBatisRepository
public interface EdgeMapper extends BaseMapper<Edge> {
    int deleteByPrimaryKey(String edgeid);

    int insert(Edge record);

    int insertSelective(Edge record);

    Edge selectByPrimaryKey(String edgeid);

    int updateByPrimaryKeySelective(Edge record);

    int updateByPrimaryKey(Edge record);
    
    List<String> findByNodeid(String nodeid);
}