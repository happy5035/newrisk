package com.risk.plan.controller.transport;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.risk.plan.common.PageBean;
import com.risk.plan.entity.DisasterNode;
import com.risk.plan.entity.ENRelation;
import com.risk.plan.entity.Emergency;
import com.risk.plan.entity.Gene;
import com.risk.plan.entity.Goods;
import com.risk.plan.entity.GoodsType;
import com.risk.plan.entity.Nodes;
import com.risk.plan.entity.TranModel;
import com.risk.plan.entity.Trsportation;
import com.risk.plan.entity.Users;
import com.risk.plan.service.box.edge.GeneService;
import com.risk.plan.service.box.edge.TaskService;
import com.risk.plan.service.box.emer.EmerTypeService;
import com.risk.plan.service.box.emer.EmergencyService;
import com.risk.plan.service.box.transportation.TranModelService;
import com.risk.plan.service.box.transportation.TrsportationService;
import com.risk.plan.service.box.users.UsersService;
import com.risk.plan.util.Identities;

@Controller
public class TrsportController {
	//service
	@Autowired
	EmerTypeService emerTypeService;
	@Autowired
	EmergencyService emergencyService;
	@Autowired
	UsersService usersService;
	//httpServlet
	@Autowired
	HttpServletRequest request;
	@Autowired
	TrsportationService trsportationService;
	@Autowired
	TranModelService tranmodelService;
	@Autowired
	HttpServletResponse response;
	@Autowired
	com.risk.plan.service.box.disaster.NodesService nodesService;
	@Autowired 
	GeneService geneService;
	@Autowired
	TaskService taskService;
	
	
	@RequestMapping("/gotoAddTrsport")
	public String gotoAddTrsport(ModelMap modelmap){
		List<String> statelist=new ArrayList<String>();
		statelist.add("正常");
		statelist.add("维修中");
		statelist.add("其它");
		modelmap.put("statelist", statelist);
		
		//加载风险类型和事件
		List<String>emertypenames=emerTypeService.selectAllEmerTypeNames();
		Users user=(Users)request.getSession().getAttribute("user");
		String userid=user.getUserid();
		String usertype=user.getUsertype();
		Map<String, Object> params1=new HashMap<String, Object>();
		params1.put("userid", userid);
		params1.put("emertypename", emertypenames.get(0));
		params1.put("usertype", usertype);
		List<Emergency> emergencylist=emergencyService.selectByEmerTypeName(params1);
		if(emertypenames.size() >0)
		{
			modelmap.put("emergencylist", emergencylist);
		}else {
			String listStrng="No Option";
			modelmap.put("emergencylist", listStrng);
		}
		modelmap.put("emertypename", emertypenames);
		
		
		List<String>transtypes=tranmodelService.selectAllTransType();
		String transtype=transtypes.get(0);   
		Map<String, Object> params2=new HashMap<String, Object>();
		params2.put("transtype", transtype);	
		List<TranModel> tranmodellist=tranmodelService.selectByTransType(params2);
		modelmap.put("transtypename", transtypes) ;
		modelmap.put("tranmodellist",tranmodellist) ;
	
		
		Map<String, Object> params3=new HashMap<String, Object>();
		params3.put("userid", userid);
		params3.put("usertype", usertype);
		List<Nodes> nodeslist=nodesService.findByUseridAndUserType(params3);
		if(nodeslist.size() >0)
		{
			modelmap.put("nodeslist", nodeslist);
		}else {
			String listStrng="No Option";
			modelmap.put("nodeslist", listStrng);
		}
		modelmap.put("nodeslist", nodeslist);
		
		
		return "WebRoot/Transportation/addTransportation";
	}
	
	@ResponseBody
	@RequestMapping("/findLoadWeight")
	public void findLoadWeight(String transtype) throws IOException{
		transtype=URLDecoder.decode(transtype, "UTF-8");
		
		String cSelect="";
		Map<String, Object> params=new HashMap<String, Object>();
		
		params.put("transtype", transtype);
		List<TranModel> transmodellist=tranmodelService.selectByTransType(params);
		if (transmodellist != null && transmodellist.size() > 0) {
			for (int i = 0; i < transmodellist.size(); i++) {
				TranModel tranmodel = transmodellist.get(i);
				cSelect += "<option value=" + tranmodel.getTranmodelid()
						+ ">"  +tranmodel.getLoadweight()+ "</option>";
			}
		} else {
			cSelect += "<option value=\"0\">无符合实例，请选择其它</option>";
		}			
	response.getWriter().write(cSelect);
	}
	
	
	@RequestMapping("/saveTrsport")
	public String saveTrsportation(ModelMap modelmap,Trsportation trsportation){
		trsportation.setTransid(Identities.uuid2());
		if(trsportation.getNodeid().equals("0")||trsportation.getNodeid()==null){
			  modelmap.addAttribute("NoNodes", "该实例下无节点");
			  return "WebRoot/Transportation/NoNodes";
			}
		    else
		    {
		    	int resault=trsportationService.insertSelective(trsportation);
		        if(resault >0)
		        {
			       return "forward:gotoAddTrsport";
		        }
				else
					return "error/500";				    	
		    }	
	}
	
	@RequestMapping("/GotoSearchTrsport")
	public String GotoSearchTrsport(ModelMap modelmap){
		List<String> transtypes=tranmodelService.selectAllTransType();
		String transtype=transtypes.get(0);
		Map<String, Object> params1=new HashMap<String, Object>();
		params1.put("transtype", transtype);
		List<TranModel> tranmodellist=tranmodelService.selectByTransType(params1);
		modelmap.put("transtypename", transtypes );
		modelmap.put("tranmodellist",tranmodellist);
		return "WebRoot/Transportation/listTransportation";
	}
	
	
@RequestMapping("/SearchTrsport")
public String SearchTrsport(ModelMap modelmap,String page,Trsportation trsportation,TranModel tranModel){
	 StringBuffer url=new StringBuffer();
     url.append(request.getContextPath()+"/SearchTrsport?method=SearchTrsport");
     List<String> transtypes=tranmodelService.selectAllTransType();
		String transtype=transtypes.get(0);
		Map<String, Object> params1=new HashMap<String, Object>();
		params1.put("transtype", transtype);
		List<TranModel> tranmodellist=tranmodelService.selectByTransType(params1);
		modelmap.put("transtypename", transtypes );
		modelmap.put("tranmodellist",tranmodellist);
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
			params.put("sortName", "transid");
	        params.put("pageSize", pageSize);
		    params.put("startNum", startNum);
		    String busnumber=trsportation.getBusnumber();
		    if(busnumber != null && !"".equals(busnumber)){
		    	params.put("busnumber", "%"+busnumber+"%");
		    	modelmap.put("busnumber", busnumber);
		    	url.append("&busnumber="+busnumber);
		    }
		    String transno=trsportation.getTransno();
		    if(transno!= null && !"".equals(transno)){
		    	params.put("transno", "%"+transno+"%");
		    	modelmap.put("transno", transno);
		    	url.append("&transno="+transno);
		    }
		    String tranmdelid=trsportation.getTranmodelid();
		    if(tranmdelid != null && !"".equals(tranmdelid)){
		    	params.put("tranmodelid", "%"+tranmdelid+"%");
		    	modelmap.put("tranmodelid", tranmdelid);
		    	url.append("&tranmodelid="+tranmdelid);
		    }
		    
		    
		    String nodeid=trsportation.getNodeid();
		    if(nodeid != null && !"".equals(nodeid)){
		    	params.put("nodeid", "%"+nodeid+"%");
		    	modelmap.put("nodeid", nodeid);
		    	url.append("&nodeid="+nodeid);
		    }
		    
		    List<Trsportation> trsportationlist=trsportationService.findByParams(params);
		    for (int i = 0; i < trsportationlist.size(); i++) {
		    	Trsportation g=trsportationlist.get(i);
		    	TranModel tranmodelN=tranmodelService.selectByPrimaryKey(g.getTranmodelid());
		    	Nodes nodeN=nodesService.selectByPrimaryKey(g.getNodeid());
		    	g.setNodes(nodeN);
		    	g.setTranmodel(tranmodelN);
		    	trsportationlist.set(i, g);
		    }
		    int num=trsportationService.findByParamsCount(params);
			PageBean PageBean=new PageBean(current,pageSize,num,trsportationlist); 
			PageBean.setUrl(url.toString());
			modelmap.put("pageBean", PageBean);
			modelmap.put("Pagelist", PageBean.getList());
	
			return "WebRoot/Transportation/listTransportation";
       }


@RequestMapping("/EditTrsport")
public String EditTrsport(ModelMap modelmap,String transid){
	List<String> statelist=new ArrayList<String>();
	statelist.add("正常");
	statelist.add("维修中");
	statelist.add("其它");
	modelmap.put("statelist", statelist);
	List<String> transtypes=tranmodelService.selectAllTransType();
	String transtype=transtypes.get(0); 
	Map<String, Object> params1=new HashMap<String, Object>();
	params1.put("transtype", transtype);
	List<TranModel> tranmodellist=tranmodelService.selectByTransType(params1);
	modelmap.put("transtypename", transtypes );
	modelmap.put("tranmodellist", tranmodellist );
	
	Users user=(Users)request.getSession().getAttribute("user");
	String userid=user.getUserid();
	String usertype=user.getUsertype();
	Map<String, Object> params3=new HashMap<String, Object>();
	params3.put("userid", userid);
	params3.put("usertype", usertype);
	List<Nodes> nodeslist=nodesService.findByUseridAndUserType(params3);
	if(nodeslist.size() >0)
	{
		modelmap.put("nodeslist", nodeslist);
	}else {
		String listStrng="No Option";
		modelmap.put("nodeslist", listStrng);
	}
	modelmap.put("nodeslist", nodeslist);
	
	//通过id查找物资
	
	Trsportation trsportation=trsportationService.selectByPrimaryKey(transid);
	TranModel tranmodel=tranmodelService.selectByPrimaryKey(trsportation.getTranmodelid());
	Nodes nodes=nodesService.selectByPrimaryKey(trsportation.getNodeid());
	
	modelmap.put("trsportation",trsportation);
	modelmap.put("tranmodel", tranmodel);
	modelmap.put("nodes", nodes);
	return "WebRoot/Transportation/editTransportation";
}

@RequestMapping("/UpdateTrsport")
public String UpdateTrsport(ModelMap modelmap,Trsportation trsportation,String tranmodelid){
	int result=trsportationService.updateByPrimaryKeySelective(trsportation);
	TranModel tranmodel=tranmodelService.selectByPrimaryKey(tranmodelid);
	modelmap.put("tranmodelid", tranmodelid);
	modelmap.put("transtypename",tranmodel.getTranstype());
	if(result >0)
		return "forward:SearchTrsport";
	else{
		modelmap.put("transid",trsportation.getTransid());
		return "forward:EditTrsport";
	}
}

@RequestMapping("/DeleteTrsport")
public String DeleteTrsport(ModelMap modelmap,Trsportation trsportation){
	int result=0;
	/*List<Gene> gene=geneService.selectByTransid(trsportation.getTransid());*/
	/*taskService.deleteByGeneid(gene.getGeneid());
	geneService.deleteByTransid(trsportation.getTransid());
	*/
	result=trsportationService.deleteByPrimaryKey(trsportation.getTransid());
	
	if(result >0)
		return "redirect:SearchTrsport";
	else
		return "WebRoot/DisasterNode/addfailed";
}


}

 