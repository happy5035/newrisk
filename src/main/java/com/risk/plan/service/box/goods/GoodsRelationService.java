package com.risk.plan.service.box.goods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.plan.common.BaseService;
import com.risk.plan.dao.GoodsRelationMapper;
import com.risk.plan.entity.GoodsRelation;
@Service
public class GoodsRelationService extends BaseService<GoodsRelation> {
	@Autowired
	GoodsRelationMapper goodsRelationMapper;
	public List<GoodsRelation> findByUserParam(Map<String, Object> params ,String userid,String usertype){
		
		if(userid != null && usertype != null){
			params.put("userid", userid);
			params.put("usertype", usertype);
			return goodsRelationMapper.findByParams(params);
		}
		else {
			return new ArrayList<GoodsRelation>();
		}
	}
	
}
