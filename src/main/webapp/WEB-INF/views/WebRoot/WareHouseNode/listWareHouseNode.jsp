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
    
<title>查看风险应对策略</title>
<link href="<c:url value="/static/style/gov_style_10.css" />" rel="stylesheet" type="text/css">
   <script type="text/javascript" src="<c:url value="/static/js/js/jquery-1.8.0.js"/>"></script> 
<script type="text/javascript"> 

            $(document).ready(function(){          	
		    $( "#emerTypeName" ).change(function(){
		    $.ajax({
		    	type: "POST",
		    	url: "findEmergency?emerTypeName="+encodeURI(encodeURI($(this).val())),
		    	cache: false,
		    	async: false,
		    	success: function(data){
		    		$("#emerId").html(data);
		    	}});		    
		    }); 		    
     }); 

</script> 
 </head>
  
 <body>
<form id="DisasterNodeForm" name="DisasterNodeForm" method="post" theme="simple"  namespace="/" action="SearchWareHouse">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">风险应对策略信息管理>>按条件查询风险应对策略</td>
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
					<td  class="tr1" align="right">风险应对策略名称:</td>
					<td><input type="text" name="warehousename" value=${warehousename}></td>
					<td  class="tr1" align="right">*所属风险事件类型:</td>
					<td>
					<select id="emerTypeName" name="emertypename">
						<c:forEach var="item" items="${emertypename}">
							<option value="${item}" <c:if test="${item eq emertype.emertypename}">selected</c:if>>${item}</option>
						</c:forEach>
					</select>
					</td>
					<td  class="tr1" align="right">*所属风险事件:</td>
					<td>
					<select id="emerId" name="emerid">
						<c:forEach var="item" items="${emergencylist}">
							<option value="${item.emerid}" <c:if test="${item.emerid eq emergency.emerid}">selected</c:if>>${item.emername}</option>
						</c:forEach>
					</select>
                    </td>	
                    </tr>					
			<tr class="tr10">
					<td  class="tr1" align="right">风险应对策略编码:</td>
					<td><input type="text" name="warehouseno" value=${wareHouseNo}></td>
					<td  class="tr1" align="right">*风险应对策略等级:</td>
					<td class="tr1">
					    <input type="radio" name="warehouselevel" value="P" <c:if test="${fn:contains(warehouselevel, 'P')}">checked</c:if>>省
					    <input type="radio" name="warehouselevel" value="C" <c:if test="${fn:contains(warehouselevel, 'C')}">checked</c:if>>市 
					    <input type="radio" name="warehouselevel" value="D" <c:if test="${fn:contains(warehouselevel, 'D')}">checked</c:if>>县/区
					</td>
					<td  class="tr1" align="right">*仓库性质:</td>
					<td class="tr1">
					    <input type="radio" name="property" value="C" <c:if test="${fn:contains(property, 'C')}">checked</c:if>>国有
					    <input type="radio" name="property" value="L" <c:if test="${fn:contains(property, 'L')}">checked</c:if>>地方所有
					    <input type="radio" name="property" value="S" <c:if test="${fn:contains(property, 'S')}">checked</c:if>>个人 
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
					<td width="100%" class="f3">风险应对策略信息管理>>查看所有风险应对策略</td>
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
          <td width="5%"><b>风险应对策略名称</b></td>
          <td width="5%"><b>风险应对策略详情</b></td>
          <td width="5%"><b>所属风险事件类型</b></td>
          <td width="5%"><b>所属风险事件</b></td>
          <td width="5%"><b>所在经度</b></td>
          <td width="5%"><b>所在纬度</b></td>
          <td width="5%"><b>详细地址</b></td>
          <td width="5%"><b>备注</b></td>
          <td width="3%"><b>修改</b></td>
          <td width="3%"><b>删除</b></td>
        </tr>

       <c:forEach var="item" items="${Pagelist}" varStatus="status">
        	<tr class="tr2" align="center">
	        	<td>${item.nodes.nodename}</td>
	        	<td><a href="DetailWareHouse?wnodeid=${item.nodes.nodeid}">风险应对策略详情</a></td>
	        	<td>${item.emertype.emertypename}</td>	        	
	        	<td>${item.emergency.emername}</td>        	
	        	<td>${item.nodes.longitude}</td> 
	        	<td>${item.nodes.latitude}</td>
	        	<td>${item.nodes.address}</td>
	        	<td>${item.nodes.notes}</td>                  
	    		<td><a href="EditWareHouse?nodeid=${item.nodes.nodeid}&enrelationid=${item.enrelationid}">修改</a></td>
	    		<td><a href="DeleteWareHouse?nodeid=${item.nodes.nodeid}&warehousename=${warehousename}&warehouseno=${warehouseno}&emerid=${emerid}&warehouselevel=${warehouselevel}&property=${property}&enrelationid=${item.enrelationid}">删除</a></td>	
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
