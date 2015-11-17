<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    
<title>查看运输工具</title>
<link href="<c:url value="/static/style/gov_style_10.css" />" rel="stylesheet" type="text/css">
   <script type="text/javascript" src="<c:url value="/static/js/js/jquery-1.8.0.js"/>"></script> 
<script type="text/javascript"> 

           $(document).ready(function(){          	
		    $( "#transtype" ).change(function(){
		    $.ajax({
		    	type: "POST",
		    	url: "findLoadWeight?transtype="+encodeURI(encodeURI($(this).val())),
		    	cache: false,
		    	async: false,
		    	success: function(data){
		    		$("#tranmodelid").html(data);
		    	}});
		    }); 
     }); 

</script> 
 </head>
  
 <body>
<form id="TrsportForm" name="Trsport" method="post" theme="simple"  namespace="/" action="SearchTrsport">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">运输工具管理>>按条件查询运输工具</td>
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
					<td  class="tr1" align="right">运输工具车牌号:</td>
					<td><input type="text" name="busnumber" value=${busnumber}></td>
					<td  class="tr1" align="right">运输工具编号:</td>
					<td><input type="text" name="transno" value=${transno}></td>
					<td  class="tr1" align="right">*运输工具类型:</td>									
					<td>
					<select id="transtype" name="transtype">
                        <c:forEach var="item" items="${transtypename}">
							<option value="${item}" <c:if test="${item eq tranmodel.transtype}">selected</c:if>>${item}</option>
						</c:forEach>
     				</select>										
					<select id="tranmodelid" name="tranmodelid">
						<c:forEach var="item" items="${tranmodellist}">
							<option value="${item.tranmodelid}" <c:if test="${item.tranmodelid eq tranmodel.tranmodelid}">selected</c:if>>${item.loadweight}</option>
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
					<td width="100%" class="f3">运输工具管理>>查看所有运输工具</td>
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
          <td width="5%"><b>运输工具车牌号</b></td>
          <td width="5%"><b>运输工具编码</b></td>
          <td width="5%"><b>运输工具类型</b></td>
          <td width="5%"><b>运输工具状态</b></td>
          <td width="5%"><b>初始所处节点</b></td>         
          <td width="5%"><b>备注</b></td>
          <td width="3%"><b>修改</b></td>
          <td width="3%"><b>删除</b></td>
        </tr>

       <c:forEach var="item" items="${Pagelist}" varStatus="status">
        	<tr class="tr2" align="center">
	        	<td>${item.busnumber}</td>
	        	<td>${item.transno}</td>
	        	<td>${item.tranmodel.transtype}载重量${item.tranmodel.loadweight}吨</td>	        	
	        	<td>${item.state}</td>
	        	<td>${item.nodes.nodename}</td>
	        	<td>${item.note}</td>                
	    		<td><a href="EditTrsport?transid=${item.transid}">修改</a></td>
	    		<td><a href="DeleteTrsport?transid=${item.transid}&busnumber=${busnumber}&transno=${transno}&tranmodelid=${tranmodelid}">删除</a></td>	
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
