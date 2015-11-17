package com.risk.plan.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<T> {
	
	@Autowired
	BaseMapper<T> baseMapper;

	
	public static int DEFAULT_PAGESIZE = 20;
	
	public int deleteByPrimaryKey(Serializable id) {
		return baseMapper.deleteByPrimaryKey(id);
	}
	
	public int insert(T record) {
		return baseMapper.insert(record);
	}
	
	public int insertSelective(T record) {
		return baseMapper.insertSelective(record);
	}
	
	public T selectByPrimaryKey(Serializable id) {
		return baseMapper.selectByPrimaryKey(id);
	}
	
	public List<T> selectAll() {
		return baseMapper.selectAll(null);
	}
	
	public List<T> selectAll(String orderCause, String orderSort) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderCause", orderCause);
		params.put("orderSort", orderSort);
		return baseMapper.selectAll(params);
	}
	
	public int updateByPrimaryKeySelective(T record) {
		return baseMapper.updateByPrimaryKeySelective(record);
	}
	
	public int updateByPrimaryKey(T record) {
		return baseMapper.updateByPrimaryKey(record);
	}
	
	/*public List<T>findByTranstype(Map<String, Object> params) {
		return baseMapper.findByTranstype(params);
	}
	*/
	public List<T> findByParams(Map<String, Object> params) {
		return baseMapper.findByParams(params);
	}
	
	public List<T> findByParams(Map<String, Object> params, String orderCause, String orderSort) {
		params.put("orderCause", orderCause);
		params.put("orderSort", orderSort);
		return baseMapper.findByParams(params);
	}
	
	public int findByParamsCount(Map<String, Object> params) {
		return baseMapper.findByParamsCount(params);
	}
				
	public Map<String, Object> pagedByParams(Map<String, Object> params, int page, int rows) {
		 //当前页  
        int intPage = page == 0 ? 1 : page;  
        //每页显示条数  
        int pageSize = rows == 0 ? DEFAULT_PAGESIZE : rows;  
        //每页的开始记录  
        int startNum = (intPage-1)*pageSize;
        params.put("pageSize", pageSize);
	    params.put("startNum", startNum);
		List<T> data = baseMapper.findByParams(params);
		int total = baseMapper.findByParamsCount(params);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", total);
		resultMap.put("rows", data);
		return resultMap;
	}
	
	public Map<String, Object> pagedByParams(Map<String, Object> params, int page, int rows, String sort, String order) {
		//当前页  
	    int intPage = page == 0 ? 1 : page;  
	    //每页显示条数  
	    int pageSize = rows == 0 ? DEFAULT_PAGESIZE : rows;  
        //每页的开始记录  
        int startNum = (intPage-1)*pageSize;
		 params.put("pageSize", pageSize);
		 params.put("startNum", startNum);
		 params.put("sortName", sort);
		 params.put("sortOrder", order);
		 List<T> data = baseMapper.findByParams(params);
		 int total = baseMapper.findByParamsCount(params);
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 resultMap.put("total", total);
		 resultMap.put("rows", data);
		return resultMap;
	}
	
	public Map<String, Object> pagedByParams(Page page) {
		//当前页  
		int intPage = page.getPage() == 0 ? 1 : page.getPage();  
		//每页显示条数  
		int pageSize = page.getRows() == 0 ? DEFAULT_PAGESIZE : page.getRows();  
		//每页的开始记录  
		int startNum = (intPage-1)*pageSize;
		Map<String, Object> params = page.getParams();
		if (null == params ) {
			params = new HashMap<String, Object>();
		}
		 params.put("pageSize", pageSize);
		 params.put("startNum", startNum);
		 params.put("sortName",page.getSort());
		 params.put("sortOrder",  page.getOrder());
		 int total = baseMapper.findByParamsCount(params);
		 List<T> data = baseMapper.findByParams(params);
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 resultMap.put("total", total);
		 resultMap.put("rows", data);
		return resultMap;
	}
	
	public boolean isInexistent(String ckey, String cvalue, String pkey, String pvalue) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ckey", ckey);
		params.put("cvalue", cvalue== ""?null:cvalue.trim());
		params.put("pkey", pkey);
		params.put("pvalue", pvalue == ""?null:pvalue.trim());
		int result = baseMapper.findByKeyCount(params);
		if (result == 0) {
			return true;
		}
		return false;
	}
	
}
