<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>风险规划系统平台欢迎您！</title>

		<link type="text/css" href="<c:url value="/static/css/css/style.css" />" rel="stylesheet" /> 
		<link type="text/css" href="<c:url value="/static/css/css/login.css"/>" rel="stylesheet" />
		<link type="text/css" href="<c:url value="/static/css/css/jquery.cleditor.css"/>" rel="stylesheet" />
		
	<script type='text/javascript' src="<c:url value="/static/js/js/jquery-1.4.2.min.js"/>"></script>	<!-- jquery library -->
	<script type='text/javascript' src="<c:url value="/static/js/js/jquery-ui-1.8.5.custom.min.js" />"></script> <!-- jquery UI -->
	<script type='text/javascript' src="<c:url value="/static/js/js/jquery.tablesorter.min.js" />"></script> <!-- tablesorter -->
	<script type='text/javascript' src="<c:url value="/static/js/js/cufon-yui.js" />"></script> <!-- Cufon font replacement -->
	<script type='text/javascript' src="<c:url value="/static/js/js/ColaborateLight_400.font.js" />"></script> <!-- the Colaborate Light font -->
	<script type='text/javascript' src="<c:url value="/static/js/js/easyTooltip.js" />"></script> <!-- element tooltips -->


	<script type="text/javascript">
		//获取验证码    
	function changeValidateCode(obj) {
		var timenow = new Date().getTime();
		document.getElementById("imgObj").src = "VerifyCode?d="
				+ timenow;
	}

	//登陆验证
	
	$(function() {
		var sysmessage = "${sysMessge }";
		if(sysmessage != null && sysmessage != ""){
			$("#sysMessage").show();
		}
		$("#imageSubmit").click(function() {
			if ($("#logname").val() == "用户名" || $("#logname").val() == "") {
				$("#sysMessgeinfo").html("<strong>提示!</strong>请输入用户名！");
				$("#logname").focus();
				return false;
			}
			if ($("#logpwd").val() == "密码" || $("#logpwd").val() == "") {
				$("#sysMessgeinfo").html("<strong>提示!</strong>请输入密码！");
				$("#logpwd").focus();
				return false;
			}
			if ($("#validate").val() == "验证码" || $("#validate").val() == "" ) {
				$("#sysMessgeinfo").html("<strong>提示!</strong>请输入验证码！");
				$("#validate").focus();
				return false;
			}

		});

		$("#logname").focus(function() {
			$("#sysMessgeinfo").html("<strong>提示!</strong>请输入用户名！");
			$("#sysMessage").show();
			if ($("#logname").val() == "用户名") { 
				$("#logname").val("");
			} 

		});
		$("#logname").blur(function() {
			if ($("#logname").val() == "") { 
				$("#logname").val("用户名");
			} 

		});
		$("#logpwd").focus(function() {

			$("#sysMessgeinfo").html("<strong>提示!</strong>请输入密码！");
			$("#sysMessage").show();
			if ($("#logpwd").val() == "密码") { 
				$("#logpwd").val("");
			}
		});
		$("#logpwd").blur(function() {
			if ($("#logpwd").val() == "") { 
				$("#logpwd").val("密码");
			} 

		});
		$("#validate").focus(function() {

			$("#sysMessgeinfo").html("<strong>提示!</strong>请输入验证码！");
			$("#sysMessage").show();
			if ($("#validate").val() == "验证码") { 
				$("#validate").val("");
			}
		});
		$("#validate").blur(function() {
			if ($("#validate").val() == "") { 
				$("#validate").val("验证码");
			} 

		});	
	});
	if(window!=top){
	 top.location.href=location.href;
	}
	function gotoAddUser(){
		document.getElementById("logform").action = "addUser";
		document.getElementById("logform").submit();
	}
	</script>
	<style type="text/css">
        body{width:100%; height:100%; font-size:12px; background: url("/risk/static/assets/logologin.png"); background-position:top center; background-repeat:no-repeat;}
    </style>
	</head>
	<body>
		<div id="background">
			<div id="container">			
				<div id="box">
					<form action="login" method="POST" id="logform" name="logform">
						<div class="one_half">
							<p>
								<input name="logname" id="logname" value="用户名" class="field"/>
							</p>
							<p>
								<input name="validate" id="validate" value="验证码" class="captcha" />
								<img height="30"  id="imgObj" title="点击我,换一个！"
									src="VerifyCode" onclick="changeValidateCode()"
									class="verify-code-img" />
							</p>
						</div>
						<div class="one_half last">
							<p>
								<input type="password" name="logpwd" id="logpwd" class="field" value="密码"/>
							</p>
							<p style="color:#123456;font-size:25px;">
							   <td><input type="radio" name="logtype" value="0" checked="checked">普通用户 </td>							  
							</p>
							<p>
								<input type="submit" value="登录" class="login" id="imageSubmit"  />
								<input type="button" value="注册" class="login" id="imageRegister" onclick="gotoAddUser()" />
							</p>
						</div>
					</form>
				</div>

			</div>

			<div id="sysMessage" class="notification info sysMessge" sytle="display:none;" >			
				<div class="text" >
					<p id="sysMessgeinfo"  style="text-align:center;vertical-align:center;color:#123456;font-size:15px;line-height:150%;">
						<strong>提示!</strong>${sysMessge }
					</p>
				</div>
			</div>
		</div>
	</body>
</html>
