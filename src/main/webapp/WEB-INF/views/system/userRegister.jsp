<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/css/main.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/easyui/1.4/themes/metro/easyui.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/static/easyui/1.4/themes/icon.css" />">
	<script type="text/javascript" src="<c:url value="/static/easyui/1.4/jquery.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/static/easyui/1.4/jquery.easyui.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/static/easyui/1.4/locale/easyui-lang-zh_CN.js" />"></script>
	<script type="text/javascript" src="<c:url value="/static/js/main.js" />"></script>
</head>
<body>
	<form id="addUserForm" action="<c:url value="/user/add" />" method="post" >
	    <h2>快速注册</h2>
	    <p>The textbox allows a user to enter information.</p>
	    <div style="padding:30px 600px 600px 700px;">
		    <div class="easyui-panel" title="用户注册" style="width:400px;padding:30px 60px;">
		        <div style="margin-bottom:20px">
		            <div>登录名称</div>
		            <input id="loginname" name="loginname" class="easyui-textbox"  style="width:100%;height:32px">
		        </div>
		        <div style="margin-bottom:20px">
		            <div>登录密码:</div>
		            <input id="loginpwd" name="loginpwd" class="easyui-textbox" style="width:100%;height:32px">
		        </div>
		        <div style="margin-bottom:20px">
		            <div>确认登录密码:</div>
		            <input id="reloginpwd" name="reloginpwd" class="easyui-textbox" style="width:100%;height:32px">
		        </div>
		        <div style="margin-bottom:20px">
		            <div>手机号码:</div>
		            <input id="telephonenum" name="telephonenum" class="easyui-textbox" style="width:100%;height:32px">
		        </div>
		        <div style="margin-bottom:20px">
		            <div>邮箱:</div>
		            <input id="email" name="email" class="easyui-textbox" data-options="prompt:'请输入有效的email',validType:'email'" style="width:100%;height:32px">
		        </div>
		        
		        <div>
		            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok"
		             style="width:100%;height:32px" onclick="submitForm('#addUserForm')">注册</a>
		        </div>
		    </div>
	    </div>
    </form>
</body>
</html>
