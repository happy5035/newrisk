<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试</title>
</head>
<body><%-- 
	<c:forEach var="popedem" items="${splist1}">
	<p>${popedem.popedomname}</p>
	</c:forEach>
	<c:forEach var="popedem" items="${splist2}">
	<p>${popedem.popedomname}</p>
	</c:forEach>
	<c:forEach var="popedem" items="${splist3}">
	<p>${popedem.popedomname}</p>
	</c:forEach> --%><!-- 
	<p> hello world</p> -->
	<%-- <c:if test="${splist3 != null || fn:length(splist3)> 0}">
	<c:forEach var="popedem" items="${PageList}">
	<p>test</p> --%>
	
	<%-- <p>"${fn:length(splist3)}"</p> --%>

	<%-- 
	<p>"${fn:length(splist3)}"</p>
	<p>"${users.logname}"</p> --%>
	<c:forEach var="role" items="#{PageList}">
								
								
								
								<td>
									<a href="gotoupdateRolesAction?id= <c:out value="role.roleid"/>">修改</a>
								</td>
								<td>
									<a href="deleteRoleAction?id=<c:out value="role.roleid"/>">删除</a>
								</td>
							</c:forEach>
</body>
</html>