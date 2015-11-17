<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    
<title>查看物资与节点供需关系</title>
<link href="<c:url value="/static/style/gov_style_10.css" />" rel="stylesheet" type="text/css">
   <script type="text/javascript" src="<c:url value="/static/js/js/jquery-1.8.0.js"/>"></script> 
<script type="text/javascript"> 
function cbt_res()
{
	document.getElementById("GoodsRelForm").reset();
	return false;
}
</script> 
 </head>
  
 <body>
<form id="GoodsRelForm" name="GoodsRel" method="post" theme="simple"  namespace="/" action="SearchGoodsRel">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">物资与节点供需关系管理&gt;&gt;查询物资与节点供需关系</td>
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
				        <option value="">全部节点</option> 
						<c:forEach var="item" items="${nodeslist}">
							<option value="${item.nodeid}" <c:if test="${item.nodeid eq nodeid}">selected</c:if>>${item.nodename}</option>
						</c:forEach>
				</select>	
				</td>	
				<td  class="tr1" align="right">*物资名称:</td>
				<td>
				<select id="goodsId" name="goodsid">
				        <option value="">所有物资</option>
						<c:forEach var="item" items="${goodslist}">
							<option value="${item.goodsid}" <c:if test="${item.goodsid eq goodsid}">selected</c:if>>${item.goodsname}</option>
						</c:forEach>
				</select>	
				<td  class="tr1" align="right">*供应或需求:</td>									
					<td><input type="radio" name="type" <c:if test="${fn:contains(type, 'S')}">checked</c:if> value="S" > 可供应 <input type="radio" name="type" value="F" <c:if test="${fn:contains(type, 'F')}">checked</c:if>>需求 </td>
				</td>		
				</tr>
			<tr class="tr10">
    	  			<td align="center" colspan="6">
	   					<input type=image src="/risk/static/images/pub/lzoa_pub_search.gif" width="67" height="19" style="cursor:hand;border:0px" ; return false;">&nbsp;&nbsp;								
	  				</td>
  				</tr>
			</table>
		</td>
    </tr>
</table>
 <table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">物资与节点供需关系管理>>查看物资与节点供需关系</td>
                </tr>
            </table></td>
          <td width="50%"></td>
        </tr>
      </table></td>
  </tr>
</table> 

<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="20"></td>
  </tr>
  <tr>
    <td width="100%" align="center">
      <table width="800" cellspacing="1" border="0" class="table1" height="95">
        <tr class="tr1" align="center">
          <td width="5%"><b>节点名称</b></td>
          <td width="5%"><b>物资名称</b></td>
          <td width="5%"><b>优先级</b></td>
          <td width="5%"><b>供应/需求</b></td>
          <td width="5%"><b>数量</b></td>        
          <td width="5%"><b>物资类型</b></td>        
          <td width="5%"><b>规格</b></td>       
          <td width="5%"><b>备注</b></td>
          <td width="3%"><b>修改</b></td>
          <td width="3%"><b>删除</b></td>
        </tr>

       <c:forEach var="item" items="${Pagelist}" varStatus="status">
        	<tr class="tr2" align="center">
        	    <td>${item.nodes.nodename}</td>
        	    <td>${item.goods.goodsname}</td>
        	    <td>
        	    <c:if test="${fn:contains(item.priority, '1')}">优先考虑</c:if>
	        	<c:if test="${fn:contains(item.priority, '2')}">一般</c:if>
	        	<c:if test="${fn:contains(item.priority, '3')}">最后考虑</c:if>
                </td>
	        	<td>
	        	<c:if test="${fn:contains(item.demandnum, '+')}">可供应</c:if>
	        	<c:if test="${fn:contains(item.demandnum, '-')}">需求</c:if>
	        	</td>
	        	<td>${fn:substring(item.demandnum, 1, fn:length(item.demandnum) )}${item.goods.measureunit}</td>
	        	<td>${item.goods.goodstype.grade}${item.goods.goodstype.goodstypename}</td>	        		        	      	
	        	<td>${item.goods.specification}</td>
	        		  
	        	<td>${item.note}</td>                
	    		<td><a href="EditGoodsRel?goodsReid=${item.goodsreid}">修改</a></td>
	    		<td><a href="DeleteGoodsRel?goodsReid=${item.goodsreid}&nodeid=${nodeid}&goodsid=${goodsid}&type=${type}">删除</a></td>	
	    	</tr>
		</c:forEach>
      </table>    
    </td>
 </tr>
</table>
</form>
<div align="right""><%@ include file="../page.jsp"%></div> 
</body>
</html>
