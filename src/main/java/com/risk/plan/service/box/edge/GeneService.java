package com.risk.plan.service.box.edge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.plan.common.BaseService;
import com.risk.plan.dao.GeneMapper;
import com.risk.plan.entity.Gene;
@Service
public class GeneService extends BaseService<Gene> {
	@Autowired
	GeneMapper geneMapper;
	public List<Gene> findByTransIDandSchemeID(String transid,String schemeid){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("schemeid", schemeid);
		params.put("transid", transid);
		return geneMapper.findByParams(params);
		
	}
	public void deleteByTransid(String transid) {
		// TODO Auto-generated method stub
		 geneMapper.deleteByTransid(transid);
	}
	
	public List<Gene> selectByTransid(String transid){
		return geneMapper.selectByTransid(transid);
	}
}
