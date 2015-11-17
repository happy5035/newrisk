<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>角色息管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<c:url value="/static/style/gov_style_10.css" />">
	<link rel="stylesheet" href="<c:url value="/static/css/css/zTreeStyle/zTreeStyle.css" />" type="text/css">
	<script type="text/javascript" src="<c:url value="/static/js/js/jquery-1.4.4.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/static/js/js/jquery.ztree.core-3.4.js" />"></script>
	<script type="text/javascript" src="<c:url value="/static/js/js/jquery.ztree.excheck-3.4.js" />" ></script>

<script type="text/javascript">
function cbt_res()
{
	document.getElementById("roleform").reset();
	return false;
}
	var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
				
			}
		};
					
		var zNodes =[
		             <c:forEach var="popedem" items="${listpopedem}"  varStatus="status">
		             {id:"<c:out value="${popedem.popedemid}"/>", pId:"<c:out value="${popedem.parentid}"/>", name:"${popedem.popedomname}"}
						<c:if test="${!status.last}">
						,
						</c:if>
		             </c:forEach>    
			
		];
		
		function getChecked(){
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = treeObj.getCheckedNodes(true);
			var nodeids = "";
			for(var i = 0; i< nodes.length;i++ ){
				nodeids += nodes[i].id+",";
			}
			alert(nodeids);
			roleform.action = "saveRolesAction?nodeids="+nodeids;
			roleform.submit();
		}
	
		function setCheck() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			setting.check.chkboxType = { "Y" : "ps", "N" : "ps" };
		}
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			setCheck();
		
		});
</script>

  </head>
  
  <body onload="">
  
<form action="saveRolesAction" id="roleform" method="post"  name="roleform">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">角色息管理>>添加角色</td>
                </tr>
            </table></td>
          <td width="50%"></td>
        </tr>
      </table></td>
  </tr>
</table>
<table width="50%" border="0" cellspacing="1" cellpadding="3" align="center">
	<tr>
		<td align="center" class="tr4"> 
			<table border="0" cellpadding="3" cellspacing="1" class="table3">
			   <tr class="tr2">
				<td  class="tr1">流水号:</td>
					<td><input type="text" name="roleid" ></td>
				</tr>
				<tr class="tr2">
					<td  class="tr1">角色名称:</td>
					<td><input type="text" name="rolename" ></td>				
				</tr>
				<tr class="tr2">
					<td  class="tr1">备注:</td>
					<td><input type="text" name="remark" ></td>				
				</tr>
				<tr class="tr2">
					<td  class="tr1">权限:</td>
			<td align="left">
					
					<ul id="treeDemo" class="ztree"></ul>
					
			</td>
				</tr>
			<tr class="tr10">
    	  			<td align="center" colspan="4">
	   					<input type="button" value="提交" onclick="return getChecked();"> &nbsp;&nbsp;	
						<input type="image" src="/risk/static/images/pub/lzoa_pub_reset.gif" width="67" height="19" style="cursor:hand" onClick="return cbt_res();">
	   					<a href="javascript:history.back(-1);"><img border="0" src="/risk/static/images/pub/lzoa_pub_back.gif"/></a>         
	  				</td>
  				</tr>
  
			</table>
		</td>
    </tr>
</table>
      
	<table width="50%" border="0" cellpadding="3" cellspacing="1" class="table3">
	    <tr class="tr10">
			<td align="center" ><a href="listRolesAction">查看所有</a></td>			
		</tr>	
</table>
</form> 
</body>
</html>
