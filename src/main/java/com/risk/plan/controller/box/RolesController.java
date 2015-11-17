package com.risk.plan.controller.box;

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
import com.risk.plan.entity.Popedem;
import com.risk.plan.entity.RolePopedem;
import com.risk.plan.entity.Roles;
import com.risk.plan.service.box.roles.RolesPopedemService;
import com.risk.plan.service.box.users.PopedemService;
import com.risk.plan.service.box.users.RolesService;
import com.risk.plan.util.Identities;

@Controller
public class RolesController {
	@Autowired
	RolesPopedemService rolesPopedemService;
	@Autowired
	RolePopedem rolepopedem;
	@Autowired
	RolesService rolesService;
	@Autowired
	PopedemService popedemService;
	@Autowired
	private HttpServletRequest request;
	@RequestMapping("/listRolesAction")
	public String listRolesAction(ModelMap modelmap, String page ){
		StringBuffer url=new StringBuffer();
		//把action跳转的地址存在url中
		url.append(request.getContextPath()+"/listRolesAction?method=listRoles");
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
			params.put("sortName", "roleid");
	        params.put("pageSize", pageSize);
		    params.put("startNum", startNum);
			int num=rolesService.findByParamsCount(null);
			List<Roles> listRoles=rolesService.findByParams(params);
			PageBean PageBean=new PageBean(current,pageSize,num,listRoles); 
			PageBean.setUrl(url.toString());
			modelmap.put("pageBean", PageBean);
			modelmap.put("PageList", PageBean.getList());
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error/404";
		}
		
		return "WebRoot/roles/listRole";
	}
	
	@RequestMapping("/gotoaddRolesAction")
	public String gotoaddRolesAction(ModelMap modelmap){
		List<Popedem> listpopedem= popedemService.selectAll();
		modelmap.put("listpopedem", listpopedem);
		return "WebRoot/roles/addRoles";
	}
	@RequestMapping("/saveRolesAction")
	public String saveRolesAction(Roles roles,String[] nodeids){
		rolesService.insert(roles);
		for (String string : nodeids) {
			if (string.length()>0) {
				rolepopedem.setRpid(Identities.uuid2());
				rolepopedem.setRoleid(roles.getRoleid());
				rolepopedem.setPopedemid(string);
				rolesPopedemService.insert(rolepopedem);
			}
		}
		System.out.println(nodeids);
		return "WebRoot/roles/addSuccess";
	}
	
}
