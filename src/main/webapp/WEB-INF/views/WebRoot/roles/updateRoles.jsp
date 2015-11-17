<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改角色信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/style/gov_style_10.css">
	<link href="/studentInformationWeb/style/gov_style_10.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.core-3.4.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.excheck-3.4.js"></script>

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
			<s:iterator value="#request.listpopedem" id="popedem" status='st'>
				{id:"<s:property value="#popedem.popedemId"/>", pId:"<s:property value="#popedem.parentId"/>", name:"${popedem.popedomName}"
				<s:iterator value="#request.rolmap"><s:if test="%{#popedem.popedemId==key}">, checked:true </s:if> </s:iterator>
				}
				<s:if test="%{!#st.last}">
					,
				</s:if>
			</s:iterator>
			
		];
		
		function getChecked(){
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = treeObj.getCheckedNodes(true);
			var nodeids = "";
			for(var i = 0; i< nodes.length;i++ ){
				nodeids += nodes[i].id+",";
			}
			roleform.action = "updateRolesAction.action?nodeids="+nodeids;
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
  
<form action="updateRolesAction.action" id="roleform" method="post"  name="roleform">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="<%=request.getContextPath()%>/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">角色管理>>修改角色信息</td>
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
			  	 <input type="hidden"  name="roleid" value="<s:property value="#request.roles.roleId" />" >
				<tr class="tr2">
					<td  class="tr1">角色名称:</td>
					<td><input type="text" name="rolename" value="${roles.roleName}"  maxlength="40"></td>				
				</tr>
				<tr class="tr2">
					<td  class="tr1">备注:</td>
					<td><input type="text" name="remark" value="<s:property value="#request.roles.remark" />" maxlength="40"></td>				
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
						<input type="image" src="<%=request.getContextPath()%>/images/pub/lzoa_pub_reset.gif" width="67" height="19" style="cursor:hand" onClick="return cbt_res();">
	   					<a href="javascript:history.back(-1);"><img border="0" src="<%=request.getContextPath()%>/images/pub/lzoa_pub_back.gif"/></a>         
	  				</td>
  				</tr>
  
			</table>
		</td>
    </tr>
</table>
      
	<table border="0" cellpadding="3" cellspacing="1" class="table3">
	    <tr class="tr10">
			<td align="center" ><a href="listRolesAction.action">查看所有</a></td>			
		</tr>	
</table>
</form> 
</body>
</html>
