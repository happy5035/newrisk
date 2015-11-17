package com.risk.plan.intercepor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.risk.plan.entity.Users;

public class HanderInterceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		 String url= request.getRequestURI();
		 String  urlA="http://"+request.getServerName() + ":" +request.getServerPort();
		 if(url.contains("login") || url.contains("VerifyCode") || url.contains("css") ||url.contains("js")){
			 return true;
		 }
		Users user=(Users)request.getSession().getAttribute("user");
		if(user !=null)
		{
			return true;
		}
		  PrintWriter out = response.getWriter();  
          StringBuilder builder = new StringBuilder();  
          builder.append("<script type=\"text/javascript\" charset=\"utf-8\">");  
          builder.append("alert(\" please login again!\");");  
         /* builder.append("window.top.location.href=\"");   	
          builder.append(urlA);  //这里是http://ip:port/项目名
          builder.append("/lee/login\";</script>");  //这里是重新登录的页面url
*/         
          builder.append("window.open('login','_top');");
          builder.append("</script>");
          out.print(builder.toString());  
          out.close();  
		response.sendRedirect("login");
		return false;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		ex.printStackTrace();
		// TODO Auto-generated method stub
		
	}

}
