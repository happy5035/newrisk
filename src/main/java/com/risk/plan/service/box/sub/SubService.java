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
import com.risk.plan.exception.LoopExistException;

@Service	
public class SubService extends BaseService<Sub>{
	
	private String subid;
	private boolean isLoop=false;
	
	@Autowired
	SubMapper subMapper;
	@Autowired
	EdgeMapper edgeMapper;
	
	public List<Sub> selectByEmerId(Map<String, Object> params){
		return subMapper.selectByEmerId(params);
			
}
	/**
	 * 	
	* @Title: findPreSubs 
	* @Description: 迭代遍历
	* @param subid
	* @return List<String>    返回类型
	 */
	public List<String> findPreSubs(String subid){
		if(isLoop) return null;
		List<String> subs=new ArrayList<String>();
		
		if(subid==null) 
				return null;
		List<Edge> edges=edgeMapper.findBySecondNodeid(subid);  
		if(edges == null) 
				return null;
		for (Edge edge : edges) {
			if(edge.getFirstnodeid()!=null){
				String subn=edge.getFirstnodeid();
				if(this.subid.trim() !=null)
					if(subn.trim().equals(this.subid.trim()))
						{
							isLoop=true;
							return null;
						}
				 subs.add(subn);
				 List<String> subns=findPreSubs(subn);
				 if(subns != null && subns.size()>0) 
				 {
					 if(subns.contains(this.subid))
					 {
						 isLoop=true;
						 return null;
					 }
					 subs.addAll(subns);
				 }
			}
		}
		return subs;
	}
	/**
	 * 
	* @Title: findPreSubo 
	* @Description: 通过subid查找前向节点并检测是否成环
	* @param subid
	* @return
	* @throws LoopExistException List<Sub>    返回类型
	 */
	public List<Sub> findPreSubo(String subid) throws LoopExistException{
		if(subid == null) return null;
		this.subid=subid;
		this.isLoop=false;
		List<String> subs=findPreSubs(subid);
		if(this.isLoop) throw new LoopExistException("已经形成环路");
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
