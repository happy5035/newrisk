package com.risk.plan.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public interface BaseMapper<T> {
	
	public int deleteByPrimaryKey(Serializable id);

	public int insert(T record);

	public int insertSelective(T record);

	public T selectByPrimaryKey(Serializable id);
	
	public List<T> selectAll(Map<String, Object> params);

	public int updateByPrimaryKeySelective(T record);

	public int updateByPrimaryKey(T record);
	
	
	
	public List<T> findByParams(Map<String, Object> params);

	
	public int findByParamsCount(Map<String, Object> params);
	
	public List<T> findByKey(Map<String, Object> params);
	
	public int findByKeyCount(Map<String, Object> params);
}
