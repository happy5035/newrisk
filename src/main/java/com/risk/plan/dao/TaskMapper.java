package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.Task;
@MyBatisRepository
public interface TaskMapper extends BaseMapper<Task>{
    int deleteByPrimaryKey(String taskid);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(String taskid);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);
}