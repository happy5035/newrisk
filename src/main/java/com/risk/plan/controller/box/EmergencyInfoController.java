package com.risk.plan.controller.box;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.risk.plan.common.PageBean;
import com.risk.plan.entity.EmerType;
import com.risk.plan.entity.Emergency;
import com.risk.plan.entity.UERelation;
import com.risk.plan.entity.Users;
import com.risk.plan.service.box.emer.EmerTypeService;
import com.risk.plan.service.box.emer.EmergencyService;
import com.risk.plan.service.box.emer.UeRelationService;
import com.risk.plan.service.box.users.UsersService;
import com.risk.plan.util.Identities;

@Controller
public class EmergencyInfoController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	EmerTypeService emerTypeService;
	@Autowired
	EmergencyService emergencyService;
	@Autowired
	UeRelationService ueRelationService;
	@Autowired
	UsersService usersService;
	
	@Autowired
	UERelation ueRelation;
	
	
	private List<EmerType> emerTypelist2;
	private List<EmerType> emerTypelist;
	private Emergency emergency;
	private EmerType emertype;
	
	@RequestMapping("/gotoAddEmergency")
	public String gotoAddEmergency(ModelMap modelmap){
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
		this.emerTypelist2= emerTypelist2;
		modelmap.put("emerTypeName",emertypenames);
		modelmap.put("emerTypelist",emerTypelist);
		modelmap.put("emerTypelist2",emerTypelist2 );
		return "WebRoot/Emergency/addEmergency";
	}
	@RequestMapping("/saveEmergency")
	public String saveEmergency(Emergency emergency){
		Users user=(Users)request.getSession().getAttribute("user");
		emergency.setEmerid(Identities.uuid2());
		emergencyService.insert(emergency);
		//将用户与风险事件匹配起来
		ueRelation.setUerelationid(Identities.uuid2());
		ueRelation.setEmerid(emergency.getEmerid());
		ueRelation.setUserid(user.getUserid());
		ueRelation.setState("1");   //是自己创建的，而不是被别人授权的
		ueRelationService.insert(ueRelation);
		return "forward:gotoAddEmergency";
	}
	@ResponseBody
	@RequestMapping("/findDescribe")
	public void findDescribe(ModelMap modelmap,String emerTypeId) throws IOException{
		EmerType emertype= emerTypeService.selectByPrimaryKey(emerTypeId);
		List<EmerType> emerTypelist2=new ArrayList<EmerType>();
		emerTypelist2.add(emertype);
		String cSelect="";
	 	emerTypeId=URLDecoder.decode(emerTypeId, "UTF-8");
	 	request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		if (emerTypelist2 != null && emerTypelist2.size() > 0) {
			for (int i = 0; i < emerTypelist2.size(); i++) {
				EmerType type = emerTypelist2.get(i);
				cSelect += "<option value=" + type.getEmertypeid()
						+ ">" + type.getDescribe() + "</option>";
			}
		} else {
			cSelect += "<option value=\"0\">无符合该类型的风险事件</option>";
		}	
		response.getWriter().write(cSelect);
	}
	@RequestMapping("/GotoSearchEmergency")
	public String gotoSearchEmergency(ModelMap modelmap,Emergency emergency,String page){
		Users user=(Users)request.getSession().getAttribute("user");
		String userid=user.getUserid();
		/*String usertype=user.getUsertype();*/
		
		String emerno=emergency.getEmerno();
		String emername=emergency.getEmername();
		StringBuffer url=new StringBuffer();
		//把action跳转的地址存在url中
		url.append(request.getContextPath()+"/GotoSearchEmergency?method=GotoSearchEmergency");
		
			
		int current=1;
		if (page != null && !page.trim().equals("")) {
			current=Integer.parseInt(page);
		}
		try {
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
		    if(emerno != null && !"".equals(emerno))
	    	 {
	    		params.put("emerno", "%"+emerno+"%"); 
	    		 url.append("&emerno="+emerno);
	    	 }
			 if(emername != null && !"".equals(emername))
	    	 {
				 params.put("emername", "%"+emername+"%");
	    		 url.append("&emername="+emername);
	    	 }
			params.put("userid", userid);
			
			return "WebRoot/Emergency/listEmergency";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/404";
		}
		
	}
	
	@RequestMapping("/SearchEmergency")
	public String searchEmergency(ModelMap modelmap,Emergency emergency,String page ){
		this.emergency=emergency;
		/*this.emertype=emertype;*/
		Users user=(Users)request.getSession().getAttribute("user");
		String userid=user.getUserid(); 
		String usertype=user.getUsertype();
		
		String emerno=emergency.getEmerno();
		String emername=emergency.getEmername();
		StringBuffer url=new StringBuffer();
		//把action跳转的地址存在url中
		url.append(request.getContextPath()+"/SearchEmergency?method=SearchEmergency");
		
		int current=1;
		if (page != null && !page.trim().equals("")) {
			current=Integer.parseInt(page);
		}
		try {
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
		    if(emerno != null && !"".equals(emerno))
	    	 {
	    		params.put("emerno", "%"+emerno+"%"); 
	    		 url.append("&emerno="+emerno);
	    	 }
			 if(emername != null && !"".equals(emername))
	    	 {
				 params.put("emername", "%"+emername+"%");
	    		 url.append("&emername="+emername);
	    	 }
			params.put("userid", userid);
			params.put("usertype", usertype);
		/*	params.put("emertypeid", emergency.getEmertypeid());*/
			if(emergency.getEmername().length()>0)
				params.put("emername", emergency.getEmername());
			if(emergency.getEmerno().length()>0)
				params.put("emerno", emergency.getEmerno());
			
			int num=emergencyService.findByParamsCount(params);
			List<Emergency> listEmergency=emergencyService.findByParams(params);
			PageBean PageBean=new PageBean(current,pageSize,num,listEmergency); 
			PageBean.setUrl(url.toString());
			modelmap.put("pageBean", PageBean);
			modelmap.put("Pagelist", PageBean.getList());
			return "WebRoot/Emergency/listEmergency";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/404";
		}
	}
	
	@RequestMapping("/EditEmergency")
	public String editEmergency(ModelMap modelmap, String emerid){
		
		Emergency emergency=emergencyService.selectByPrimaryKey(emerid);
		modelmap.put("emergency", emergency);
		/*modelmap.put("emerTypeName",emertypenames);*/
		modelmap.put("emerTypelist",emerTypelist);
		modelmap.put("emerTypelist2",emerTypelist2 );
		return "WebRoot/Emergency/editEmergency";
	}
	@RequestMapping("/UpdateEmergency")
	public String updateEmergency(Emergency emergency) throws IOException{
		int resault=emergencyService.updateByPrimaryKeySelective(emergency);
		if(resault>0)
			return "redirect:GotoSearchEmergency";
		else {
			return "error/404";
		}
	}
	@RequestMapping("/DeleteEmergency")
	public String deleteEmergency(String emerid){
		int resault=emergencyService.deleteByPrimaryKey(emerid);
		if(resault >0)
			return "redirect:GotoSearchEmergency";
		else {
			return "error/404";
		}
	}
	
	@RequestMapping("/gotoAuthorize")
	public String gotoAuthorize(ModelMap modelmap){
		Users user=(Users)request.getSession().getAttribute("user");
		List<Emergency> emergencylist=null;
		
		if(user.getUsertype().equals("1")){
			 emergencylist=emergencyService.selectAll();
		}else {
			emergencylist=emergencyService.selectByUserid(user.getUserid());
		}
		modelmap.put("emergencylist", emergencylist);
		return "WebRoot/Emergency/reAuthorize";
	}
	@RequestMapping("/reAuthorize")
	public String reAuthorize(ModelMap modelmap, String emerid,Users userN){
		Emergency emergency=emergencyService.selectByPrimaryKey(emerid);
		Map<String , Object> params=new HashMap<String, Object>();
		params.put("username", userN.getUsername());
		params.put("certicobe", userN.getCerticobe());
		params.put("logname", userN.getLogname());
		params.put("logpwd", userN.getLogpwd());
		List<Users> users=usersService.findByParams(params);
		//将用户与风险事件匹配起来
		ueRelation.setUerelationid(Identities.uuid2());
		ueRelation.setEmerid(emergency.getEmerid());
		ueRelation.setUserid(users.get(0).getUserid());
		ueRelation.setState("0");   //是自己创建的，而不是被别人授权的
		ueRelationService.insert(ueRelation);
		return "forward:gotoAuthorize";
	}
	
}
