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
    <title>添加风险应对策略</title>    
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
	document.getElementById("WareHouseNodeForm").reset();
	return false;
}
function OpenWindow(){ 

	window.open("getAllArea?showinfoFlag=4&emerId="+document.getElementById("emerId").value); 	
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
<form id="WareHouseNodeForm" method="post"  name="WareHouseNodeForm" action="saveWareHouse">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">风险应对策略信息管理&gt;&gt;添加风险应对策略</td>
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
					<td  class="tr1" align="right" >*风险应对策略名称:</td>
					<td><input type="text" name="warehousename" ></td>	
					<td  class="tr1" align="right" >*风险应对策略编码:</td>
					<td><input type="text" name="warehouseno" ></td>				
				</tr>
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
			<tr class="tr2">
					<td  class="tr1" align="right">*风险应对策略等级:</td>
					<td><input type="radio" name="warehouselevel" value="P">省<input type="radio" name="warehouselevel" value="C">市 <input type="radio" name="warehouselevel" value="D">县/区 </td>
				    <td  class="tr1" align="right">*仓库性质:</td>
					<td><input type="radio" name="property" value="C">国有<input type="radio" name="property" value="L">地方所有 <input type="radio" name="property" value="S">个人 </td>
				</tr>
				<!-- <tr class="tr2">
					<td  class="tr1" align="right">*仓库规模:</td>
					<td><input type="text" name="dimensions" ></td>
					<td  class="tr1" align="right">*仓库容量:</td>
					<td><input type="text" name="capacity" ></td>				
				</tr> -->
				<tr class="tr2">
					<td  class="tr1" align="right">*隶属部门:</td>
					<td><input type="text" name="belong" ></td>
					<td  class="tr1" align="right">*风险应对策略管理员:</td>
					<td><input type="text" name="administrator" ></td>				
				</tr>
				<tr class="tr2">
					<td  class="tr1" align="right">*法人:</td>
					<td><input type="text" name="legalman" ></td>
					<td  class="tr1" align="right">*法人联系方式:</td>
					<td><input type="text" name="legalmanphone" ></td>				
				</tr>
				<tr class="tr2">
					<td  class="tr1" align="right">*联系人:</td>
					<td><input type="text" name="linkman" ></td>
					<td  class="tr1" align="right">*联系人电话:</td>
					<td><input type="text" name="linkmanPhone" ></td>				
				</tr>
			<tr class="tr2">
				<td  class="tr1" align="right">X坐标:</td>
				<td><input type="text" id="longitude" onclick="OpenWindow()" name="longitude" >  
				</td>
				<td  class="tr1" align="right">Y坐标:</td>
				<td><input type="text" id="latitude" onclick="OpenWindow()"  name="latitude" >        
				</td>
			</tr>
			<tr class="tr2">
			        <td  class="tr1" align="right">*所在地区:</td>
					<td >
						<select id="areaid"  name="areaid"  >
							<c:forEach var="item" items="${areas}">
								<option value="${item.areaid}">${item.areaname}</option>
							</c:forEach>
						</select>
						<select   id="streetid" name="streetid" style="width:100;"  >
							<c:forEach var="item" items="${streetlist}">
								<option value="${item.areaid}">${item.areaname}</option>
							</c:forEach>
						</select>
					 	<select name="roadid" id="roadid" style="width:100;" > 
						<c:forEach var="item" items="${roadlist}">
							<option value ="${item.areaid}">${item.areaname}</option>	
						</c:forEach> 
						</select>
				</td>
					<td  class="tr1" align="right">详细地址:</td>
					<td><input type="text" name="address" ></td>
					</tr>
			<tr class="tr2">
			        <td  class="tr1" align="right">*联系电话:</td>
					<td><input type="text" name="phone" ></td>
					<td  class="tr1" align="right">备注:</td>
					<td><input type="text" name="note" ></td>
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
