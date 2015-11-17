package com.risk.plan.service.box.emer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risk.plan.common.BaseService;
import com.risk.plan.dao.EmergencyMapper;
import com.risk.plan.entity.EmerType;
import com.risk.plan.entity.Emergency;
@Service
public class EmergencyService extends BaseService<Emergency> {

		@Autowired
		EmergencyMapper emergencyMapper;
		
		public List<Emergency> selectByUserid(String userid){
			return emergencyMapper.selectByUserid(userid);
		}
		
		public List<Emergency> selectByEmerTypeName(Map<String, Object> params){
			String usertype=(String)params.get("usertype");
			String userid=(String)params.get("userid");
			if(usertype != null && usertype.length()> 0)
			{
				if(usertype.equals("1")){
					if(userid !=null)
						params.remove("userid");
					return emergencyMapper.selectByEmerTypeName(params);
				}
				else 
					return emergencyMapper.selectByEmerTypeName(params);
			}
			else return null;
		}
		public List<Emergency> selectByEmerName(Map<String, Object> params){
			String usertype=(String)params.get("usertype");
			String userid=(String)params.get("userid");
			if(usertype != null && usertype.length()> 0)
			{
				if(usertype.equals("1")){
					if(userid !=null)
						params.remove("userid");
					return emergencyMapper.selectByEmerName(params);
				}
				else 
					return emergencyMapper.selectByEmerName(params);
			}
			else return null;
		}
		
		public List<Emergency> selectByEmerId(Map<String, Object> params){
			String usertype=(String)params.get("usertype");
			String userid=(String)params.get("userid");
			if(usertype != null && usertype.length()> 0)
			{
				if(usertype.equals("1")){
					if(userid !=null)
						params.remove("userid");
					return emergencyMapper.selectByEmerId(params);
				}
				else 
					return emergencyMapper.selectByEmerId(params);
			}
			else return null;
		}
		
		public List<String> selectAllEmerNames(){
			List<Emergency> emergencys=emergencyMapper.selectAll(null);
			List<String>emernames=new ArrayList<String>();
			for (Emergency emergency : emergencys) {
				if(!emernames.contains(emergency.getEmername()))
				{
					emernames.add(emergency.getEmername());
				}
			}
			return emernames;
		}
}
