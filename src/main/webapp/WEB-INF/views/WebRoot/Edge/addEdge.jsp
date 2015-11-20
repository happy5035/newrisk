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
    <title>添加边</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
   <script type="text/javascript" src="https://code.jquery.com/jquery-1.10.2.js"></script> 
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
function OpenWindow(){ 
	window.open("getAllArea?showinfoFlag=2&emerId="+document.getElementById("emerId").value); 	
	}	
	
function OpenWindow2(){ 
	window.open("getAllArea?showinfoFlag=3&emerId="+document.getElementById("emerId").value); 	
	} 
//子窗口关闭后，返回值到此处，进而设置起点、终点	
function setValue(dnodeId,disastername,flag,lng,lat){ 
        //起点
	if(flag == "2")
	   {document.getElementById("origin").value = disastername; 
	    document.getElementById("originid").value = dnodeId; 
	    document.getElementById("originLng").value = lng; 
	    document.getElementById("originLat").value = lat;
	   }
	   //终点
	if(flag == "3")
	    {document.getElementById("terminal").value = disastername; 
	     document.getElementById("terminalid").value = dnodeId;
	     document.getElementById("terminalLng").value = lng; 
	     document.getElementById("terminalLat").value = lat;
	    }
	    //画折线
	if(flag == "4")
	    {document.getElementById("axisx").value = dnodeId; 
	     document.getElementById("axisy").value = disastername;
	    }
	    //画曲线
	if(flag == "5")
	    {document.getElementById("axisx").value = dnodeId; 
	     document.getElementById("axisy").value = disastername;
	    }
	}  
		var checkflag = "false";
function drawline(){
    window.open("getAllArea?emerId="+document.getElementById("emerId").value+"&edgeType="+document.getElementById("edgeType").value); 
}

function drawline1(){
     window.open("getAllArea.action?showinfoFlag=5&emerId="+document.getElementById("emerId").value+"&originLng="+document.getElementById("originLng").value+"&originLat="+document.getElementById("originLat").value+"&terminalLng="+document.getElementById("terminalLng").value+"&terminalLat="+document.getElementById("terminalLat").value); 
     //alert("验证纬度");
     //alert("验证"+document.getElementById("axisx").value);
}


$(document).ready(function(){          	
$( "#emername" ).change(function(){
$.ajax({
	type: "POST",
	url: "findSubNum?emerid="+encodeURI(encodeURI($(this).val())),
	cache: false,
	async: false,
	success: function(data){
		$("#emersub").html(data);
	}});
}); 
$( "#emername" ).change(function(){
	$.ajax({
		type: "POST",
		url: "findEmerSub?emerid="+encodeURI(encodeURI($(this).val())),
		cache: false,
		async: false,
		success: function(data){
			$(".subemer").html(data);
			
		}});
	}); 
$( "#Tab1" ).on("change","#subemer",function(e){      
	var pre=$(this).val();
	test1="#subemer"+(i-1)+"";
	var end=$(test1).val();
	
	$.ajax({
		type: "POST",
		url: "deletesecond?edgeid="+$(this).parent().parent().attr("id"),
		cache: false,
		async: false,
		});
	if(typeof(pre)!=='undefined'&&typeof(end)!=='undefined')
	if($.trim(pre) != $.trim(end)){
		$.ajax({
			type: "POST",
			url: "updatefisrt?subid="+encodeURI(encodeURI($(this).val()))+"&subaid="+end+"&edgeid="+$(this).parent().parent().attr("id"),
			cache: false,
			async: false,
			});
	}
	
	$.ajax({
		type: "POST",
		url: "findSecondSub?subid="+encodeURI(encodeURI($(this).val())),
		cache: false,
		async: false,
		success: function(data){
			var c=e.target.parentNode.parentNode.rowIndex-1;
			test="#subemer"+c+"";
			$(test).html(data);
		}}); 
});  

});//曾经括号放错位置
//风险清单项 
var  i=0;
function addRow(TabId){  
	 $.ajax({
		type: "POST",
		url: "findEmerSub?emerid="+encodeURI($("#emername").val()),
		cache: false,
		async: true,
		success: function(data){
			var c=i-1;
			var test=".subemer[name='subemer"+c+"']";
			$(test).html(data);	  
			 $(test).change(); 
		}});
	
var table = document.getElementById(TabId);
//在最后一行插入一行
var newRow = table.insertRow(table.rows.length);
//在该行插入单元格
var newCel1 = newRow.insertCell(0);
var newCel2 = newRow.insertCell(1);


newCel1.innerHTML = "<select class='subemer' id='subemer' name='subemer"+i+"'></select>";
newCel2.innerHTML = "<select id='subemer"+i+"' ></select>";
$.ajax({
	type: "POST",
	url: "findEmerSub?emerid="+encodeURI($("#emername").val()),
	cache: false,
	async: false,//同步
	success: function(data){
		var c=i;
		var test=".subemer[name='subemer"+c+"']";
		$(test).html(data);	
		 $(test).change(); 
		 
		
	}});
 var t1="#subemer"+i+"";
 $.ajax({
	 type: "POST",
		url: "saveEdgeOri?subid="+encodeURI($("#subemer").val())+"&subsid="+$(t1).val(),
		cache: false,
		async: true,
		 success: function(data){
			var c=i-1;
			var test=".subemer[name='subemer"+c+"']";
			var trm=$(test).parent().parent();
			trm.attr("id",data);
		} 
 });  

i++;
document.getElementById("length1").value = i;
}  


function tb_delete(){

var t2="#subemer"+(i-1)+"";
$.ajax({
	type: "POST",
	url: "deleteEdge?subid="+encodeURI($("#subemer").val())+"&subsid="+$(t2).val(),
	cache: false,
	async: false,//同步
		 		
	});
var ls_t=document.all("Tab1");
ls_t.deleteRow(i) ;

i--;

}

</script>


  </head>
  
  <body>
<form id="EdgeForm" method="post"  name="EdgeForm" action="saveEdge">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">子项目顺序管理&gt;</td>
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
					
					<td  class="tr1" align="right">*所属项目:</td>
					<td>
					<select id="emername" name="emername">
						<c:forEach var="item" items="${emergencylist}">
							<option value="${item.emerid}">${item.emername}</option>
						</c:forEach>
					</select>
                    </td>	
                    <td  class="tr1" align="right">*子项目个数:</td>
					<td>
					<select id="emersub" name="emersub">
						<c:forEach var="item" items="${emersublist}">
							<option value="${item.emerid}">${item.subnum}</option>
						</c:forEach>
					</select>
                    </td>				
			</tr>
		<!-- 	<tr class="tr2">
				<td  class="tr1" align="right">起点:</td>
				<td><input type="text" id="origin" onclick="OpenWindow()" name="origin" ></td>
				<td  class="tr1" align="right">终点:</td>
				<td><input type="text" id="terminal" onclick="OpenWindow2()"  name="terminal" ></td>
			</tr>
			
			<tr class="tr10">
    	  			<td align="center" colspan="4">
    	  				 &nbsp;&nbsp;
    	  				 <input type=button value="查看曲线" onclick="drawline1()">
    	  				 <input type=button value="去画折线" onclick="drawline()">
    	  				 <input type=image src="/risk/static/images/pub/lzoa_pub_save.gif" width="67" height="19" style="cursor:hand;border:0px" ;  onClick="return cbt_local();  return false;">
                         <input type="image" src="/risk/static/images/pub/lzoa_pub_reset.gif" width="67" height="19" style="cursor:hand" onClick="return cbt_res();">
	   					<a href="javascript:history.back(-1);"><img border="0" src="/risk/static/images/pub/lzoa_pub_back.gif"/></a> 
	  				</td>
  			</tr> -->
			</table>
		</td>
    </tr>
</table>
<form id="DisasterNodeForm" method="post"  name="DisasterNodeForm" action="saveDisasterNode2">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">子项目顺序管理&gt;&gt;添加子项目顺序</td>
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
        <td class="tr1" align="middle">前一个项目</td>
        <td class="tr1" align="middle">后一个项目</td>
      </tr>
   </table>
   <input type="button" onclick="addRow('Tab1');"  value="增加子项目顺序"/>
   <input type=button value="删除子项目顺序"  onclick="tb_delete()" >
   <input type=image src="/risk/static/images/pub/lzoa_pub_save.gif" width="67" height="19" align="middle" style="cursor:hand;border:0px ;a" ;  onClick="return cbt_local();  return false;">    <!-- "要填写该跳转的地方！！！！" -->
   <input type="hidden" id="length1"  name="length1" >
   </center>   
</form>
<!-- <input type="hidden" id="originid" name="originid">
<input type="hidden" id="terminalid"  name="terminalid" >
<input type="hidden" id="originLng" name="originlng">
<input type="hidden" id="originLat"  name="originlat" >
<input type="hidden" id="terminalLng" name="terminallng">
<input type="hidden" id="terminalLat"  name="terminallat" >
<input type="hidden" id="axisx"  name="axisx" >
<input type="hidden" id="axisy"  name="axisy" > -->
</form> 
</body>
</html>
