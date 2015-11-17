<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> 
	
	<title>Hello! Admin</title>

	 <link type="text/css" href="<c:url value="/static/css/css/style.css" />" rel="stylesheet" />  <!-- the layout css file -->
	 <script type='text/javascript' src="<c:url value="/static/js/js/jquery-1.4.2.min.js" />" ></script> 	<!-- jquery library -->
	<script type='text/javascript' src="<c:url value="/static/js/js/cufon-yui.js" />" ></script> <!-- Cufon font replacement -->
	<script type='text/javascript' src="<c:url value="/static/js/js/custom.js" />" ></script> <!-- the "make them work" script -->
</head>
<body>   
<div id="primary_left">
        
				<div id="logo">
					<img src="/risk/static/assets/logo_bg.png"  alt="" />
				</div> <!-- logo end -->

				<div id="menu"> <!-- navigation menu -->
					<ul>
						<c:forEach var="splist11" items="${splist1}" >
					<li>
							 <a href="topa?popedomId=<c:out value="${splist11.popedemid}"/>&level=1" target="mainNavigation">
							<img src="<c:out value="/risk/static/${splist11.photo}"/>"  alt="" />
							<span class="current">
					 	 <c:out value="${splist11.popedomname}"/>  
							<%--  <a href="topa?popedomId=<c:out value="${splist11.popedemid}"/>&level=1" target="mainNavigation">
								 <c:out value="${splist11.popedomname}"/>   --%>
							</a> 
							</span>
						</a>
						<%-- <ul>
							<c:forEach var="splist21" items="${splist2}" >
								<c:if test="${fn:contains( splist21.parentid,splist11.popedemid)}">
									<li>
										<a href="topa?popedomId=<c:out value="${splist21.popedemid}"/>&level=2" target="mainNavigation">
											<c:out value="${splist21.popedomname}"/> 
										</a> 
									</li>	
								</c:if>
							</c:forEach>
						</ul> --%>
					</li>
					</c:forEach>
					</ul>
				</div> <!-- navigation menu end -->
			</div> <!-- sidebar end -->

</body>
</html>

