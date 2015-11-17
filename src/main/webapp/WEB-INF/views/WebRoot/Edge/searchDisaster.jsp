<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="<c:url value="/static/style/gov_style_10.css" />" rel="stylesheet" type="text/css">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>查看风险事件情况</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
   <script type="text/javascript" src="<c:url value="/static/js/js/jquery-1.8.0.js"/>"></script> 
     <script type="text/javascript"> 
function cbt_local()
{
if(document.forms[0].logiccabinetno.value =='')
{alert("逻辑柜编号不能为空");document.forms[0].logiccabinetno.focus(); return false;}
}
function cbt_res()
{
	document.getElementById("DisasterNodeForm").reset();
	return false;
}


	


function drawline1(){
     window.open("getAllArea?showinfoFlag=5&emerId="+document.getElementById("emerId").value+"&originLng="+document.getElementById("originLng").value+"&originLat="+document.getElementById("originLat").value+"&terminalLng="+document.getElementById("terminalLng").value+"&terminalLat="+document.getElementById("terminalLat").value); 
   /*  alert("验证纬度");
     alert("验证"+document.getElementById("axisx").value);*/
}

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

     <script type="text/javascript" src="<%=path %>/js/My97DatePicker/WdatePicker.js"></script>  

  </head>
  
  <body>
<form id="EdgeForm" method="post"  name="EdgeForm" action="saveEdge.action">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">调度方案信息&gt;&gt;查看风险情况</td>
                </tr>
            </table></td>
          <td width="50%"></td>
        </tr>
      </table></td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="1" cellpadding="3" >
	<tr>
		<td align="center" class="tr4"> 
			<table border="0" cellpadding="3" cellspacing="1" class="table3">
			<tr class="tr2">
					<td  class="tr1" align="right">*所属风险事件类型:</td>
					<td>
					<select id="emerTypeName" name="emertypename">
						<c:forEach var="item" items="${emertypename}">
							<option value="${item}">${item}</option>
						</c:forEach>
					</select>
					</td>
					<td  class="tr1" align="right">*所属风险事件:</td>
					<td>
					<select id="emerId" name="emerid">
						<c:forEach var="item" items="${emergencylist}">
							<option value="${item.emerid}">${item.emername}</option>
						</c:forEach>
					</select>
                    </td>				
			</tr>
						
			<tr class="tr10">
    	  			<td align="center" colspan="4">
    	  				 &nbsp;&nbsp;
    	  				 <input type=button value="去查看" onclick="drawline1()">
    	  				
    	  				
	  				</td>
  			</tr>
			</table>
		</td>
    </tr>
</table>
<input type="hidden" id="originid" name="originid">
<input type="hidden" id="terminalid"  name="terminalid" >
<input type="hidden" id="originLng" name="originlng">
<input type="hidden" id="originLat"  name="originLat" >
<input type="hidden" id="terminalLng" name="terminallng">
<input type="hidden" id="terminalLat"  name="terminallat" >
<input type="hidden" id="axisx"  name="axisx" >
<input type="hidden" id="axisy"  name="axisy" >
</form> 
</body>
</html>
