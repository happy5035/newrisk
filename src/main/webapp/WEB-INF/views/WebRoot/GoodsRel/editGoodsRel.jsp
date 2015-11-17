<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link href="<c:url value="/static/style/gov_style_10.css" />" rel="stylesheet" type="text/css">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
   <script type="text/javascript" src="<c:url value="/static/js/js/jquery-1.8.0.js"/>"></script> 
<script type="text/javascript">
function cbt_res()
{
  document.forms[0].reset();
  return false;
}
</script>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改物资与节点供需关系</title>
    
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
 <form name="GoodsForm" action="UpdateGoodsRel" method="post" >
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">物资与节点供需关系管理>>修改物资与节点供需关系</td>
                </tr>
            </table></td>
          <td width="50%"></td>
        </tr>
      </table></td>
  </tr>
</table> 
<table width="100%" border="0" cellspacing="1" cellpadding="3">
	<tr>
		<td align="center" class="tr4"> 
			<table border="0" cellpadding="3" cellspacing="1" class="table3">			
			<tr class="tr2">
				<td  class="tr1" align="right">*节点名称:</td>
				<td>
				<select id="nodeId" name="nodeid">
						<c:forEach var="item" items="${nodeslist}">
							<option value="${item.nodeid}" <c:if test="${item.nodeid eq goodsrel.nodes.nodeid}">selected</c:if>>${item.nodename}</option>
						</c:forEach>
				</select>	
				</td>	
				<td  class="tr1" align="right">*物资名称:</td>
				<td>
				<select id="goodsId" name="goodsid">
						<c:forEach var="item" items="${goodslist}">
							<option value="${item.goodsid}" <c:if test="${item.goodsid eq goodsrel.goods.goodsid}">selected</c:if>>${item.goodsname}</option>
						</c:forEach>
				</select>	
				</td>	
			</tr>
			<tr class="tr2">
					<td  class="tr1" align="right">*供应或需求:</td>									
					<td><input type="radio" name="type" value="S" <c:if test="${fn:contains(goodsrel.demandnum, '+')}">checked</c:if>>可供应 <input type="radio" name="type" value="F" <c:if test="${fn:contains(goodsRel.demandNum, '-')}">checked</c:if>>需求 </td>					
					<td  class="tr1" align="right" >*数量:</td>
					<td><input type="text" name="demandNum" value="${fn:substring(goodsrel.demandnum, 1, fn:length(goodsrel.demandnum) )}">${goodsrel.goods.measureunit}</td>			
			</tr>
			<tr class="tr2">
					<td  class="tr1" align="right">*优先级:</td>
					<td><input type="radio" name="priority" value="1" <c:if test="${fn:contains(goodsrel.priority, '1')}">checked</c:if>>优先考虑 <input type="radio" name="priority" value="2" <c:if test="${fn:contains(goodsrel.priority, '2')}">checked</c:if>>一般<input type="radio" name="priority" value="3" <c:if test="${fn:contains(goodsRel.priority, '3')}">checked</c:if>>最后考虑</td>					
					<td  class="tr1" align="right">*备注:</td>
					<td><input type="text" name="note" value="${goodsrel.note}"></td>				
				</tr>
				<tr class="tr10">
    	  			<td align="center" colspan="4">
	   					<input type=image src="/risk/static/images/pub/lzoa_pub_save.gif" width="67" height="19" style="cursor:hand;border:0px" ; return false;">&nbsp;&nbsp;		
						<input type="image" src="/risk/static/images/pub/lzoa_pub_reset.gif" width="67" height="19" style="cursor:hand" onClick="return cbt_res();">
	   					<a href="javascript:history.back(-1);"><img border="0" src="/risk/static/images/pub/lzoa_pub_back.gif"/></a>         
	  				</td>
  				</tr>
			</table>
		</td>
    </tr>
</table>
<input type="hidden" name="goodsreid" id="goodsReId" value="${goodsrel.goodsreid}"/> 
</form>  
   
   
   
  </body>
</html>
