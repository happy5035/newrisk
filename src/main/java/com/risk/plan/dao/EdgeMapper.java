package com.risk.plan.dao;

import java.util.List;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.Edge;
@MyBatisRepository
public interface EdgeMapper extends BaseMapper<Edge> {
    int deleteByPrimaryKey(String edgeid);
    
    int  DeleteByfirstAndsecond(String firstnodeid,String secondnodeid);

    int insert(Edge record);

    int insertSelective(Edge record);

    Edge selectByPrimaryKey(String edgeid);

    int updateByPrimaryKeySelective(Edge record);

    int updateByPrimaryKey(Edge record);
    
    List<String> findByNodeid(String nodeid);
    
    List<Edge> findByFirstNodeid(String firstnodeid);
    
    List<Edge> findBySecondNodeid(String secondnodeid);
}