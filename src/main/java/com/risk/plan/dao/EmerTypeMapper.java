package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.EmerType;
@MyBatisRepository
public interface EmerTypeMapper  extends BaseMapper<EmerType>{
    int deleteByPrimaryKey(String emertypeid);

    int insert(EmerType record);

    int insertSelective(EmerType record);

    EmerType selectByPrimaryKey(String emertypeid);

    int updateByPrimaryKeySelective(EmerType record);

    int updateByPrimaryKey(EmerType record);
}