package com.risk.plan.controller.goods;

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

import com.risk.plan.common.PageBean;
import com.risk.plan.entity.Emergency;
import com.risk.plan.entity.Goods;
import com.risk.plan.entity.GoodsRelation;
import com.risk.plan.entity.GoodsType;
import com.risk.plan.entity.Nodes;
import com.risk.plan.entity.Users;
import com.risk.plan.service.box.disaster.NodesService;
import com.risk.plan.service.box.emer.EmerTypeService;
import com.risk.plan.service.box.emer.EmergencyService;
import com.risk.plan.service.box.goods.GoodsRelationService;
import com.risk.plan.service.box.goods.GoodsService;
import com.risk.plan.util.Identities;

/** 
* @ClassName: GoodsRelController 
* @Description: 供需管理控制器
* @author yjw 10291095_xjtu_edu_com 
* @date 2015年10月5日 下午3:41:17 
*  
*/
@Controller
public class GoodsRelController {
	//service
	@Autowired
	EmerTypeService emerTypeService;
	@Autowired
	EmergencyService emergencyService;
	@Autowired
	GoodsService goodsService;
	@Autowired
	NodesService nodesService;
	@Autowired
	GoodsRelationService goodsRelationService;
	
	
	//httpservlet
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	
	
	/** 
	* @Title: gotoAddGoodsRel 
	* @Description: 加载添加页面
	* @param modelmap
	* @return String    返回类型    
	*/
	@RequestMapping("/gotoAddGoodsRel")
	public String gotoAddGoodsRel(ModelMap modelmap){
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
		
		//加载物资和节点
		List<Goods> goodslist=goodsService.selectAll();
		List<Nodes> nodeslist=nodesService.findByEmerid(emergencylist.get(0).getEmerid());
		List<Goods> goodslist2=new ArrayList<Goods>();
		goodslist2.add(goodslist.get(0));
		
		modelmap.put("goodslist", goodslist );
		modelmap.put("nodeslist",nodeslist  );
		modelmap.put("goodslist2", goodslist2 );
		
		return "WebRoot/GoodsRel/addGoodsRel";
	}
	
	/** 
	* @Title: saveGoodsRel 
	* @Description: 保存供需关系
	* @param modelmap
	* @param goods
	* @param emerid
	* @param goodsrel
	* @param type
	* @param demandnum
	* @return String    返回类型    
	*/
	@RequestMapping("/saveGoodsRel")
	public String saveGoodsRel(ModelMap modelmap,Goods goods,String emerid,GoodsRelation goodsrel,
			String type,String demandnum){
		
		if(type.contains("F"))
		{
			demandnum="-"+demandnum;
		}
		else if(type.contains("S"))
		{
			demandnum="+"+demandnum;
		}
		String goodreid=Identities.uuid2();
		goodsrel.setGoodsreid(goodreid);
		goodsrel.setDemandnum(demandnum);
		
		int resault=goodsRelationService.insertSelective(goodsrel);
		if(resault > 0)
			return "redirect:gotoAddGoodsRel";
		else 
			return "error/500";
	}
	
	
	/** 
	* @Title: GotoSearchGoodsRel 
	* @Description: 加载查找页面
	* @param modelmap
	* @return String    返回类型    
	*/
	@RequestMapping("/GotoSearchGoodsRel")
	public String GotoSearchGoodsRel(ModelMap modelmap){
		
		
		Users user=(Users)request.getSession().getAttribute("user");
		String userid=user.getUserid();
		String usertype=user.getUsertype();
		List<Nodes> nodeslist=nodesService.findByUseridAndUserType(userid, usertype);
		List<Goods> goodslist=goodsService.selectAll();
		
		modelmap.put("goodslist", goodslist );
		modelmap.put("nodeslist",nodeslist  );
	
		
		return "WebRoot/GoodsRel/listGoodsRel";
	}
	
	
	/** 
	* @Title: SearchGoodsRel 
	* @Description: 查找供需关系
	* @param modelmap
	* @param page
	* @param type
	* @param nodeid
	* @param goodsid
	* @return String    返回类型    
	*/
	@RequestMapping("/SearchGoodsRel")
	public String SearchGoodsRel(ModelMap modelmap,String page,String type
			,String nodeid,String goodsid){
		 StringBuffer url=new StringBuffer();
	     url.append(request.getContextPath()+"/SearchGoodsRel?method=SearchGoodsRel");
		
		Users user=(Users)request.getSession().getAttribute("user");
		String userid=user.getUserid();
		String usertype=user.getUsertype();
		List<Nodes> nodeslist=nodesService.findByUseridAndUserType(userid, usertype);
		List<Goods> goodslist=goodsService.selectAll();
		
		modelmap.put("goodslist", goodslist );
		modelmap.put("nodeslist",nodeslist  );
		
		modelmap.put("nodeid", nodeid);
		modelmap.put("goodsid", goodsid);
		
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
				params.put("sortName", "goodsreid");
		        params.put("pageSize", pageSize);
			    params.put("startNum", startNum);
			    if(nodeid != null && !"".equals(nodeid)){
			    	params.put("nodeid", "%"+nodeid+"%");
			    	url.append("&nodeid="+nodeid);
			    }
			    if(goodsid != null && !"".equals(goodsid)){
			    	params.put("goodsid", "%"+goodsid+"%");
			    	url.append("&goodsid="+goodsid);
			    }
			    if(type != null && !"".equals(type) && type.contains("S")){
			    	params.put("demandnum", "%"+"+"+"%");
			    	url.append("&type="+type);
			    }
			    if(type != null && !"".equals(type) && type.contains("F")){
			    	params.put("demandnum", "%"+"-"+"%");
			    	url.append("&type="+type);
			    }
			    
		    	modelmap.put("nodeid", nodeid);
		    	modelmap.put("goodsid", goodsid);
		    	modelmap.put("type", type);
		    	List<GoodsRelation> goodsrelationlist=goodsRelationService.findByParams(params);
			    int num= goodsRelationService.findByParamsCount(params);
				PageBean PageBean=new PageBean(current,pageSize,num,goodsrelationlist); 
				PageBean.setUrl(url.toString());
				modelmap.put("pageBean", PageBean);
				modelmap.put("Pagelist", PageBean.getList());
		
		return "WebRoot/GoodsRel/listGoodsRel";
	}
	
	/** 
	* @Title: EditGoodsRel 
	* @Description: 编辑供需关系
	* @param modelmap
	* @param goodsReid
	* @return String    返回类型    
	*/
	@RequestMapping("/EditGoodsRel")
	public String EditGoodsRel(ModelMap modelmap ,String goodsReid){
		Users user=(Users)request.getSession().getAttribute("user");
		String userid=user.getUserid();
		String usertype=user.getUsertype();
		List<Nodes> nodeslist=nodesService.findByUseridAndUserType(userid, usertype);
		List<Goods> goodslist=goodsService.selectAll();
		GoodsRelation goodsrel =goodsRelationService.selectByPrimaryKey(goodsReid);
		
		modelmap.put("goodslist", goodslist );
		modelmap.put("nodeslist",nodeslist  );
		modelmap.put("goodsrel", goodsrel );
		
		return "WebRoot/GoodsRel/editGoodsRel";
	}
	
	/** 
	* @Tite: UpdateGoodsRel 
	* @Description: 保存更新
	* @param modelmap
	* @param goodsRel
	* @param demandNum
	* @param type
	* @return String    返回类型    
	*/
	@RequestMapping("/UpdateGoodsRel")
	public String UpdateGoodsRel(ModelMap modelmap ,GoodsRelation goodsRel,String demandNum, String type){
		if(type.contains("F"))
		{
			demandNum="-"+demandNum;
		}
		else if(type.contains("S"))
		{
			demandNum="+"+demandNum;
		}
		goodsRel.setDemandnum(demandNum);
		int resault=goodsRelationService.updateByPrimaryKeySelective(goodsRel);
		
		modelmap.put("nodeid", goodsRel.getNodeid());
		modelmap.put("goodsid", goodsRel.getGoodsid());
		
		if(resault >0)
			return "forward:SearchGoodsRel";
		else
			return "error/500";
	}
	
	@RequestMapping("/DeleteGoodsRel")
	public String DeleteGoodsRel(ModelMap modelmap ,GoodsRelation goodsRel){
		
		return "redirect:GotoSearchGoodsRel";
	}
	
	
	/** 
	* @Title: findNodes 
	* @Description: 加载相应的节点
	* @param emerId
	* @throws IOException void    返回类型    
	*/
	@ResponseBody
	@RequestMapping("/findNodes")
	public void findNodes(String emerId) throws IOException{
		emerId=URLDecoder.decode(emerId, "UTF-8");
		String cSelect = "";
		try {
			List<Nodes> nodeslist=nodesService.findByEmerid(emerId);
			if (nodeslist != null && nodeslist.size() > 0) {
				for (int i = 0; i < nodeslist.size(); i++) {
					Nodes nodes = nodeslist.get(i);
					cSelect += "<option value=" + nodes.getNodeid()
							+ ">" + nodes.getNodename() + "</option>";
				}
			} else {
				cSelect += "<option value=0 >该实例下无节点</option>";
			}			
		response.getWriter().write(cSelect);
		} catch (Exception e) {
		}
		
	}
	
	/** 
	* @Title: findMeasureUnit 
	* @Description: 查找物资相应的单位
	* @param modelmap
	* @param goodsId
	* @throws IOException void    返回类型    
	*/
	@ResponseBody
	@RequestMapping("/findMeasureUnit")
	public void findMeasureUnit(ModelMap modelmap,String goodsId) throws IOException{
		goodsId=URLDecoder.decode(goodsId, "UTF-8");
		String cSelect = "";
		try {
			List<Goods> goodslist2=new ArrayList<Goods>();
			goodslist2.add(goodsService.selectByPrimaryKey(goodsId));
			if (goodslist2 != null && goodslist2.size() > 0) {
				for (int i = 0; i < goodslist2.size(); i++) {
					Goods goods = goodslist2.get(i);
					cSelect += "<option value=" + goods.getGoodsid()
							+ ">" + goods.getMeasureunit() + "</option>";
				}
			} else {
				cSelect += "<option value=\"0\"单位</option>";
			}			
		response.getWriter().write(cSelect);
		} catch (Exception e) {
		}
	}
}
