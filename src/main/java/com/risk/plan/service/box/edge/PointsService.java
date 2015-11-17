package com.risk.plan.service.box.edge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.plan.common.BaseService;
import com.risk.plan.dao.PointsMapper;
import com.risk.plan.entity.Points;
@Service
public class PointsService extends BaseService<Points> {
	@Autowired
	PointsMapper pointsMapper;
	public List<Points> findByEdgeid(String edgeid){
		return pointsMapper.selectByEdgeId(edgeid);
	}
	public List<Points> findByELL(String edgeid ,String lat ,String lng){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("lat", lat);
		params.put("lng", lng);
		params.put("edgeid", edgeid);
		return pointsMapper.findByParams(params);
	}
}
