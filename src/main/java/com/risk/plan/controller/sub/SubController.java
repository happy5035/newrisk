package com.risk.plan.controller.sub;




import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.risk.plan.common.PageBean;
import com.risk.plan.entity.Area;
import com.risk.plan.entity.DisasterNode;
import com.risk.plan.entity.ENRelation;
import com.risk.plan.entity.EmerType;
import com.risk.plan.entity.Emergency;
import com.risk.plan.entity.Nodes;
import com.risk.plan.entity.Users;
import com.risk.plan.service.box.disaster.AreaService;
import com.risk.plan.service.box.disaster.DisasterNodesService;
import com.risk.plan.service.box.disaster.ENrelationService;
import com.risk.plan.service.box.disaster.NodesService;
import com.risk.plan.service.box.emer.EmerTypeService;
import com.risk.plan.service.box.emer.EmergencyService;
import com.risk.plan.service.box.goods.GoodsTypeService;
import com.risk.plan.service.box.sub.SubService;
import com.risk.plan.util.Identities;


@Controller
public class SubController {
	@Autowired
	SubService subService;
	@Autowired
	EmerTypeService emerTypeService;
	@Autowired
	EmergencyService emergencyService;
	@Autowired
	GoodsTypeService goodsTypeService;
	@Autowired
	DisasterNodesService disasterNodesService;
	@Autowired
	ENrelationService enRelationService;
	
	@Autowired
	ENRelation enRelaion;
	@Autowired
	EmerType emertype;
	
	
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	

	@RequestMapping("/gotoAddSub")
	public String gotoAddSub(ModelMap modelmap,String subid){
		Map<String, Object> params=new HashMap<String, Object>();
		
		List<EmerType> list= emerTypeService.selectAll();
		List<String>emertypenames=new ArrayList<String>();
		List<EmerType> emerTypelist=new ArrayList<EmerType>();
		for (EmerType emerType : list) {
			if(!emertypenames.contains(emerType.getEmertypename()) ){
				emertypenames.add(emerType.getEmertypename());
				emerTypelist.add(emerType);
			}
		}
		Users user=(Users)request.getSession().getAttribute("user");
		String userid=user.getUserid();
		Map<String, Object> params1=new HashMap<String, Object>();
		params1.put("userid", userid);
		
	/*	List<String>emernames=new ArrayList<String>();
		List<Emergency> emergencylist=emergencyService.selectAll();
		for (Emergency emergency : emergencylist) {
			if(!emernames.contains(emergency.getEmername()) ){
				emernames.add(emergency.getEmername());
				emerlist.add(emerType);
			}
		}*/
		
	/*	if(emertypenames.size() >0)
		{
			modelmap.put("emergencylist", emergencylist);
		}else {
			String listStrng="No Option";
			modelmap.put("emergencylist", listStrng);
		}*/
		modelmap.put("emerTypeName", emertypenames);
		
		
			
	
		
		return "WebRoot/DisasterNode/addDisasterNode";
	}
}
	/*@RequestMapping("/saveDisasterNode")
	public String saveDisasterNode(ModelMap modelmap,String emerTypeName,String emerId ,String disasterLevel,Nodes node
			,String roadid,String disastername,String note,DisasterNode disasterNode){
		int resault=0;
		String nodeid=Identities.uuid2();
		node.setNodeid(nodeid);
		node.setAreaid(roadid);
		node.setNodename(disastername);
		node.setNodetype("D");
		node.setNotes(note);
		resault= nodesService.insertSelective(node);
		disasterNode.setDisasterlevel(disasterLevel);
		disasterNode.setDnodeid(nodeid);
		resault*= disasterNodesService.insertSelective(disasterNode);
		enRelaion.setEnrelationid(Identities.uuid2());
		enRelaion.setEmerid(emerId);
		enRelaion.setNodeid(nodeid);
		resault*= enRelationService.insertSelective(enRelaion);
		if(resault >0)
			return "WebRoot/DisasterNode/addSuccess";
		else
			return "WebRoot/DisasterNode/addfailed";
	}*/
	
	
	/*@RequestMapping("/GotoSearchDisasterNode")
	public String GotoSearchDisasterNode(ModelMap modelmap){
		List<EmerType> list= emerTypeService.selectAll();
		List<String>emertypenames=new ArrayList<String>();
		List<EmerType> emerTypelist=new ArrayList<EmerType>();
		for (EmerType emerType : list) {
			if(!emertypenames.contains(emerType.getEmertypename()) ){
				emertypenames.add(emerType.getEmertypename());
				emerTypelist.add(emerType);
			}
		}
		Users user=(Users)request.getSession().getAttribute("user");
		String userid=user.getUserid();
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("userid", userid);
		params.put("emertypename", "水灾");
		List<Emergency> emergencylist=emergencyService.selectByEmerTypeName(params);
		if(emertypenames.size() >0)
		{
			modelmap.put("emergencylist", emergencylist);
		}else {
			String listStrng="No Option";
			modelmap.put("emergencylist", listStrng);
		}
		modelmap.put("emertypename", emertypenames);
		return "WebRoot/DisasterNode/listDisasterNode";
	}
	
	
	*//** 
	* @Title: SearchDisasterNode 
	* @Description: 搜索风险清单项
	* @param modelmap
	* @param page
	* @param disasterName
	* @param disasterNo
	* @param principal
	* @param disasterLevel
	* @param emerId
	* @param emerTypeName
	* @return
	* @throws IOException String    返回类型    
	*//*
	@RequestMapping("/SearchDisasterNode")
	public String SearchDisasterNode(ModelMap modelmap,String page,String disasterName,
			String disasterNo,String principal,String disasterLevel,String  emerId,String emerTypeName) throws IOException{
		Users user=(Users)request.getSession().getAttribute("user");
		String userid=user.getUserid();
		StringBuffer url=new StringBuffer();
	    url.append(request.getContextPath()+"/SearchDisasterNode?method=SearchDisasterNode");
	    
	    List<EmerType> list= emerTypeService.selectAll();
		List<String>emertypenames=new ArrayList<String>();
		List<EmerType> emerTypelist=new ArrayList<EmerType>();
		for (EmerType emerType : list) {
			if(!emertypenames.contains(emerType.getEmertypename()) ){
				emertypenames.add(emerType.getEmertypename());
				emerTypelist.add(emerType);
			}
		}
		Map<String, Object> params1=new HashMap<String, Object>();
		params1.put("userid", userid);
		params1.put("emertypename", emerTypeName);
		List<Emergency> emergencylist=emergencyService.selectByEmerTypeName(params1);
		if(emertypenames.size() >0)
		{
			modelmap.put("emergencylist", emergencylist);
		}else {
			String listStrng="No Option";
			modelmap.put("emergencylist", listStrng);
		}
		modelmap.put("emerTypeName", emertypenames);
		
	    
	    int current=1;
		if (page != null && !page.trim().equals("")) {
			current=Integer.parseInt(page);
		}
			int first=0;
			int pageSize=Integer.parseInt(String.valueOf(PageBean.pagecount));
			if(current!=0){
				first=(current-1)*pageSize;
			}
			int startNum = first;
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("sortName", "emerid");
	        params.put("pageSize", pageSize);
		    params.put("startNum", startNum);
		    if(disasterName != null && !"".equals(disasterName))
	    	 {
	    		disasterName=URLDecoder.decode(disasterName, "UTF-8");
	    		params.put("disastername", "%"+disasterName+"%"); 
	    		 url.append("&disasterName="+disasterName);
	    	 }
		    if(disasterNo != null && !"".equals(disasterNo))
	    	 {
		    	disasterNo=URLDecoder.decode(disasterNo, "UTF-8");
	    		params.put("disasterno", "%"+disasterNo+"%"); 
	    		 url.append("&disasterNo="+disasterNo);
	    	 } 
		    if(principal != null && !"".equals(principal))
	    	 {
		    	principal=URLDecoder.decode(principal, "UTF-8");
	    		params.put("principal", "%"+principal+"%"); 
	    		 url.append("&principal="+principal);
	    	 }
		    if(disasterLevel != null && !"".equals(disasterLevel))
	    	 {
		    	disasterLevel=URLDecoder.decode(disasterLevel, "UTF-8");
	    		params.put("disasterlevel", "%"+disasterLevel+"%"); 
	    		 url.append("&disasterLevel="+disasterLevel);
	    	 }
		    
		    if(emerId != null && !"".equals(emerId))
	    	 {        
		    	params.put("emerid", emerId);
	    		 url.append("&emerId="+emerId);  //翻页的时候用的，拼接url！
	    	 }
		    modelmap.put("disasterNo",disasterNo);
		    modelmap.put("disasterLevel", disasterLevel);
		    modelmap.put("emerId", emerId);
		    Emergency emergency=emergencyService.selectByPrimaryKey(emerId);
		    modelmap.put("emergency", emergency);
			
		    List<ENRelation> enRelationlist=enRelationService.findByParams(params);
		    if(enRelationlist.size()>0 && enRelationlist !=null)
		    {
		    	ENRelation enrelation= enRelationlist.get(0);
			    EmerType et=enrelation.getEmertype();
			    modelmap.put("emerType", et);
		    }else{
		    	emertype.setEmertypename(emerTypeName);
			    modelmap.put("emerType", emertype);
		    }
		    int num=enRelationService.findByParamsCount(params);
			PageBean PageBean=new PageBean(current,pageSize,num,enRelationlist); 
			PageBean.setUrl(url.toString());
			modelmap.put("pageBean", PageBean);
			modelmap.put("Pagelist", PageBean.getList());
		
		return "WebRoot/DisasterNode/listDisasterNode";
	}
	
	*//** 
	* @Title: DeleteDisasterNode 
	* @Description:		 删除风险清单项
	* @param modelmap
	* @return String    返回类型    
	*//*
	@RequestMapping("/DeleteDisasterNode")
	public String DeleteDisasterNode(ModelMap modelmap,Emergency emergency,ENRelation enRelation,DisasterNode disasterNode,Nodes node){
		int resault=0;
		disasterNode.setDnodeid(node.getNodeid());
		resault=enRelationService.deleteByPrimaryKey(enRelation.getEnrelationid());
		resault*=disasterNodesService.deleteByPrimaryKey(disasterNode.getDnodeid());
		resault*=nodesService.deleteByPrimaryKey(node.getNodeid());
		if(resault >0)
			return "redirect:GotoSearchDisasterNode";
		else
			return "WebRoot/DisasterNode/addfailed";
	}
	
	*//** 
	* @Title: EditDisasterNode 
	* @Description: 编辑风险清单项
	* @param modelmap
	* @return String    返回类型    
	*//*
	@RequestMapping("/EditDisasterNode")
	public String EditDisasterNode(ModelMap modelmap,String nodeId,String enrelationId){
		String id=nodeId;
		String enrelaitonid=enrelationId;
		Users user=(Users)request.getSession().getAttribute("user");
		String userid=user.getUserid();
		DisasterNode diastserNode=disasterNodesService.selectByPrimaryKey(id);
		ENRelation enRelaiton=enRelationService.selectByPrimaryKey(enrelaitonid);
		Emergency emergency=emergencyService.selectByPrimaryKey(enRelaiton.getEmerid());
		Nodes node=nodesService.selectByPrimaryKey(id);
		modelmap.put("disasterNode", diastserNode );
		modelmap.put("emergency", emergency );
		modelmap.put("enrelationid",  enrelaitonid);
		modelmap.put("nodes",  node);
		
		//获取风险类型名称
		List<String> emerTypeNames=emerTypeService.selectAllEmerTypeNames();
		//获取用户权限下的风险类型对应的风险
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("emertypename", emergency.getEmertype().getEmertypename());
		params.put("userid", userid );
		List<Emergency> emergencyList=emergencyService.selectByEmerTypeName(params);
		modelmap.put("emerTypeName", emerTypeNames );
		modelmap.put("emergencylist",  emergencyList);
		
		//区域处理
		String areaid=node.getAreaid();
		if(areaid !=null)
		{
			Area area3=areaService.selectByPrimaryKey(areaid);
			params.put("parareaid", area3.getParareaid());
			List<Area> roadList=areaService.findByParams(params);
			Area area2=areaService.selectByPrimaryKey(area3.getParareaid());
			params.put("parareaid", area2.getParareaid());
			List<Area> streetList=areaService.findByParams(params);
			Area area1=areaService.selectByPrimaryKey(area2.getParareaid());
			params.put("parareaid", area1.getParareaid());
			List<Area> areas=areaService.findByParams(params);
			modelmap.put("road", area3 ); //下面几行是该风险清单项所在区、市、省；所有省、市、区
			modelmap.put("street", area2 );
			modelmap.put("area", area1 );
			modelmap.put("areas", areas );
			modelmap.put("roadlist", roadList );
			modelmap.put("streetlist", streetList );
		}
		
		return "WebRoot/DisasterNode/editDisasterNode"; 
	}
	
	*//** 
	* @Title: UpdateDisasterNode 
	* @Description: 更新风险清单项
	* @param modelmap
	* @param emerTypeName
	* @param emerId
	* @param disasterLevel
	* @param node
	* @param roadid
	* @param disastername
	* @param note
	* @param disasterNode
	* @return String    返回类型    
	*//*
	@RequestMapping("/UpdateDisasterNode")
	public String UpdateDisasterNode(ModelMap modelmap,String emerTypeName,String dnodeid ,String emerId ,
			String disasterLevel,Nodes node
			,String roadid,String disastername,String note,DisasterNode disasterNode,String enrelationid){
		int resault=0;
		node.setNodeid(dnodeid);
		node.setAreaid(roadid);
		node.setNodename(disastername);
		node.setNodetype("D");
		node.setNotes(note);
		resault= nodesService.updateByPrimaryKeySelective(node);
		disasterNode.setDisasterlevel(disasterLevel);
		resault*= disasterNodesService.updateByPrimaryKeySelective(disasterNode);
		enRelaion.setEnrelationid(enrelationid);
		enRelaion.setEmerid(emerId);
		enRelaion.setNodeid(dnodeid);
		resault*= enRelationService.updateByPrimaryKeySelective(enRelaion);
		if(resault >0)
			return "redirect:GotoSearchDisasterNode";
		else
			return "WebRoot/DisasterNode/addfailed";
	}
	@RequestMapping("/DetailDisasterNode")
	public String DetailDisasterNode(ModelMap modelmap,String dnodeId){
		DisasterNode disasterNode=disasterNodesService.selectByPrimaryKey(dnodeId);
		modelmap.put("disasterNode", disasterNode);
		
		
		Nodes node=nodesService.selectByPrimaryKey(dnodeId);
		String areaid=node.getAreaid();
		Map<String, Object> params=new  HashMap<String, Object>();
		if(areaid !=null)
		{
			Area area3=areaService.selectByPrimaryKey(areaid);
			params.put("parareaid", area3.getParareaid());
			List<Area> roadList=areaService.findByParams(params);
			Area area2=areaService.selectByPrimaryKey(area3.getParareaid());
			params.put("parareaid", area2.getParareaid());
			List<Area> streetList=areaService.findByParams(params);
			Area area1=areaService.selectByPrimaryKey(area2.getParareaid());
			params.put("parareaid", area1.getParareaid());
			List<Area> areas=areaService.findByParams(params);
			modelmap.put("road", area3 ); //下面几行是该风险清单项所在区、市、省；所有省、市、区
			modelmap.put("street", area2 );
			modelmap.put("area", area1 );
			modelmap.put("areas", areas );
			modelmap.put("roadlist", roadList );
			modelmap.put("streetlist", streetList );
		}
		
		return "WebRoot/DisasterNode/detailDisasterNode";
	}
	
	@RequestMapping("/getAllArea")
	public String getAllArea(ModelMap modelmap,String showinfoFlag,String emerId){
		if(showinfoFlag != null){
			showinfoFlag.trim();
		}
		modelmap.put("showinfoFlag", showinfoFlag);
		return "WebRoot/areaTree";
	}
	
	@ResponseBody
	@RequestMapping("/findEmergency")
	public void findEmergency(String emerTypeName) throws IOException{
		emerTypeName=URLDecoder.decode(emerTypeName, "UTF-8");
		Users user=(Users)request.getSession().getAttribute("user");
		String userid=user.getUserid();
		String usertype=user.getUsertype();
		String cSelect="";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("userid", userid);
		params.put("emertypename", emerTypeName);
		params.put("usertype", usertype);
		List<Emergency> emergencylist=emergencyService.selectByEmerTypeName(params);
		if (emergencylist != null && emergencylist.size() > 0) {
			for (int i = 0; i < emergencylist.size(); i++) {
				Emergency emergency = emergencylist.get(i);
				cSelect += "<option value=" + emergency.getEmerid()
						+ ">" + emergency.getEmername() + "</option>";
			}
		} else {
			cSelect += "<option value=\"0\">无符合实例，请选择其它</option>";
		}			
	response.getWriter().write(cSelect);
	}
	
	@ResponseBody
	@RequestMapping("/findArea")
	public void findArea(String areaid) throws IOException{
		String cSelect="";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("parareaid", areaid);
		params.put("arealevel", "2");
		
		if("0".equals(areaid)){
			List<Area> List = areaService.selectAll();
			if (List != null && List.size() > 0) {
				for (int i = 0; i < List.size(); i++) {
					Area street = List.get(i);
					cSelect += "<option value=" + street.getAreaid()
							+ ">" + street.getAreaname() + "</option>";
				}
			} else {
				cSelect += "<option value=\"0\">该市下无区</option>";
			}
		}else{
			List<Area> List = areaService.findByParams(params);
			if (List != null && List.size() > 0) {
				for (int i = 0; i < List.size(); i++) {
					Area street = List.get(i);
					cSelect += "<option value=" + street.getAreaid()
							+ ">" + street.getAreaname() + "</option>";
				}
			} else {
				cSelect += "<option value=\"0\">该市下无区</option>";
			}
		}
		response.getWriter().write(cSelect);
		
	}
	
	@ResponseBody
	@RequestMapping("/findStreet")
	public void findStreet(String streetid) throws IOException{
		String cSelect="";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("parareaid", streetid);
		params.put("arealevel", "3");
		
		if("0".equals(streetid)){
			List<Area> List = areaService.selectAll();
			if (List != null && List.size() > 0) {
				for (int i = 0; i < List.size(); i++) {
					Area street = List.get(i);
					cSelect += "<option value=" + street.getAreaid()
							+ ">" + street.getAreaname() + "</option>";
				}
			} else {
				cSelect += "<option value=\"0\">该市下无区</option>";
			}
		}else{
			List<Area> List = areaService.findByParams(params);
			if (List != null && List.size() > 0) {
				for (int i = 0; i < List.size(); i++) {
					Area street = List.get(i);
					cSelect += "<option value=" + street.getAreaid()
							+ ">" + street.getAreaname() + "</option>";
				}
			} else {
				cSelect += "<option value=\"0\">该市下无区</option>";
			}
		}
		response.getWriter().write(cSelect);
		
	}
	

}*/
