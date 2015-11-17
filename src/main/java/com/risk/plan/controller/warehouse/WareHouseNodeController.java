package com.risk.plan.controller.warehouse;

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
import org.springframework.web.bind.annotation.RequestMapping;

import com.risk.plan.common.PageBean;
import com.risk.plan.entity.Area;
import com.risk.plan.entity.DisasterNode;
import com.risk.plan.entity.ENRelation;
import com.risk.plan.entity.EmerType;
import com.risk.plan.entity.Emergency;
import com.risk.plan.entity.Nodes;
import com.risk.plan.entity.Users;
import com.risk.plan.entity.WareHouse;
import com.risk.plan.service.box.disaster.AreaService;
import com.risk.plan.service.box.disaster.ENrelationService;
import com.risk.plan.service.box.disaster.NodesService;
import com.risk.plan.service.box.emer.EmerTypeService;
import com.risk.plan.service.box.emer.EmergencyService;
import com.risk.plan.service.box.warehouse.WareHouseService;
import com.risk.plan.util.Identities;

/** 
* @ClassName: WareHouseNodeController 
* @Description: TODO
* @author yjw 10291095_xjtu_edu_com 
* @date 2015年10月4日 下午5:22:06 
*  
*/
@Controller
public class WareHouseNodeController {
	//Service
	@Autowired
	AreaService areaService;
	@Autowired
	EmerTypeService emerTypeService;
	@Autowired
	EmergencyService emergencyService;
	@Autowired
	NodesService nodesService;
	@Autowired
	ENrelationService enRelationService;
	@Autowired
	WareHouseService wareHouseService;
	
	//entity
	@Autowired
	ENRelation enRelaion;
	@Autowired
	EmerType emertype;
	@Autowired
	Area area;
	
	//httpservlet
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	
	
	/** 
	* @Title: gotoAddWareHouse 
	* @Description: 添加仓库
	* @param modelmap
	* @return String    返回类型    
	*/
	@RequestMapping("/gotoAddWareHouse")
	public String gotoAddWareHouse(ModelMap modelmap,Area area,String nodeId){
		//加载选择地区
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("arealevel", "1");
		List<Area> areas=areaService.findByParams(params);
		modelmap.put("areas", areas);
		List<Area> streetlist =  new ArrayList<Area>();
		List<Area> roadlist = new ArrayList<Area>();
		Area street = new Area();
		Area road = new Area();
		String areaid=area.getAreaid();
		String streetid = null;
		if (areaid == null && areas.size() > 0) {
			// 第一次进入默认选择第一个逻辑柜
			areaid = areas.get(1).getAreaid();
			area = areaService.selectByPrimaryKey(areaid);
			params.put("parareaid", areaid);
			params.put("arealevel", "2");
			streetlist = areaService.findByParams(params);
			
			if (streetid == null && streetlist.size() > 0) {
				streetid = streetlist.get(0).getAreaid();
			}
			street = areaService.selectByPrimaryKey(streetid);
			params.put("parareaid", streetid);
			params.put("arealevel", "3");
			roadlist =  areaService.findByParams(params);
			road=roadlist.get(0);
		} else if (nodeId != null) {
			// 第二次选择点击查询时
			area =  areaService.selectByPrimaryKey(areaid);
			params.put("parareaid", areaid);
			params.put("arealevel", "2");
			streetlist = areaService.findByParams(params);
			if (streetid == null && streetlist.size() > 0) {
				streetid = streetlist.get(0).getAreaid();
			}
			street = areaService.selectByPrimaryKey(streetid);
			params.put("parareaid", streetid);
			params.put("arealevel", "3");
			roadlist =  areaService.findByParams(params);
		}
		modelmap.put("road", road);
		modelmap.put("area", area);
		modelmap.put("roadlist",roadlist );
		modelmap.put("street", street);
		modelmap.put("streetlist",streetlist );
		modelmap.put("areas",areas );
		modelmap.put("areaid",areaid );
		
		//加载风险类型和事件
		List<String>emertypenames=emerTypeService.selectAllEmerTypeNames();
		Users user=(Users)request.getSession().getAttribute("user");
		String userid=user.getUserid();
		Map<String, Object> params1=new HashMap<String, Object>();
		params1.put("userid", userid);
		params1.put("emertypename", "水灾");
		List<Emergency> emergencylist=emergencyService.selectByEmerTypeName(params1);
		if(emertypenames.size() >0)
		{
			modelmap.put("emergencylist", emergencylist);
		}else {
			String listStrng="No Option";
			modelmap.put("emergencylist", listStrng);
		}
		modelmap.put("emertypename", emertypenames);
		
		
		return "WebRoot/WareHouseNode/addWareHouseNode";
	}

	
	/** 
	* @Title: saveWareHouse 
	* @Description: TODO
	* @param modelmap
	* @param wareHouse
	* @param emergency
	* @param node
	* @param enRelation
	* @param note
	* @param roadid
	* @return String    返回类型    
	*/
	@RequestMapping("/saveWareHouse")
	public String saveWareHouse(ModelMap modelmap,WareHouse wareHouse,Emergency emergency ,Nodes node,ENRelation enRelation
			,String note, String roadid){
		int resault=0;
		//节点
		String nodeid=Identities.uuid2();
		node.setNodeid(nodeid);
		node.setAreaid(roadid);
		node.setNodename(wareHouse.getWarehousename());
		node.setNodetype("W");
		node.setNotes(note);
		resault= nodesService.insertSelective(node);
		//风险应对策略
		wareHouse.setWnodeid(nodeid);
		resault*=wareHouseService.insertSelective(wareHouse);
		//节点和风险事件关联
		enRelation.setEnrelationid(Identities.uuid2());
		enRelation.setEmerid(emergency.getEmerid());
		enRelation.setNodeid(nodeid);
		resault*=enRelationService.insertSelective(enRelation);
		if(resault >0)
			return "WebRoot/WareHouseNode/addSuccess";
		else
			return "WebRoot/DisasterNode/addfailed";
	}
	
	/** 
	* @Title: GotoSearchWareHouse 
	* @Description: 前往查找风险应对策略
	* @param modelmap
	* @return String    返回类型    
	*/
	@RequestMapping("/GotoSearchWareHouse")
	public String GotoSearchWareHouse(ModelMap modelmap){
		//加载风险类型和事件
		List<String>emertypenames=emerTypeService.selectAllEmerTypeNames();
		Users user=(Users)request.getSession().getAttribute("user");
		String userid=user.getUserid();
		Map<String, Object> params1=new HashMap<String, Object>();
		params1.put("userid", userid);
		params1.put("emertypename", "水灾");
		List<Emergency> emergencylist=emergencyService.selectByEmerTypeName(params1);
		if(emertypenames.size() >0)
		{
			modelmap.put("emergencylist", emergencylist);
		}else {
			String listStrng="No Option";
			modelmap.put("emergencylist", listStrng);
		}
		modelmap.put("emertypename", emertypenames);
		return "WebRoot/WareHouseNode/listWareHouseNode";
	}
	
	/** 
	* @Title: SearchWareHouse 
	* @Description: 查找风险应对策略
	* @param modelmap
	* @param wareHouse
	* @param emertypename
	* @param emerid
	* @param page
	* @return String    返回类型    
	*/
	@RequestMapping("/SearchWareHouse")
	public String SearchWareHouse(ModelMap modelmap,WareHouse wareHouse,String emertypename,String emerid
			,String page){
		Users user=(Users)request.getSession().getAttribute("user");
		String userid=user.getUserid();
		StringBuffer url=new StringBuffer();
	    url.append(request.getContextPath()+"/SearchWareHouse?method=SearchWareHouse");

		//加载风险类型和事件
		List<String>emertypenames=emerTypeService.selectAllEmerTypeNames();
	    Map<String, Object> params1=new HashMap<String, Object>();
		params1.put("userid", userid);
		params1.put("emertypename", emertypename);
		List<Emergency> emergencylist=emergencyService.selectByEmerTypeName(params1);
		if(emertypenames.size() >0)
		{
			modelmap.put("emergencylist", emergencylist);
		}else {
			String listStrng="No Option";
			modelmap.put("emergencylist", listStrng);
		}
		modelmap.put("emertypename", emertypenames);
		
		//按条件查找
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
			    String warehousename= wareHouse.getWarehousename() ;
				String warehouseno=wareHouse.getWarehouseno();
				String warehouselevel=wareHouse.getWarehouselevel();
				String property=wareHouse.getProperty();
				url.append("&warehousename="+warehousename+
						"&warehouseno="+warehouseno+
						"&warehouselevel="+warehouselevel+
						"&property="+property); 
			    if(emerid != null && !"".equals(emerid))
		    	 {        
			    	params.put("emerid", emerid);
		    		 url.append("&emerid="+emerid);  //翻页的时候用的，拼接url！
		    	 }
			    modelmap.put("warehousename",warehousename);
			    modelmap.put("warehouseno", warehouseno);
			    modelmap.put("warehouselevel", warehouselevel);
			    modelmap.put("property", property);
			    modelmap.put("emerid", emerid);
			    Emergency emergency=emergencyService.selectByPrimaryKey(emerid);
			    modelmap.put("emergency", emergency);
			    List<ENRelation> enRelationlist=enRelationService.selectByWareHouseParams(params, wareHouse);
			    if(enRelationlist.size()>0 && enRelationlist !=null)
			    {
			    	ENRelation enrelation= enRelationlist.get(0);
				    EmerType et=enrelation.getEmertype();
				    modelmap.put("emertype", et);
			    }else{
			    	emertype.setEmertypename(emertypename);
				    modelmap.put("emertype", emertype);
			    }
			    int num=enRelationService.selectCountByWareHouseParams(params, wareHouse);
				PageBean PageBean=new PageBean(current,pageSize,num,enRelationlist); 
				PageBean.setUrl(url.toString());
				modelmap.put("pageBean", PageBean);
				modelmap.put("Pagelist", PageBean.getList());
	    
		return "WebRoot/WareHouseNode/listWareHouseNode";
	}

	/** 
	* @Title: DetailWareHouse 
	* @Description: 查看仓库详情
	* @param modelmap
	* @param wnodeid
	* @return String    返回类型    
	*/
	@RequestMapping("/DetailWareHouse")
	public String DetailWareHouse(ModelMap modelmap,String wnodeid){
		WareHouse wareHouse=wareHouseService.selectByPrimaryKey(wnodeid);
		modelmap.put("wareHouse", wareHouse);
		
		Nodes node=nodesService.selectByPrimaryKey(wnodeid);
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
		return "WebRoot/WareHouseNode/detailWareHouseNode";
	}

	/** 
	* @Title: EditWareHouse 
	* @Description: 编辑风险应对策略
	* @param modelmap
	* @param nodeid
	* @param enrelationid
	* @return String    返回类型    
	*/
	@RequestMapping("/EditWareHouse")
	public String EditWareHouse(ModelMap modelmap,String nodeid,String enrelationid){
		String id=nodeid;
		String enrelaitonid=enrelationid;
		Users user=(Users)request.getSession().getAttribute("user");
		String userid=user.getUserid();
		WareHouse wareHouse=wareHouseService.selectByPrimaryKey(id);
		ENRelation enRelaiton=enRelationService.selectByPrimaryKey(enrelaitonid);
		Emergency emergency=emergencyService.selectByPrimaryKey(enRelaiton.getEmerid());
		Nodes node=nodesService.selectByPrimaryKey(id);
		modelmap.put("wareHouse", wareHouse );
		modelmap.put("emergency", emergency );
		modelmap.put("enrelationid",  enrelaitonid);
		modelmap.put("nodes",  node);
		
		//获取风险类型名称
		List<String> emerTypeNames=emerTypeService.selectAllEmerTypeNames();
		//获取用户权限下的风险类型对应的风险
		Map<String, Object> params=new HashMap<String, Object>();
		/*params.put("emertypename", emergency.getEmertype().getEmertypename());*/
		params.put("userid", userid );
		List<Emergency> emergencyList=emergencyService.selectByEmerTypeName(params);
		modelmap.put("emertypename", emerTypeNames );
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
				
		
		return "WebRoot/WareHouseNode/editWareHouseNode";
	}
	
	/** 
	* @Title: UpdateWareHouse 
	* @Description: 更新仓库
	* @param modelmap
	* @param wareHouse
	* @param emertypename
	* @param emerid
	* @param enRelation
	* @param note
	* @param node
	* @param wnodeid
	* @param roadid
	* @param enrelationid
	* @return String    返回类型    
	*/
	@RequestMapping("/UpdateWareHouse")
	public String UpdateWareHouse(ModelMap modelmap,WareHouse wareHouse,String emertypename,String emerid,
			ENRelation enRelation,String note, Nodes node,String wnodeid,String roadid,String enrelationid){
		int resault=0;
		node.setNodeid(wnodeid);
		node.setAreaid(roadid);
		node.setNodename(wareHouse.getWarehousename());
		node.setNodetype("W");
		node.setNotes(note);
		resault= nodesService.updateByPrimaryKeySelective(node);
		resault*=wareHouseService.updateByPrimaryKeySelective(wareHouse);
		enRelaion.setEnrelationid(enrelationid);
		enRelaion.setEmerid(emerid);
		enRelaion.setNodeid(wnodeid);
		resault*= enRelationService.updateByPrimaryKeySelective(enRelaion);
		if(resault >0)
			return "redirect:GotoSearchWareHouse";
		else
			return "WebRoot/DisasterNode/addfailed";
		
	}
	@RequestMapping("/DeleteWareHouse")
	public String DeleteWareHouse(){
		return "redirect:GotoSearchWareHouse";
	}
}
