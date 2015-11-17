package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.GoodsRelation;
@MyBatisRepository
public interface GoodsRelationMapper extends BaseMapper<GoodsRelation> {
    int deleteByPrimaryKey(String goodsreid);

    int insert(GoodsRelation record);

    int insertSelective(GoodsRelation record);

    GoodsRelation selectByPrimaryKey(String goodsreid);

    int updateByPrimaryKeySelective(GoodsRelation record);

    int updateByPrimaryKey(GoodsRelation record);
}