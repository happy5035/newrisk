package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.ColloRatioRel;
@MyBatisRepository
public interface ColloRatioRelMapper extends BaseMapper<ColloRatioRel> {
    int deleteByPrimaryKey(String colloratiorelid);

    int insert(ColloRatioRel record);

    int insertSelective(ColloRatioRel record);

    ColloRatioRel selectByPrimaryKey(String colloratiorelid);

    int updateByPrimaryKeySelective(ColloRatioRel record);

    int updateByPrimaryKey(ColloRatioRel record);
}