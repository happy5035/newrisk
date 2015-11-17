package com.risk.plan.service.box.edge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.plan.common.BaseService;
import com.risk.plan.dao.PathMapper;
import com.risk.plan.entity.Path;
@Service
public class PathService extends BaseService<Path> {
	@Autowired
	PathMapper pathMapper;
	public List<Path> findByTaskId(String taskid){
		return pathMapper.findByTaskId(taskid);
	}
	
}
