package com.risk.plan.service.box.sub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.plan.common.BaseService;
import com.risk.plan.dao.EdgeMapper;
import com.risk.plan.dao.SubMapper;
import com.risk.plan.entity.Edge;
import com.risk.plan.entity.Sub;

@Service	
public class SubService extends BaseService<Sub>{
	@Autowired
	SubMapper subMapper;
	@Autowired
	EdgeMapper edgeMapper;
	public List<Sub> selectByEmerId(Map<String, Object> params){
		return subMapper.selectByEmerId(params);
			
}
	public List<String> findPreSubs(String subid){
		
		List<String> subs=new ArrayList<String>();
		
		if(subid==null) 
				return null;
		List<Edge> edges=edgeMapper.findBySecondNodeid(subid);  
		if(edges == null) 
				return null;
		for (Edge edge : edges) {
			if(edge.getFirstnodeid()!=null){
				String subn=edge.getFirstnodeid();
				 subs.add(subn);
				 List<String> subns=findPreSubs(subn);
				 if(subns != null && subns.size()>0) 
					 subs.addAll(subns);
			}
		}
		return subs;
	}
	
	public List<Sub> findPreSubo(String subid){
		if(subid == null) return null;
		List<String> subs=findPreSubs(subid);
		subs.add(subid);
		Sub sub=subMapper.selectByPrimaryKey(subid);
		List<Sub> subos=new ArrayList<Sub>();
		Map<String, Object> map=new HashMap<String, Object>();
		if(sub!=null){
			String emerid=sub.getEmerId();
			if(emerid!=null){
				map.put("emerid", emerid);
				if(subs != null && subs.size()>0){
					map.put("list", subs);
					subos=subMapper.findArSubBySubids(map);
					
				}
				else{
					subos=subMapper.selectByEmerId(map);
				}
				return subos;
			}
		}
		
		return null;
	}
}
