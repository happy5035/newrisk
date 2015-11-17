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
  <head>
    <base href="<%=basePath%>">
    
    <title>修改物资信息</title>
    
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
 <form name="GoodsForm" action="UpdateGoods.action" method="post" >
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">物资管理>>修改物资信息</td>
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
				<td  class="tr1" align="right">*物资名称:</td>
				<td><input type="text" name="goodsname" value="${goods.goodsname}"></td>
				<td  class="tr1" align="right">*物资编码:</td>
				<td><input type="text" name="goodsno" value="${goods.goodsno}"></td>	
			</tr>
			<tr class="tr2">
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
					<td  class="tr1" align="right" >*计量单位:</td>
					<td><select name="measureUnit">
					        <option value="千克" ${selectstate0}>千克</option>
							<option value="顶" ${selectstate1}>顶</option>
							<option value="箱" ${selectstate2}>箱</option>
							<option value="其它" ${selectstate3}>其它</option>		
						</select>
					</td>			
			</tr>
			<tr class="tr2">
					<td  class="tr1" align="right">*尺寸大小:</td>
					<td><input type="text" name="size" value="${goods.size}"></td>
					<td  class="tr1" align="right">*规格:</td>
					<td><input type="text" name="specification" value="${goods.specification}"></td>				
				</tr>
			<tr class="tr2">
                    <td  class="tr1" align="right">保质期:</td>
					<td><input type="text" name="guaranteeperiod" value="${goods.guaranteeperiod}"></td>
					<td  class="tr1" align="right" >主要用途:</td>
					<td><input type="text" name="mainuse" value="${goods.mainuse}"></td>				
				</tr>
				<tr class="tr2">
					<td  class="tr1" align="right">*备注:</td>
					<td><input type="text" name="note" value="${goods.note}"></td>				
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
<input type="hidden" name="goodsid" id="goodsId" value="${goods.goodsid}"/> 
</form>  
   
   
   
  </body>
</html>
