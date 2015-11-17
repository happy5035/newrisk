package com.risk.plan.dao;

import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.GoodsType;
@MyBatisRepository
public interface GoodsTypeMapper extends BaseMapper<GoodsType> {
    int deleteByPrimaryKey(String goodstypeid);

    int insert(GoodsType record);

    int insertSelective(GoodsType record);

    GoodsType selectByPrimaryKey(String goodstypeid);

    int updateByPrimaryKeySelective(GoodsType record);

    int updateByPrimaryKey(GoodsType record);
}