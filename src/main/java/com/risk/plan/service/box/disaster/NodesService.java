package com.risk.plan.service.box.disaster;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.plan.common.BaseService;
import com.risk.plan.dao.NodesMapper;
import com.risk.plan.entity.Nodes;
@Service
public class NodesService extends BaseService<Nodes> {
		@Autowired
		NodesMapper nodesMapper;
		public List<Nodes> findByUseridAndUserType(String userid,String usertype)
		{
				if(usertype.equals("0")){
					return nodesMapper.selectByUserid(userid);
				}
				else if(usertype.equals("1")){
					return nodesMapper.selectAll(null);
				}
				else
					return null;
		}
		
		public List<Nodes> findByEmerid(String emerid){
			if(emerid != null && emerid.length() >0){
				return nodesMapper.selectByEmerid(emerid);
			}
			return null;
			
		}

		public List<Nodes> findByUseridAndUserType(Map<String, Object> params) {
			String usertype=(String)params.get("usertype");
			String userid=(String)params.get("userid");
			if(usertype.equals("0")){
				return nodesMapper.selectByUserid(userid);
			}
			else if(usertype.equals("1")){
				return nodesMapper.selectAll(null);
			}
			else
				return null;
		}
}

