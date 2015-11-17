<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>角色信息列表</title>
		<style type="text/css">
body {
	text-align: center;
}
</style>
		<link href="<c:url value="/static/style/gov_style_10.css" />"
			rel="stylesheet" type="text/css">
		<link href="/studentInformationWeb/style/gov_style_10.css"
			rel="stylesheet" type="text/css">
		<script type="text/javascript">
	
</script>
	</head>

	<body topmargin="0" leftmargin="0">
		<form action="listRolesAction" method="post" id="">
			<table border="0" width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td width="100%" height="27" bgcolor="#E7EBFF">
						<table border="0" width="100%" cellspacing="0" cellpadding="0"
							height="27">
							<tr>
								<td width="3%">
									<img width="29" height="27"
										src="/risk/static/images/desktop/icon-main-001.gif" />
								</td>
								<td width="47%">
									<table border="0" width="100%" cellspacing="0" cellpadding="0">
										<tr>
											<td width="100%" height="4"></td>
										</tr>
										<tr>
											<td>

												角色信息列表
											</td>
										</tr>
									</table>
								</td>
								<td width="50%"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<table border="0" width="100%" cellspacing="0" cellpadding="0">
				
				<tr>
					<td width="100%" align="center">
						<table width="800" cellspacing="1" border="0" class="table1"
							height="95">
							
							<tr class="tr1" align="center">
								<td>
									序号
								</td>
								<td>
									角色名
								</td>
								<td>
									备注
								</td>
								<td colspan="2">
									操作
								</td>
							</tr>
							<%
								int i = 1;
							%>
							<c:forEach var="role" items="#{PageList}">
							<tr class="tr2" align="center">
								<td><%=i++%></td>
								<td>
									<c:out value="${role.rolename}"/>
								</td>
								<td>
									<c:out value="${role.remark}"/>
								</td>
								<td>
									<a href="gotoupdateRolesAction?id= <c:out value="${role.roleid}"/>">修改</a>
								</td>
								<td>
									<a href="deleteRoleAction?id=<c:out value="${role.roleid}"/>">删除</a>
								</td>
							</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</table>
			<center>
			</center>
		</form>
		<div align="right"><%@ include file="../page.jsp"%></div>
	</body>
</html>

