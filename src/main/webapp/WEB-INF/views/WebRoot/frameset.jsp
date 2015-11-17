<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC
"-//W3C//DTD XHTML 1.0 Frameset//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>

    <frameset  cols="215,*" border="0"> 
        <frame name="left" src="left" scrolling="no"  frameborder="0" noresize="noresize" border="0" /> 
        <frameset rows=120,30,*" > 
            <frame src="mainTop" name="mainTop" frameborder="0"  scrolling="no" noresize="noresize" border="0" /> 
            <frame src="mainNavigation" name="mainNavigation" frameborder="0" scrolling="no" noresize="noresize" border="0" />
            <frame src="" name="main" frameborder="0"  noresize="noresize" border="0"/> 
    	</frameset> 
    </frameset> 
    <!-- 
     <frameset  rows="200,*" border="0"> 
       <frame src="mainTop.jsp" name="mainTop" frameborder="no" border="0"  scrolling="no" /> 
        <frameset cols="215,*" border="0"> 
            <frame name="left" src="left.jsp" border="0" scrolling="no"  frameborder="no" />  
            <frameset  rows="30,*" border="0">
             	<frame src="mainNavigation.jsp" name="mainNavigation" frameborder="no" border="0"  scrolling="no" />
            	<frame src="" name="main" frameborder="no" border="0" /> 
            </frameset> 
    	</frameset> 
    </frameset> 
	 -->
<noframes></noframes>  
</html>


