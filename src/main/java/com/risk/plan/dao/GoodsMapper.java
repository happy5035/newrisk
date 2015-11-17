package com.risk.plan.dao;


import com.risk.plan.common.BaseMapper;
import com.risk.plan.common.MyBatisRepository;
import com.risk.plan.entity.Goods;
@MyBatisRepository
public interface GoodsMapper extends BaseMapper<Goods> {
    int deleteByPrimaryKey(String goodsid);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(String goodsid);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
}