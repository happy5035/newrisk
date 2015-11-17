package com.risk.plan.dao;

import java.util.List;
import java.util.Map;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.TranModel;
@MyBatisRepository
public interface TranModelMapper<T> extends BaseMapper<TranModel>{
    int deleteByPrimaryKey(String tranmodelid);

    int insert(TranModel record);

    int insertSelective(TranModel record);

    TranModel selectByPrimaryKey(String tranmodelid);

    int updateByPrimaryKeySelective(TranModel record);

    
    public List<TranModel> findByTranstype(Map<String, Object> params);
    int updateByPrimaryKey(TranModel record);
}