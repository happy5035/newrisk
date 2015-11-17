package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.CrossNode;
@MyBatisRepository
public interface CrossNodeMapper extends BaseMapper<CrossNode> {
    int deleteByPrimaryKey(String cnodeid);

    int insert(CrossNode record);

    int insertSelective(CrossNode record);

    CrossNode selectByPrimaryKey(String cnodeid);

    int updateByPrimaryKeySelective(CrossNode record);

    int updateByPrimaryKey(CrossNode record);
}