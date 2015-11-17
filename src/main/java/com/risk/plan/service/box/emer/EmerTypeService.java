package com.risk.plan.service.box.emer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.plan.common.BaseService;
import com.risk.plan.dao.EmerTypeMapper;
import com.risk.plan.entity.EmerType;
@Service
public class EmerTypeService extends BaseService<EmerType> {
	@Autowired
	EmerTypeMapper emerTypeMapper;
	public List<String> selectAllEmerTypeNames(){
		List<EmerType> emerTypes=emerTypeMapper.selectAll(null);
		List<String>emertypenames=new ArrayList<String>();
		for (EmerType emerType : emerTypes) {
			if(!emertypenames.contains(emerType.getEmertypename()))
			{
				emertypenames.add(emerType.getEmertypename());
			}
		}
		return emertypenames;
	}
}
