package com.risk.plan.service.box.transportation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.plan.common.BaseService;
import com.risk.plan.dao.EmerTypeMapper;
import com.risk.plan.dao.TranModelMapper;
import com.risk.plan.entity.EmerType;
import com.risk.plan.entity.Emergency;
import com.risk.plan.entity.TranModel;

@Service
public class TranModelService extends BaseService<TranModel>{
	@Autowired
	TranModelMapper tranmodelMapper;
	public List<String> selectAllTransType(){
		List<TranModel> tranmodels=tranmodelMapper.selectAll(null);
		List<String>transtypes=new ArrayList<String>();
		for (TranModel tranmodel :tranmodels) {
			if(!transtypes.contains(tranmodel.getTranstype()))
			{
				transtypes.add(tranmodel.getTranstype());
			}
		}
		return transtypes;
	} 

	public List<TranModel> selectByTransType(Map<String, Object> params)
	{
		return tranmodelMapper.findByTranstype(params);
	}
}

