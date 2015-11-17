<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my JSP page. <br>
    <table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static//images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">风险清单项管理>>&gt;&gt;<a href="gotoAddDisaster.action">添加风险清单项</a>&nbsp;&nbsp; <a href="getAllArea.action">查看所有风险清单项及连接关系</a> &nbsp;&nbsp;<a href="GotoSearchDisasterNode.action">查看风险清单项详细信息</a></td>
                </tr>
                <tr>
					<td width="100%" class="f3">风险应对策略管理>>&gt;&gt;<a href="gotoAddWareHouse.action">添加风险应对策略</a>&nbsp;&nbsp; <a href="getAllArea.action">查看所有节点及连接关系</a> &nbsp;&nbsp;<a href="GotoSearchWareHouse.action">查看风险应对策略详细信息</a></td>
                </tr>
                <tr>
					<td width="100%" class="f3">连接边管理>>&gt;&gt;<a href="gotoAddEdge.action">添加连接边</a>&nbsp;&nbsp; <a href="getAllArea.action">查看所有风险清单项及连接关系</a> &nbsp;&nbsp;<a href="SearchPermission.action">搜索占用关系</a></td>
                </tr>
                <tr>
					<td width="100%" class="f3">物资管理>>&gt;&gt;<a href="gotoAddGoods.action">添加物资</a>&nbsp;&nbsp; <a href="GotoSearchGoods.action">查看所有物资</a></td>
                </tr>
                <tr>
					<td width="100%" class="f3">运输工具管理>>&gt;&gt;<a href="gotoAddTrsport.action">添加运输工具</a>&nbsp;&nbsp; <a href="GotoSearchTrsport.action">查看所有运输工具</a></td>
                </tr>
                <tr>
					<td width="100%" class="f3">节点与物资之间需求关系管理>>&gt;&gt;<a href="gotoAddGoodsRel.action">添加节点与物资之间需求关系</a>&nbsp;&nbsp; <a href="GotoSearchGoodsRel.action">查看所有节点与物资之间需求关系</a></td>
                </tr>
                <tr>
					<td width="100%" class="f3">风险事件管理>>&gt;&gt;<a href="gotoAddEmergency.action">添加风险事件</a>&nbsp;&nbsp; <a href="GotoSearchEmergency.action">查看所有风险事件</a></td>
                </tr>
                <tr>
					<td width="100%" class="f3">一个车辆的动画调试>>&gt;&gt;<a href="one_Trsportation.action">一个车辆的动画调试</a>&nbsp;&nbsp; <a href="GotoSearchEmergency.action">查看所有风险事件</a></td>
                </tr>
            </table></td>
          <td width="50%"></td>
        </tr>
      </table></td>
  </tr>
</table> 
  </body>
</html>
