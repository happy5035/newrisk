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
    <title>创建风险事件实例</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<c:url value="/static/js/js/jquery-1.5.2.js"/>"></script>
   <script type="text/javascript" src="<c:url value="/static/js/js/jquery-1.8.0.js"/>"></script> 
     <script type="text/javascript"> 
function cbt_res()
{
	document.getElementById("EmergencyForm").reset();
	return false;
}

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
		    $.ajax({
		    	type: "POST",
		    	url: "findDescribe?emerTypeId="+$("#emerTypeId").val()+"&amt=" + Math.random(),
		    	cache: false,
		    	async: false,
		    	success: function(data){
		    		$("#describe").html(data);
		    	}});
		    }); 		    
     }); 
     
     $(document).ready(function(){          	
		    $( "#emerTypeId" ).change(function(){
		    $.ajax({
		    	type: "POST",
		    	url: "findDescribe?emerTypeId="+encodeURI(encodeURI($(this).val())),
		    	cache: false,
		    	async: false,
		    	success: function(data){
		    		$("#describe").html(data);
		    	}});
		    }); 
     }); 
     
     
     



   //风险清单项 
   var  i=0;
   function addRow(TabId){  
   //alert("风险清单项"+disasterName[i-1]);
   //获取要插入行的表格
   var table = document.getElementById(TabId);
   //在最后一行插入一行
   var newRow = table.insertRow(table.rows.length);
   //在该行插入单元格
   var newCel1 = newRow.insertCell(0);
   var newCel2 = newRow.insertCell(1);
   var newCel3 = newRow.insertCell(2);
   var newCel4 = newRow.insertCell(3);
   var newCel5 = newRow.insertCell(4);
   newCel1.innerHTML = "<input type=\"text\" name=\"disastername"+i+"\" id=\"disasterName"+i+"\" title=\"disasterName"+i+"\">";
   newCel2.innerHTML = "<input type=\"text\" name=\"disasterno"+i+"\" id=\"disasterNo"+i+"\" title=\"disasterNo"+i+"\">";
   newCel3.innerHTML = "<input type=\"text\" name=\"principal"+i+"\" id=\"principal"+i+"\" title=\"principal"+i+"\">";
   newCel4.innerHTML = "<input type=\"text\" name=\"phone"+i+"\" id=\"phone"+i+"\" title=\"phone"+i+"\">";
   newCel5.innerHTML = "<input type=\"radio\" name=\"disasterLevel"+i+"\" id=\"disasterLevel"+i+"\" title=\"disasterLevel"+i+"\" value=\"C\">市<input type=\"radio\" name=\"disasterLevel"+i+"\" id=\"disasterLevel"+i+"\" title=\"disasterLevel"+i+"\" value=\"D\">县/区<input type=\"radio\" name=\"disasterLevel"+i+"\" id=\"disasterLevel"+i+"\" title=\"disasterLevel"+i+"\" value=\"T\">镇/街道<input type=\"radio\" name=\"disasterLevel"+i+"\" id=\"disasterLevel"+i+"\" title=\"disasterLevel"+i+"\" value=\"V\">村";
   i++;
   document.getElementById("length1").value = i;
   }  
   function tb_delete(){
   var ls_t=document.all("Tab1");
   
   ls_t.deleteRow(i) ;

   i--;
  
   }
   //风险应对策略
   var  j=0;
   function addRow2(TabId){  
   //获取要插入行的表格
   var table = document.getElementById(TabId);
   //在最后一行插入一行
   var newRow = table.insertRow(table.rows.length);
   //在该行插入单元格
   var newCel1 = newRow.insertCell(0);
   var newCel2 = newRow.insertCell(1);
   var newCel3 = newRow.insertCell(2);
   var newCel4 = newRow.insertCell(3);
   var newCel5 = newRow.insertCell(4);
   var newCel6 = newRow.insertCell(5);
   var newCel7 = newRow.insertCell(6);
   var newCel8 = newRow.insertCell(7);
   newCel1.innerHTML = "<input type=\"text\" name=\"wareHouseName"+j+"\" id=\"wareHouseName"+j+"\" title=\"wareHouseName"+j+"\">";
   newCel2.innerHTML = "<input type=\"text\" name=\"wareHouseNo"+j+"\" id=\"wareHouseNo"+j+"\" title=\"wareHouseNo"+j+"\">";
   newCel3.innerHTML = "<input type=\"radio\" name=\"wareHouseLevel"+j+"\" id=\"wareHouseLevel"+j+"\" title=\"wareHouseLevel"+j+"\" value=\"P\">省<input type=\"radio\" name=\"wareHouseLevel"+j+"\" id=\"wareHouseLevel"+j+"\" title=\"wareHouseLevel"+j+"\" value=\"C\">市<input type=\"radio\" name=\"wareHouseLevel"+j+"\" id=\"wareHouseLevel"+j+"\" title=\"wareHouseLevel"+j+"\" value=\"D\">县/区";
   newCel4.innerHTML = "<input type=\"text\" name=\"dimensions"+j+"\" id=\"dimensions"+j+"\" title=\"dimensions"+j+"\">";
   newCel5.innerHTML = "<input type=\"radio\" name=\"property"+j+"\" id=\"property"+j+"\" title=\"property"+j+"\" value=\"C\">国有<input type=\"radio\" name=\"property"+j+"\" id=\"property"+j+"\" title=\"property"+j+"\" value=\"L\">地方所有<input type=\"radio\" name=\"property"+j+"\" id=\"property"+j+"\" title=\"property"+j+"\" value=\"S\">个人";
   newCel6.innerHTML = "<input type=\"text\" name=\"capacity"+j+"\" id=\"capacity"+j+"\" title=\"capacity"+j+"\">";
   newCel7.innerHTML = "<input type=\"text\" name=\"administrator"+j+"\" id=\"administrator"+j+"\" title=\"administrator"+j+"\">";
   newCel8.innerHTML = "<input type=\"text\" name=\"phone"+j+"\" id=\"phone"+j+"\" title=\"phone"+j+"\">";
   j++;
   document.getElementById("length2").value = j;
   }  
   function tb_delete2(){
   var ls_t=document.all("Tab2");
   ls_t.deleteRow(j) ;
   j--;
   }
   
   function openDetail(){
			$.ajax({
				type: "post",
				url: "openGoodsRelDetail?"+"&randomn="+Math.random(),
				async: true,
				success: function(data){
				   $.each(data,function(idx,item){   				   
					    addEdge(item);//将边添加在地图上
					})					
				}			
			});
		}
</script>
    <script type="text/javascript" src="<c:url value="/static/js/js/My97DatePicker/WdatePicker.js"/>"></script>

  </head>
  
  <body>
<form id="EmergencyForm" method="post"  name="EmergencyForm" action="saveEmergency2">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">风险事件管理&gt;&gt;添加风险事件</td>
                </tr>
            </table></td>
          <td width="50%"></td>
        </tr>
      </table></td>
  </tr>
</table>
<table width="50%" border="0" cellspacing="1" cellpadding="3" align="center">
	<tr>
		<td align="center" class="tr4"> 
			<table border="0" cellpadding="3" cellspacing="1" class="table3">
			<tr class="tr2">
				<td  class="tr1" align="right">风险事件名称:</td>
				<td><input type="text" name="emername" ></td>	
				<td  class="tr1" align="right">风险事件编码:</td>
				<td><input type="text" name="emerno" ></td>	
			</tr>
			<tr class="tr2">
					<td  class="tr1" align="right">风险事件类型:</td>									
					<td>
					<select id="emerTypeName" name="emertypename">
						<c:forEach var="item" items="${emerTypeName}">
							<option value="${item}">${item}</option>
						</c:forEach>
					</select>
					</td>		
					<td  class="tr1" align="right" >风险等级:</td>
					<td>
					<select id="emerTypeId" name="emertypeid">
						<c:forEach var="item" items="${emerTypelist}">
							<option value="${item.emertypeid}">${item.level}</option>
						</c:forEach>
					</select>
					<select id="describe" name="describe">
						<c:forEach var="item" items="${emerTypelist2}">
							<option value="${item.emertypeid}">${item.describe}</option>
						</c:forEach>
					</select>
				    </td>								
			</tr>
			<tr class="tr2">
					<td  class="tr1" align="right">风险发生时间:</td>
					<td><input class="Wdate" type="text" name="happentime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font color=red>&lt;- 点击选择日期</font></td>
					<td  class="tr1" align="right">风险描述:</td>
					<td><input type="text" name="emerdescribe" ></td>				
				</tr>
			<tr class="tr2">
                    <td  class="tr1" align="right">覆盖地区:</td>
					<td><input type="text" name="inaddress" ></td>
					<td  class="tr1" align="right" >备注:</td>
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

<form id="DisasterNodeForm" method="post"  name="DisasterNodeForm" action="saveDisasterNode2">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">风险事件管理&gt;&gt;添加风险清单项</td>
                </tr>
            </table></td>
          <td width="50%"></td>
        </tr>
      </table></td>
  </tr>
</table>
<center>
   <table id="Tab1" border="0" cellspacing="1" cellpadding="3">
     <tr class="tr2">
        <td class="tr1" align="middle">风险清单项名称</td>
        <td class="tr1" align="middle">风险清单项编码</td>
        <td class="tr1" align="middle">灾区负责人</td>
        <td class="tr1" align="middle">联系电话</td>
        <td class="tr1" align="middle">风险清单项等级</td>
   </table>
   <input type="button" onclick="addRow('Tab1');"  value="增加风险清单项"/>
   <input type=button value="删除风险清单项"  onclick="tb_delete()" >
   <input type=image src="/risk/static/images/pub/lzoa_pub_save.gif" width="67" height="19" align="middle" style="cursor:hand;border:0px ;a" ;  onClick="return cbt_local();  return false;">    <!-- "要填写该跳转的地方！！！！" -->
   <input type="hidden" id="length1"  name="length1" >
   </center>   
</form>

<form id="WareHouseNodeForm" method="post"  name="WareHouseNodeForm" action="saveWareHouse2">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">风险事件管理&gt;&gt;添加风险应对策略</td>
                </tr>
            </table></td>
          <td width="50%"></td>
        </tr>
      </table></td>
  </tr>
</table>
<center>
   <table id="Tab2" border="0" cellspacing="1" cellpadding="3">
     <tr class="tr2">
        <td class="tr1" align="middle">风险应对策略名称</td>
        <td class="tr1" align="middle">风险应对策略编码</td>
        <td class="tr1" align="middle">风险应对策略等级</td>        
        <!-- <td class="tr1" align="middle">仓库规模</td>
        <td class="tr1" align="middle">仓库性质</td>
        <td class="tr1" align="middle">仓库容量</td> -->
        <td class="tr1" align="middle">风险应对策略管理员</td>
        <td class="tr1" align="middle">联系电话</td>
   </table>
   <br><input type="button" onclick="addRow2('Tab2');" value="增加风险应对策略"/>
   <input type=button value="删除风险应对策略" onclick="tb_delete2()" >
   <input type=image src="/risk/static/images/pub/lzoa_pub_save.gif" width="67" height="19" align="middle" style="cursor:hand;border:0px" ;  onClick="return cbt_local();  return false;">    <!-- "要填写该跳转的地方！！！！" -->
   <input type="hidden" id="length2"  name="length2" >
   </center>
</form>

<form id="GoodsRelForm" method="post"  name="GoodsRelForm" action="openGoodsRelDetail">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">风险事件管理&gt;&gt;添加物资需求关系</td>
                </tr>
            </table></td>
          <td width="50%"></td>
        </tr>
      </table></td>
  </tr>
</table>
<center>
   <table id="Tab3" border="0" cellspacing="1" cellpadding="3" style="display:block">
     <tr class="tr2">
        <td class="tr1" align="middle">节点/物资名称</td>
     <%
     Object m=request.getAttribute("number");      
     int b = Integer.valueOf(String.valueOf(m));
     System.out.println("查看"+b);
     
     //for(Nodes node : nodelist){
     //out.println("<li>" + node.getNodeName() + "</li>");
     for(int i=0;i<b;i++){
     pageContext.setAttribute("i",i);
     %>      
        <td class="tr1" align="middle">i+1</td>
     <% } %>
     </tr>
     
     <tr class="tr2">
     </tr>
     
   </table>
   <br><input type="button" onclick="openDetail();" value="展开详情"/>
   <input type=image src="/risk/static/images/pub/lzoa_pub_save.gif" width="67" height="19" align="middle" style="cursor:hand;border:0px" ;  onClick="return cbt_local();  return false;">    <!-- "要填写该跳转的地方！！！！" -->
   </center>
</form>

</body>
</html>










