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
    <title>添加风险清单项</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
   <script type="text/javascript" src="<c:url value="/static/js/js/jquery-1.8.0.js"/>"></script> 
     <script type="text/javascript"> 
 
               $(document).ready(function(){  
        	$( "#areaid" ).change(function(){
		    $.ajax({
		    	type: "POST",
		    	url: "findArea?areaid="+$(this).val()+"&amt=" + Math.random(),
		    	cache: false,
		    	async: false,
		    	success: function(data){
		    		$("#streetid").html(data);
		    	}});
		     $.ajax({
		    	type: "POST",
		    	url: "findStreet?streetid="+$("#streetid").val()+"&amt=" + Math.random(),
		    	cache: false,
		    	async: false,
		    	success: function(data){
		    		$("#roadid").html(data);
		    	}});	
		    }); 
		    $( "#streetid" ).change(function(){
		    $.ajax({
		    	type: "POST",
		    	url: "findStreet?streetid="+$(this).val()+"&amt=" + Math.random(),
		    	cache: false,
		    	async: false,
		    	success: function(data){
		    		$("#roadid").html(data);
		    	}});
		    }); 
     }); 
  
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
function OpenWindow(){ 

	window.open("getAllArea?showinfoFlag=1&emerId="+document.getElementById("emerId").value); //记住是在哪个风险事件中添加点或者边的	
	} 
	function setValue(lngValue,latValue)   
	{ 
	document.getElementById("longitude").value = lngValue; 
	document.getElementById("latitude").value = latValue;
	} 
		var checkflag = "false";

</script>

    <script type="text/javascript" src="<c:url value="/static/js/js/My97DatePicker/WdatePicker.js"/>"></script>

  </head>
  
  <body>
<form id="DisasterNodeForm" method="post"  name="DisasterNodeForm" action="saveDisasterNode">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">子项目管理&gt;&gt;添加子项目</td>
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
					<td  class="tr1" align="right" >*子项目名称:</td>
					<td><input type="text" name="disastername" ></td>	
					<td  class="tr1" align="right" >*子项目编码:</td>
					<td><input type="text" name="disasterno" ></td>				
				</tr>
			<tr class="tr2">
					<td  class="tr1" align="right">*所属风险类型:</td>
					<td>
					<select id="emerTypeName" name="emerTypeName">
						<c:forEach var="item" items="${emerTypeName}">
							<option value="${item}">${item}</option>
						</c:forEach>
					</select>
					</td>
					<td  class="tr1" align="right">*所属项目:</td>
					<td>
					<select id="emerId" name="emerId">
						<c:forEach var="item" items="${emergencylist}">
							<option value="${item.emerid}">${item.emername}</option>
						</c:forEach>
					</select>
                    </td>				
				</tr>
			
			<tr class="tr2">
					<td  class="tr1" align="right">人力限制:</td>
					<td><input type="text" name="address" ></td>
					<td  class="tr1" align="right">资金限制:</td>
					<td><input type="text" name="note" ></td>
					</tr>
	   		<tr class="tr2">
					<td  class="tr1" align="right">*物资类型:</td>									
					<td>
					<select id="goodsTypeName" name="goodstypename">
						<c:forEach var="item" items="${goodstypename}">
							<option value="${item}">${item}</option>
						</c:forEach>
					</select>
					 <select id="goodsTypeId" name="goodstypeid">
						<c:forEach var="item" items="${goodstypelist}">
							<option value="${item.goodstypeid}">${item.grade}</option>
						</c:forEach>
					</select> 
				    </td>		
					<td  class="tr1" align="right" >*计量单位:</td>
					<td>
					<select name="measureunit" >
							<option value="千克" selected="selected">千克</option>
							<option value="顶">顶</option>
							<option value="箱">箱</option>
							<option value="其他">其他</option>
						</select>
					<font color=red> *</font></td>			
			</tr>
			<tr class="tr2">
					<td  class="tr1" align="right">子项目描述:</td>
					<td><input type="text" name="address" ></td>
					<td  class="tr1" align="right">风险率:</td>
					<td>
						<select name="riskpercent" >
							<option value="0-25%" selected="selected">0-25%</option>
							<option value="25%-50%">25%-50%</option>
							<option value="50%-75%">50%-75%</option>
							<option value="75%-100%">75%-100%</option>
						</select>
						<font color=red> *</font></td>
					
					</tr>
			<tr class="tr10">
    	  			<td align="center" colspan="4">
    	  				 &nbsp;&nbsp;
    	  				 <input type=image src="/risk/static/images/pub/lzoa_pub_save.gif" width="67" height="19" style="cursor:hand;border:0px" ;  onClick="return cbt_local();  return false;">
                         <input type="image" src="/risk/static/images/pub/lzoa_pub_reset.gif" width="67" height="19" style="cursor:hand" onClick="return cbt_res();">	   					
	  				</td>
  			</tr>
			</table>
		</td>
    </tr>
</table>
</form> 
</body>
</html>
