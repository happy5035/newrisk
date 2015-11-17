package com.risk.plan.controller.box;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.weaver.Utils;
import org.hamcrest.core.IsEqual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.risk.plan.entity.Popedem;
import com.risk.plan.entity.Roles;
import com.risk.plan.entity.UserRole;
import com.risk.plan.entity.Users;
import com.risk.plan.service.box.users.PopedemService;
import com.risk.plan.service.box.users.RolesService;
import com.risk.plan.service.box.users.UserRoleService;
import com.risk.plan.service.box.users.UsersService;
import com.risk.plan.service.system.ValidateCode;
import com.risk.plan.util.Identities;

@Controller
public class LoginController {
	private String code;
	private String password;
	private String id;
	private ModelMap modelmap;
	@Autowired
	UsersService usersService;
	@Autowired
	UserRoleService userroleservice;
	@Autowired
	RolesService rolesService;
	@Autowired
	PopedemService popedemService;
	@Autowired
	HttpServletRequest request;
	
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(ModelMap modelmap, Users users ,String validate){
		request.getSession().setAttribute("user", users);
		request.getSession().setAttribute("username", users.getLogname());
		String logname=users.getLogname();
		modelmap.put("users", users);
		
		if (users.getLogname() == null || users.getLogname() == "") {
			modelmap.put("sysMessge", "请登陆！");
			return "WebRoot/login";
		}
		//太复杂先注释
		/*if (!this.code.equalsIgnoreCase(validate)) {
			modelmap.put("sysMessge", "验证码错误!");
			return "WebRoot/login";
		}
		*/
		//验证是用户名是否正确
		List<Users> usersList=usersService.selectByLogname(logname);
		if (usersList == null || usersList.size() == 0) {
			modelmap.put("sysMessge", "用户名不存在，请重新输入！");
			return "WebRoot/login";
		}
		Users user = usersList.get(0);
		String userid=user.getUserid();
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("username", user.getLogname());
		if (!users.getLogpwd().equals(user.getLogpwd())) {
			modelmap.put("sysMessge", "密码错误，请重新输入！");
			return "WebRoot/login";
		}
		if (user.getUserstate().contains("0")) {
			modelmap.put("sysMessge", "此用户已被禁用！");
			return "WebRoot/login";
		}
		
		// 判断是否首次登陆
		if (user.getFirstornot().contains("0")) {
			this.password=user.getLogpwd();
			this.id=user.getUserid();
			return "WebRoot/firstlogin"; // 提示用户修改密码，跳转到修改密码的页面

		}
		//获取一级权限
		List<UserRole> crs= userroleservice.selectByUserId(userid);
		String roleid = "";
		if (crs != null && crs.size() > 0) {
			roleid = crs.get(0).getRoleid();
		}
		Roles role=rolesService.selectByPrimaryKey(roleid);
		List<Popedem> popedemList = popedemService.selectByRoleId(roleid);
		ArrayList<Popedem>  splist1 = new ArrayList<Popedem>();
		ArrayList<Popedem> splist2 = new ArrayList<Popedem>();
		ArrayList<Popedem> splist3 = new ArrayList<Popedem>();
		if (popedemList != null && popedemList.size() > 0) {
			
			for (int i = 0; i < popedemList.size(); i++) {
				Popedem popedem = popedemList.get(i);
				String grade = popedem.getGrade().toString();
				if ("1".equals(grade)) {
					splist1.add(popedem);
				} else if ("2".equals(grade)) {
					splist2.add(popedem);
				} else if ("3".equals(grade)) {
					splist3.add(popedem);
				}
			}
		}
		modelmap.put("role", role);
		modelmap.put("splist1", splist1);
		modelmap.put("splist2", splist2);
		modelmap.put("splist3", splist3);
		this.modelmap=modelmap;
		return "forward:home";
	}
	
	@RequestMapping("/loginOut")
	public String loginOut(HttpSession session ){
		session.invalidate();
		return "redirect:login";
	}
	
	@RequestMapping("/home")
	public String home(){
		return "WebRoot/frameset";
	}

	@RequestMapping("/left")
	public String left(ModelMap map){
		System.out.println(this.modelmap.get("splist2"));
		map.putAll(this.modelmap);
		return "WebRoot/left";
	}
	@RequestMapping("/mainTop")
	public String mainTop(ModelMap map){
		map.putAll(this.modelmap);
		return "WebRoot/mainTop";
	}
	@RequestMapping("/mainNavigation")
	public String mainNavigation(ModelMap map){
		map.putAll(this.modelmap);
		return "WebRoot/mainNavigation";
	}
	//获取三级菜单
	
	@RequestMapping("/topa")
	public String topa(ModelMap map,String popedomId ,String level){
		map.putAll(this.modelmap);
		map.putAll(this.modelmap);
		@SuppressWarnings("unchecked")
		List<Popedem> popedemList = (List<Popedem>) this.modelmap.get("splist3");
		// 如果级别是2，说明点击的二级权限查询三级权限
				if (level.equals("1") && popedemList != null
						&& popedemList.size() > 0) {
					List<Popedem> sp1 = new ArrayList<Popedem>();
					for (int i = 0; i < popedemList.size(); i++) {
						if (popedemList.get(i).getParentid().equals(popedomId)) {
							sp1.add(popedemList.get(i));
						}
					}
					map.put("splist3", sp1);
				}
		return "WebRoot/mainNavigation";
	}
	@RequestMapping("/gotoRetpwd")
	public String retpwd(ModelMap modelmap)
	{
		modelmap.put("oldpwd", this.password);
		return "WebRoot/retpwd";
	}
	//修改密码
	@RequestMapping("/submitretpwd")
	public String submitretpwd(String newpsd1)
	{
		Users user=usersService.selectByPrimaryKey(this.id);
		user.setLogpwd(newpsd1);
		user.setFirstornot("0");
		usersService.updateByPrimaryKey(user);
		return "ok";
	}
	
	@RequestMapping(value="/VerifyCode")
	public void validateCode(HttpServletRequest reqeust,  
            HttpServletResponse response){
		 try {
		// 设置响应的类型格式为图片格式  
        response.setContentType("image/jpeg");  
        //禁止图像缓存。  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);            
        HttpSession session = reqeust.getSession();            
        ValidateCode vCode = new ValidateCode(120,40,5,100);
        this.code=vCode.getCode();
        session.setAttribute("code", vCode.getCode());        
		vCode.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	@RequestMapping("/addUser")
	public String addUser(){
		return "WebRoot/users/addUser";
	}
	@RequestMapping("/saveUser")
	public String saveUser(Users users){
		users.setUserid(Identities.uuid2()); 
		users.setWorkstate("1");
		System.out.println(users.toString());
		usersService.insert(users);
		return "WebRoot/users/addUser";
	}
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginget(){
		return "WebRoot/login";
	}
	
	@RequestMapping("/test")
	public String test()
	{
		
//		List<Popedem> list=popedemService.selectByRoleId("2");
		
		return "WebRoot/index";
	}
	@RequestMapping("/hometest")
	public String home(ModelMap map)
	{
		System.out.println(map);
		return "test";
	}
}
