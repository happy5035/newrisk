package com.risk.plan.controller.box;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.risk.plan.entity.DisasterNode;
import com.risk.plan.entity.ENRelation;
import com.risk.plan.entity.EmerType;
import com.risk.plan.entity.Emergency;
import com.risk.plan.entity.Nodes;
import com.risk.plan.entity.UERelation;
import com.risk.plan.entity.Users;
import com.risk.plan.entity.WareHouse;
import com.risk.plan.service.box.disaster.DisasterNodesService;
import com.risk.plan.service.box.disaster.ENrelationService;
import com.risk.plan.service.box.disaster.NodesService;
import com.risk.plan.service.box.emer.EmerTypeService;
import com.risk.plan.service.box.emer.EmergencyService;
import com.risk.plan.service.box.emer.UeRelationService;
import com.risk.plan.service.box.warehouse.WareHouseService;
import com.risk.plan.util.Identities;

@Controller
public class QuickCreatController {
	@Autowired
	EmerTypeService emerTypeService;
	@Autowired
	NodesService nodesService;
	@Autowired
	DisasterNodesService disasterNodesService;
	@Autowired
	WareHouseService wareHouseServcie;
	@Autowired
	ENrelationService enRelationService;
	@Autowired
	HttpServletRequest request;
	@Autowired
	EmergencyService emergencyService;
	@Autowired
	UeRelationService ueRelationService;

	@Autowired
	UERelation ueRelation;
	
	@Autowired
	Nodes nodes;
	@Autowired
	DisasterNode disasterNode;
	@Autowired
	ENRelation enRelation;
	@Autowired
	WareHouse wareHouse;
	
	@RequestMapping("/gotoAddExample")
	public String gotoAddExample(ModelMap modelmap){
		List<EmerType> list= emerTypeService.selectAll();
		List<String>emertypenames=new ArrayList<String>();
		List<EmerType> emerTypelist=new ArrayList<EmerType>();
		List<EmerType> emerTypelist2=new ArrayList<EmerType>();
		for (EmerType emertype : list) {
			if (!emertypenames.contains(emertype.getEmertypename())) {
				emertypenames.add(emertype.getEmertypename());
			}
			if(emertype.getEmertypename().equals(emertypenames.get(0) )){
				if (!emertypenames.get(0).equals("") && emertypenames.get(0) != null)
						emerTypelist.add(emertype);
			}
			 String emertypeid=emerTypelist.get(0).getEmertypeid();
			if(emertype.getEmertypeid().equals(emertypeid)){
				if(!emertypeid.equals("") && emertypeid !=null){
					emerTypelist2.add(emertype);	
				}
			}
		}
		modelmap.put("emerTypeName",emertypenames);
		modelmap.put("emerTypelist",emerTypelist);
		modelmap.put("emerTypelist2",emerTypelist2 );
		modelmap.put("number", "0");
		return "WebRoot/QuicklyCreate/addExample";
	}
	@RequestMapping("/saveEmergency2")
	public String saveEmergency2(Emergency emergency,String emertypeid){
		Users user=(Users)request.getSession().getAttribute("user");
		emergency.setEmerid(Identities.uuid2());
		emergencyService.insert(emergency);
		request.getSession().setAttribute("emergency", emergency);
		//将用户与风险事件匹配起来
		ueRelation.setUerelationid(Identities.uuid2());
		ueRelation.setEmerid(emergency.getEmerid());
		ueRelation.setUserid(user.getUserid());
		ueRelation.setState("1");   //是自己创建的，而不是被别人授权的
		ueRelationService.insert(ueRelation);
		return "forward:gotoAddExample";
	}
	@RequestMapping("/saveDisasterNode2")
	public String saveDisasterNode2(ModelMap modelmap,String length1){
		int length=Integer.parseInt(length1);
		for (int i = 0; i < length; i++) {
			String nodeid=Identities.uuid2();
			nodes.setNodeid(nodeid);
			nodes.setNodetype("D");
			nodes.setNodename(request.getParameter("disastername"+i));
			nodesService.insertSelective(nodes);
			//保存风险清单项
			disasterNode.setDnodeid(nodeid);
			disasterNode.setDisastername(request.getParameter("disastername"+i));
			disasterNode.setDisasterno(request.getParameter("disasterno"+i));
			disasterNode.setPrincipal(request.getParameter("principal"+i));
			disasterNode.setPhone(request.getParameter("phone"+i));
			disasterNode.setDisasterlevel(request.getParameter("disasterLevel"+i));
			disasterNodesService.insertSelective(disasterNode);
			
			//以下是为了填节点与风险事件关系表
			Emergency emergency=(Emergency)request.getSession().getAttribute("emergency");
			if(emergency!=null){
				enRelation.setEnrelationid(Identities.uuid2());
				enRelation.setEmerid(emergency.getEmerid());
				enRelation.setNodeid(nodeid);
				enRelationService.insertSelective(enRelation);
			}
			
		}
		return "forward:gotoAddExample";
	}
	@RequestMapping("/saveWareHouse2")
	public String saveWareHouse2(ModelMap modelmap,String length2){
		int length=Integer.parseInt(length2);
		for (int i = 0; i < length; i++) {
			String nodeid=Identities.uuid2();
			nodes.setNodeid(nodeid);
			nodes.setNodetype("W");
			nodes.setNodename(request.getParameter("wareHouseName"+i));
//			待完善
//			nodesService.insertSelective(nodes);
			//保存风险应对策略
			wareHouse.setWnodeid(nodeid);
			wareHouse.setWarehousename(request.getParameter("wareHouseName"+i));
			wareHouse.setWarehouseno(request.getParameter("wareHouseNo"+i));
			wareHouse.setWarehouselevel(request.getParameter("wareHouseLevel"+i));
			wareHouse.setProperty(request.getParameter("property"+i));
			wareHouse.setDimensions(request.getParameter("dimensions"+i));
			wareHouse.setCapacity(request.getParameter("capacity"+i));
			wareHouse.setAdministrator(request.getParameter("administrator"+i));
			wareHouse.setPhone(request.getParameter("phone"+i));
//			wareHouseService.insertSelective(wareHouse);
			
			//以下是为了填节点与风险事件关系表
			Emergency emergency=(Emergency)request.getSession().getAttribute("emergency");
			if(emergency!=null){
				enRelation.setEnrelationid(Identities.uuid2());
				enRelation.setEmerid(emergency.getEmerid());
				enRelation.setNodeid(nodeid);
//				enRelationService.insertSelective(enRelation);
			}
		}
		return "forward:gotoAddExample";
	}
	
	@RequestMapping("/openGoodsRelDetail")
	public String openGoodsRelDetail(ModelMap modelmap){
		modelmap.put("number", "1");
		modelmap.put("number2", "1");
		modelmap.put("num", "1");
		return "WebRoot/QuicklyCreate/addGoodsRel2";
	}
	@RequestMapping("/openEdgeDetail")
	public String openEdgeDetail(ModelMap modelmap ){
		modelmap.put("number", "1");

		return "WebRoot/QuicklyCreate/addEdge2";
	}
}
