package com.risk.plan.service.box.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.plan.common.BaseService;
import com.risk.plan.dao.GoodsTypeMapper;
import com.risk.plan.entity.GoodsType;
@Service
public class GoodsTypeService extends BaseService<GoodsType> {
	@Autowired
	GoodsTypeMapper goodsTypeMapper;
	
	public List<String> findAllGoodsTypeName(){
		List<String> goodsTypenames=new ArrayList<String>();
		List<GoodsType> goodsTypes=goodsTypeMapper.selectAll(null);
		for (GoodsType goodsType : goodsTypes) {
			if(!goodsTypenames.contains(goodsType.getGoodstypename())){
				goodsTypenames.add(goodsType.getGoodstypename());
			}
		}
		return goodsTypenames;
	}
	
	public List<GoodsType> findBygoodsTypename(String goodstypename){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("goodstypename", goodstypename);
		List<GoodsType> goodsTypes=goodsTypeMapper.findByParams(params);
		
		return goodsTypes;
	}
}
