package com.risk.plan.service.box.edge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.plan.common.BaseService;
import com.risk.plan.dao.TaskMapper;
import com.risk.plan.entity.Task;
@Service
public class TaskService extends BaseService<Task> {
	@Autowired
	TaskMapper taskMapper;
	public List<Task> findByGeneID(String geneid){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("geneid", geneid);
		return taskMapper.findByParams(params);
	}

}
