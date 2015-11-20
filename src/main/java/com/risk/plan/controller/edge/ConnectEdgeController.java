package com.risk.plan.controller.edge;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.risk.plan.common.EdgeInfo;
import com.risk.plan.common.NodesInfo;
import com.risk.plan.entity.Edge;
import com.risk.plan.entity.Emergency;
import com.risk.plan.entity.GoodsType;
import com.risk.plan.entity.Sub;
import com.risk.plan.entity.TranModel;
import com.risk.plan.entity.Users;
import com.risk.plan.exception.LoopExistException;
import com.risk.plan.service.box.edge.EdgeService;
import com.risk.plan.service.box.emer.EmerTypeService;
import com.risk.plan.service.box.emer.EmergencyService;
import com.risk.plan.service.box.sub.SubService;
import com.risk.plan.util.Identities;

@Controller
public class ConnectEdgeController {
	//service
	@Autowired
	EmerTypeService emerTypeService;
	@Autowired
	EmergencyService emergencyService;
	@Autowired
	SubService subService;
	@Autowired
	EdgeService edgeService;
	@Autowired
	HttpServletResponse response;
	//httpServlet
	@Autowired
	HttpServletRequest request;
	
	@RequestMapping("/gosearchDisaster")
	public String gosearchDisaster(ModelMap modelmap){
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
		return "WebRoot/Edge/searchDisaster";
	}
	@RequestMapping("/gotoAddEdge")
	public String gotoAddEdge(ModelMap modelmap){
		List<String>emername=emergencyService.selectAllEmerNames();
		Users user=(Users)request.getSession().getAttribute("user");
		String userid=user.getUserid();
		String usertype=user.getUsertype();
		Map<String, Object> params1=new HashMap<String, Object>();
		params1.put("userid", userid);
		params1.put("emername", emername.get(0));
		params1.put("usertype", usertype);
		List<Emergency> emergencylist=emergencyService.selectAll();
		modelmap.put("emergencylist", emergencylist);
		List<Emergency> emergencylist1=emergencyService.selectByEmerName(params1);
		if(emername.size() >0)
		{
			modelmap.put("emersublist", emergencylist1);
		}else {
			String listStrng="No Option";
			modelmap.put("emersublist", listStrng);
		}
		//modelmap.put("emername", emername);
		
	
		return "WebRoot/Edge/addEdge";
	}

	@ResponseBody
	@RequestMapping("/findSubNum")
	public void findSubNum(String emerid) throws UnsupportedEncodingException{
		
		Users user=(Users)request.getSession().getAttribute("user");
		String userid=user.getUserid();
		String usertype=user.getUsertype();
		emerid=URLDecoder.decode(emerid, "UTF-8");
		String cSelect = "";
		try {
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("userid", userid);
			
			params.put("usertype", usertype);
			params.put("emerid", emerid);
			List<Emergency> emersublist=emergencyService.selectByEmerId(params);
			
			if (emersublist != null && emersublist.size() > 0) {
				for (int i = 0; i < emersublist.size(); i++) {
					Emergency type = emersublist.get(i);
					cSelect += "<option value=" + type.getEmerid()
							+ ">" + type.getSubnum()+ "</option>";
				}
			} else {
				cSelect += "<option value=\"0\">该项目无子项目</option>";
			}			
		response.getWriter().write(cSelect);
		} catch (Exception e) {
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/findEmerSub")
	public void findEmerSub(String emerid, ModelMap modelmap) throws UnsupportedEncodingException{
		
		Users user=(Users)request.getSession().getAttribute("user");
		String userid=user.getUserid();
		String usertype=user.getUsertype();
		emerid=URLDecoder.decode(emerid, "UTF-8");
		String cSelect = "";
		try {
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("emerid", emerid);
			List<Sub> sublist=subService.selectByEmerId(params);
			//modelmap.put("sublist", sublist);
//			request.setAttribute("sublist", sublist);
			if (sublist != null && sublist.size() > 0) {
				for (int i = 0; i < sublist.size(); i++) {
					Sub type = sublist.get(i);
					cSelect += "<option value=" + type.getSubid()
							+ ">" + type.getSubname()+ "</option>";
				}
			} else {
				cSelect += "<option value=\"0\">该项目无子项目</option>";
			}			
		response.getWriter().write(cSelect);
		} catch (Exception e) {
		}
	}
	
	
	
	@ResponseBody
	@RequestMapping("/findSecondSub")
	public void findSecondSub(String subid, ModelMap modelmap) throws Exception{
		
		Users user=(Users)request.getSession().getAttribute("user");
		String userid=user.getUserid();
		String usertype=user.getUsertype();
		subid=URLDecoder.decode(subid, "UTF-8");
		
		
		
		String cSelect = "";
		try {
			/*
			 * 迭代之前的节点
			 */
			
			List<Sub> sublist=subService.findPreSubo(subid);
			
			
			if (sublist != null && sublist.size() > 0) {
				for (int i = 0; i < sublist.size(); i++) {
					Sub type = sublist.get(i);
					cSelect += "<option value=" + type.getSubid()
							+ ">" + type.getSubname()+ "</option>";
				}
			} else {
				cSelect += "<option value=\"0\">该项目无子项目</option>";
			}			
		response.getWriter().write(cSelect);
		}catch(LoopExistException e){
			cSelect="<option value=\"0\">"+e.toString()+"</option>";
			response.getWriter().write(cSelect);
			e.printStackTrace();
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/saveEdgeOri")
	public void saveEdgeOri(String subid,String subsid,ModelMap modelmap,Edge edge) throws UnsupportedEncodingException{
		
		Users user=(Users)request.getSession().getAttribute("user");
		String userid=user.getUserid();
		String usertype=user.getUsertype();
		subid=URLDecoder.decode(subid, "UTF-8");
		subsid=URLDecoder.decode(subsid, "UTF-8");
		edge.setEdgeid(Identities.uuid2());
		
		String cSelect = "";
		try {
			
			if (subid != null && subsid!=null) {
				Map<String, Object> params=new HashMap<String, Object>();
				edge.setFirstnodeid(subid);
				edge.setSecondnodeid(subsid);
				edgeService.insertSelective(edge);
				
			} else {
				 modelmap.addAttribute("NoNodes", "保存失败");
			}			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/deleteEdge")
	public void deleteEdge(String subid,String subsid,ModelMap modelmap,Edge edge) throws UnsupportedEncodingException{
		
		Users user=(Users)request.getSession().getAttribute("user");
		String userid=user.getUserid();
		String usertype=user.getUsertype();
		subid=URLDecoder.decode(subid, "UTF-8");
		subsid=URLDecoder.decode(subsid, "UTF-8");
		
		String cSelect = "";
		try {	
			if (subid != null && subsid!=null) {
				Map<String, Object> params=new HashMap<String, Object>();
				params.put("firstnodeid",subid);
				params.put("secondnodeid", subsid);
				edgeService.DeleteByfirstAndsecond(params);
				
			} else {
				 modelmap.addAttribute("NoNodes", "删除失败");
			}			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/getAllArea")
	public String getAllArea(){
		
		return "WebRoot/Edge/allEdge";
	}
	@ResponseBody
	@RequestMapping("/getEdgeInfo")
	public Map<String, Object>  getEdgeInfo(){
		List<NodesInfo> nodesInfos=new ArrayList<NodesInfo>();
		List<EdgeInfo> edgeInfos=new ArrayList<EdgeInfo>();
		Map<String, Object> modelmap=new HashMap<String, Object>();
		String emerid="4d376e9c235847b2b39fef9ff3ee86d1";
		List<Edge> edges=edgeService.selectAll();
		if(edges==null) return null;
		for (Edge edge : edges) {
			EdgeInfo edgeinfo=new EdgeInfo();
			edgeinfo.setFrom(edge.getFirstnodeid());
			edgeinfo.setTo(edge.getSecondnodeid());
//			edgeinfo.setName(edge.getEdgeid());
			edgeInfos.add(edgeinfo);
		}
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("emerid", emerid);
		List<Sub> subs=subService.selectByEmerId(params);
		if(subs == null) return null;
		for (int i = 0; i < subs.size(); i++) {
			NodesInfo nodesinfo=new NodesInfo();
			Sub sub=subs.get(i);
			nodesinfo.setId(sub.getSubid());
			nodesinfo.setName(sub.getSubno());
			nodesInfos.add(nodesinfo);
		}
		modelmap.put("nodes", nodesInfos);
		modelmap.put("edges", edgeInfos);
		return modelmap;
	}
	
}