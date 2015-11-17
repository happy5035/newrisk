<%-- <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>风险规划系统平台欢迎您！</title>
   <link rel="stylesheet" type="text/css" href="<c:url value="/static/easyui/1.4/themes/metro/easyui.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/static/easyui/1.4/themes/icon.css" />">
	<script type="text/javascript" src="<c:url value="/static/easyui/1.4/jquery.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/static/easyui/1.4/jquery.easyui.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/static/easyui/1.4/locale/easyui-lang-zh_CN.js" />"></script>
	<script type="text/javascript" src="<c:url value="/static/js/main.js" />"></script>
	</head>	
	<body>
		<div id="background">
			<div id="container">			
				<div id="box">
					<form action="login.action" method="POST" id="logform" name="logform">
						<div class="one_half">
							<p>
								<input name="logname" id="logname" value="用户名" class="field"/>
							</p>
							<p>
								<input name="validate" id="validate" value="验证码" class="captcha" />
								<img height="20" id="imgObj" title="点击我,换一个！"
									src="VerifyCode.action" onclick="changeValidateCode()"
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
		<script type="text/javascript">
		//获取验证码    
	function changeValidateCode(obj) {
		var timenow = new Date().getTime();
		document.getElementById("imgObj").src = "VerifyCode.action?d="
				+ timenow;
	}

	//登陆验证
	
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
		
	
	function gotoAddUser(){
		document.getElementById("logform").action = "addUser.action";
		document.getElementById("logform").submit();
	}
	</script>
	</body>
<style type="text/css">
        #background{
        width:100%; height:100%; font-size:12px; 
        background: url("/test.jpg"); 
        background-position:top center; 
        background-repeat:no-repeat;
        }
    </style>
</html>
