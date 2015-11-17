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
    
<title>查看物资</title>
<link href="<c:url value="/static/style/gov_style_10.css" />" rel="stylesheet" type="text/css">
   <script type="text/javascript" src="<c:url value="/static/js/js/jquery-1.8.0.js"/>"></script> 
<script type="text/javascript"> 

            $(document).ready(function(){          	
		    $( "#goodsTypeName" ).change(function(){
		    $.ajax({
		    	type: "POST",
		    	url: "findGrade?goodsTypeName="+encodeURI(encodeURI($(this).val())),
		    	cache: false,
		    	async: false,
		    	success: function(data){
		    		$("#goodsTypeId").html(data);
		    	}});
		    }); 
     }); 

</script> 
 </head>
  
 <body>
<form id="GoodsForm" name="GoodsForm" method="post" theme="simple"  namespace="/" action="SearchGoods">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">物资管理>>按条件查询物资</td>
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
					<td  class="tr1" align="right">物资名称:</td>
					<td><input type="text" name="goodsname" value=${goodsname}></td>
					<td  class="tr1" align="right">物资编号:</td>
					<td><input type="text" name="goodsno" value=${goodsno}></td>
					<td  class="tr1" align="right">*物资类型:</td>									
					<td>
					<select id="goodsTypeName" name="goodstypename">
                        <c:forEach var="item" items="${goodstypename}">
							<option value="${item}" <c:if test="${item eq goodstype.goodstypename}">selected</c:if>>${item}</option>
						</c:forEach>
     				</select>										
					<select id="goodsTypeId" name="goodstypeid">
						<c:forEach var="item" items="${goodstypelist}">
							<option value="${item.goodstypeid}" <c:if test="${item.goodstypeid eq goodstype.goodstypeid}">selected</c:if>>${item.grade}</option>
						</c:forEach>
					</select>
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
					<td width="100%" class="f3">物资管理>>查看所有物资</td>
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
          <td width="5%"><b>物资名称</b></td>
          <td width="5%"><b>物资编号</b></td>
          <td width="5%"><b>物资类型</b></td>
          <td width="5%"><b>主要用途</b></td>
          <td width="5%"><b>计量单位</b></td>
          <td width="5%"><b>保质期</b></td>
          <td width="5%"><b>规格</b></td>
          <td width="5%"><b>尺寸大小</b></td>
          <td width="5%"><b>备注</b></td>
          <td width="3%"><b>修改</b></td>
          <td width="3%"><b>删除</b></td>
        </tr>

       <c:forEach var="item" items="${Pagelist}" varStatus="status">
        	<tr class="tr2" align="center">
	        	<td>${item.goodsname}</td>
	        	<td>${item.goodsno}</td>
	        	<td>${item.goodstype.grade}${item.goodstype.goodstypename}</td>	        	
	        	<td>${item.mainuse}</td>
	        	<td>${item.measureunit}</td>
	        	<td>${item.guaranteeperiod}</td>	        	
	        	<td>${item.specification}</td>
	        	<td>${item.size}</td>
	        	<td>${item.note}</td>                
	    		<td><a href="EditGoods?goodsid=${item.goodsid}">修改</a></td>
	    		<td><a href="deletegoods?goodsid=${item.goodsid}&goodsname=${goodsname}&goodsno=${goodsno}&goodstypeid=${goodstypeid}">删除</a></td>	
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
