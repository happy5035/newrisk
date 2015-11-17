<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<meta name="keywords" content="jquery动画菜单熔岩灯菜单效果" />
<meta name="description" content="jquery特效,js特效,flash特效,div+css教程,html5教程" />

<script type="text/javascript" src="<c:url value="/static/js/js/jquery.js" />" ></script>
<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
/* meun */
.meun{position:absolute;height:30px;width:100%;padding-left:13px;background:url("static/assets/bg-login.gif") }
.meun_bg{ position:absolute;top:0px;left:0px;background:url("static/assets/image87.png");height:8px;width:100%; n}
.meun li{float:left;}
.meun li.back{background:url("static/assets/meun_tab.png") no-repeat;padding-left:8px;height:30px;overflow:hidden;z-index:8;position:absolute;}
.meun li.back .left{background:url("static/assets/meun_tab.png") no-repeat right 0;height:30px;float:right;width:8px;}
.meun li.back .arrow{float:left;width:92%;height:30px;position:relative;}
.meun li.back .arrow .icon{position:absolute;top:22px;left:45%;background:url("static/assets/arrow.gif") no-repeat;height:5px;width:9px;overflow:hidden;}
.meun li a{ font-family:"微软雅黑","黑体";text-decoration:none;color:#fff;font-size:14px;z-index:10;display:block;float:left;position:relative;overflow:hidden;padding:5px 33px 0;height:23px;}
.meun li a span{cursor:pointer;}
</style>
<!--演示内容开始-->
<script type="text/javascript" src="<c:url value="/static/js/js/jquery.plugins.js" />" ></script>
<script type="text/javascript">
$(function() {
	$(".meun").lavaLamp({
		fx: "backout", 
		speed: 700,
		click: function(event, menuItem) {
			return true;
		}
	});
	
});
$(document).ready(function(){
	parent.main.location.href ="<c:out value="${splist3[0].url}"/>";
});
</script>
<!--[if lt IE 7]>
<style type="text/css">
.meun_bg{background:none!important; filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='images/image87.png',sizingMethod='crop')}
</style>
<![endif]-->
</head>

<body>
	<div class="headeline"></div>
	
	<div class="meun">
	<c:if test="${splist3 != null && fn:length(splist3)> 0}">
		<div class="meun_bg"></div>
		
			<ul>
			
			<c:forEach var="popedem" items="${splist3}">
				<li><a href= "<c:out value="${popedem.url}"/>" target="main"><span><c:out value="${popedem.popedomname}"/></span></a></li>
			
			</c:forEach>
			</ul>
	</c:if>
		</div>
	
</body>
</html>
