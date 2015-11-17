<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Custom TextBox - jQuery EasyUI Demo</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/easyui/1.4/themes/metro/easyui.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/static/easyui/1.4/themes/icon.css" />">
	<script type="text/javascript" src="<c:url value="/static/easyui/1.4/jquery.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/static/easyui/1.4/jquery.easyui.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/static/easyui/1.4/locale/easyui-lang-zh_CN.js" />"></script>
	<script type="text/javascript" src="<c:url value="/static/js/main.js" />"></script>
	<script type="text/javascript">
	//获取验证码    
	//function changeValidateCode(obj) {
		//var timenow = new Date().getTime();
		//document.getElementById("imgObj").src = "VerifyCode?d="
				//+ timenow;
	//}
	</script>
</head>
<body>
    <form id="uForm" action="insertstudent" method="post">
		<input name="id" type="text" placeholder="序号"/>
		<input name="name" type="text" placeholder="名字"/>
		<input name="age" type="text" placeholder="年龄"/>
		<input name="grade" type="text" placeholder="班级"/>
		<input type="submit"value="insert";>
	</form>
	<form id="gForm" action="updatestudent" method="post">
		<input name="id" type="text" placeholder="序号"/>
		<input name="name" type="text" placeholder="名字"/>
		<input name="age" type="text" placeholder="年龄"/>
		<input name="grade" type="text" placeholder="班级"/>
		<input type="submit" value="update";>
	</form>
	<form id="dForm" action="deletestudent" method="post">
		<input name="id" type="text" placeholder="序号"/>
		<input type="submit" value="delete";>
	</form>
		<form id="cForm" action="selectstudent" method="post">
		<input name="id" type="text" placeholder="序号"/>
		<input type="submit" value="select";>
	</form>

</body>
</html>