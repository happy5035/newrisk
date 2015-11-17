<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    
<title>查看物资</title>
 <link href="<c:url value="/static/style/gov_style_10.css" />" rel="stylesheet" type="text/css">
 <link href="/BDplatformWeb/style/gov_style_10.css" rel="stylesheet" type="text/css">   
 <script type="text/javascript" src="<c:url value="/static/js//js/jquery-1.8.0.js" />"></script>   
<script type="text/javascript"> 
/* 
           $(document).ready(function(){          	
		    $( "#emerTypeName" ).change(function(){
		    $.ajax({
		    	type: "POST",
		    	url: "findLevel?emerTypeName="+encodeURI(encodeURI($(this).val())),
		    	cache: false,
		    	async: false,
		    	success: function(data){
		    		$("#emerTypeId").html(data);
		    	}});
		    }); 		    
     });  */
</script> 
 </head>
  
 <body>
<form id="GoodsForm" name="GoodsForm" method="post" theme="simple"  namespace="/" action="SearchEmergency">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">项目管理>>按条件查询项目</td>
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
					<td  class="tr1" align="right">项目名称:</td>
					<td><input type="text" name="emername" value=${emerName}></td>
					<td  class="tr1" align="right">项目编码:</td>
					<td><input type="text" name="emerno" value=${emerNo}></td>
					
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
					<td width="100%" class="f3">项目管理>>查看符合条件的项目</td>
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
          <td width="5%"><b>项目名称</b></td>
          <td width="5%"><b>项目编号</b></td>
          <!-- <td width="5%"><b>风险事件类型</b></td> -->
          <td width="5%"><b>项目发生时间</b></td>
          <td width="5%"><b>项目描述</b></td>
       	<td width="5%"><b>子项目个数</b></td> 
          <td width="5%"><b>备注</b></td>
          <td width="3%"><b>修改</b></td>
          <td width="3%"><b>删除</b></td>
        </tr>

       <c:forEach var="item" items="${Pagelist}" varStatus="status">
        	<tr class="tr2" align="center">
	        	<td>${item.emername}</td>
	        	<td>${item.emerno}</td>
	        	<%-- <td>${item.emertype.level}级${item.emertype.emertypename}</td>	   --%>      	
	        	<td><fmt:formatDate value="${item.happentime}" pattern="yyyy-MM-dd"  /></td>
	        	<td>${item.emerdescribe}</td>
	        	 <td>${item.subnum}</td>	    	
	        	<td>${item.note}</td>                
	    		<td><a href="EditEmergency?emerid=${item.emerid}">修改</a></td>
	    		<td><a href="DeleteEmergency?emerid=${item.emerid}&emerName=${emerName}&emerNo=${emerNo}<%-- &emerTypeId=${emerTypeId} --%>">删除</a></td>	
	    	</tr>
		</c:forEach>
      </table>    
    </td>
 </tr>
</table>
</form>
 <div align="right"><%@ include file="../page.jsp"%></div>  
</body>
</html>
