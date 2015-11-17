package com.risk.plan.controller.box;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.risk.plan.common.PageBean;
import com.risk.plan.entity.Roles;
import com.risk.plan.entity.Users;
import com.risk.plan.service.box.users.UsersService;

@Controller
public class UserController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	UsersService userService;
	@RequestMapping("/UserMyself")
	public String userMyself(ModelMap modelmap){
		Users user=(Users)request.getSession().getAttribute("user");
		modelmap.put("users", user);
		return "WebRoot/users/editUser";
	}
	
	@RequestMapping("/listUser")
	public String listUser(ModelMap modelmap,String page,Users user){
		String username=user.getUsername();
		String usertype=user.getUsertype();
		String userstate=user.getUserstate();
		StringBuffer url=new StringBuffer();
		//把action跳转的地址存在url中
		url.append(request.getContextPath()+"/listUser?method=listUser");
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
			params.put("sortName", "userid");
	        params.put("pageSize", pageSize);
		    params.put("startNum", startNum);
		    if(username != null && !"".equals(username))
	    	 {
	    		params.put("username", "%"+username+"%"); 
	    		 url.append("&username="+username);
	    	 }
			 if(usertype != null && !"".equals(usertype))
	    	 {
				 params.put("usertype", "%"+usertype+"%");
	    		 url.append("&usertype="+usertype);
	    	 }
			 if(userstate != null && !"".equals(userstate))
	    	 {
				 params.put("userstate", "%"+userstate+"%");          
	    		 url.append("&userstate="+userstate);
	    	 }	
			int num=userService.findByParamsCount(params);
			List<Users> listRoles=userService.findByParams(params);
			PageBean PageBean=new PageBean(current,pageSize,num,listRoles); 
			PageBean.setUrl(url.toString());
			modelmap.put("pageBean", PageBean);
			modelmap.put("PageList", PageBean.getList());
			return "WebRoot/users/listUser";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/404";
		}
		
	}
	@RequestMapping("/listUserDetail")
	public String listUserDetail(ModelMap modelmap,String userid){
		Users user= userService.selectByPrimaryKey(userid);
		modelmap.put("userlogin", user);
		return "WebRoot/users/listUserDetail";
	}
	@RequestMapping("/editUser")
	public String editUser(ModelMap modelmap ,String userid){
		Users user= userService.selectByPrimaryKey(userid);
		modelmap.put("users", user);
		return "WebRoot/users/editUser";
	}
	@RequestMapping("/deleteUser")
	public String deleteUser(ModelMap modelmap ,String userid){
		Users user= userService.selectByPrimaryKey(userid);
		int resault=0;
		if(!user.getUserid().equals("")&& userid.equals(user.getUserid()))
		{ 
			 resault= userService.deleteByPrimaryKey(userid);
		}
		if(resault>0)
			return "forward:/listUser";
		else 
			return "error/404";
	}
}

	
	