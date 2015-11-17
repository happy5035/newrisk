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
	document.getElementById("GoodsRelForm").reset();
	return false;
}
</script>
    <script type="text/javascript" src="<c:url value="/static/js/js/My97DatePicker/WdatePicker.js"/>"></script>

  </head>
  
  <body>
<form id="GoodsRelForm" method="post"  name="GoodsRelForm" action="saveGoodsRel2">
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
     <%
     Object m=request.getAttribute("number");  //风险清单项的数目    
     int a =Integer.valueOf(String.valueOf(m));
     Object n=request.getAttribute("num");      //物资的种类
     int b =Integer.valueOf(String.valueOf(n));%>
     <tr class="tr2">
        <td class="tr1" align="middle">风险清单项/物资名称</td>
     <%
     for(int i=0;i<a;i++){
     pageContext.setAttribute("i",i);
     %>      
        <td class="tr1" align="middle">${DnodeNames[i]}</td>
     <% } %>
     </tr>
     
     <%  //第二行及以后其它各行
     for(int j=0;j<b;j++){
     pageContext.setAttribute("j",j);
      %>
      <tr class="tr2">   
         <td class="tr1" align="middle">${goodsNames[j]}</td>
      <%for(int k=0;k<a;k++){    
      pageContext.setAttribute("k", k);     
      String cc="results"+j+","+k;
      pageContext.setAttribute("cc", cc); 
      %>
      <td class="tr8">需求量<input type="text" name="${cc}" >(${measureUnits[j]})</td>
      <% } %>
      </tr>
      <% } %>
   </table>
   <br/>
   <br/>
   <table id="Tab3" border="0" cellspacing="1" cellpadding="3" style="display:block">
     <%
     Object mm=request.getAttribute("number2");  //节点的数目    
     int aa =Integer.valueOf(String.valueOf(mm));%>
     <tr class="tr2">
        <td class="tr1" align="middle">风险应对策略/物资名称</td>
     <%
     for(int i=0;i<aa;i++){
     pageContext.setAttribute("i",i);
     %>      
        <td class="tr1" align="middle">${WnodeNames[i]}</td>
     <% } %>
     </tr>
     
     <%  //第二行及以后其它各行
     for(int j=0;j<b;j++){
     pageContext.setAttribute("j",j);
      %>
      <tr class="tr2">   
         <td class="tr1" align="middle">${goodsNames[j]}</td>
      <%for(int k=0;k<aa;k++){    
      pageContext.setAttribute("k", k);     
      String cc="results2"+j+","+k;
      pageContext.setAttribute("cc", cc); 
      %>
      <td class="tr8">供应量<input type="text" name="${cc}" >(${measureUnits[j]})</td>
      <% } %>
      </tr>
      <% } %>
   </table>
   <input type=image src="/risk/static/images/pub/lzoa_pub_save.gif" width="67" height="19" style="cursor:hand;border:0px" ;  onClick="return cbt_local();  return false;">    <!-- "要填写该跳转的地方！！！！" -->
   </center>
   <input type="hidden" id="length1"  name="length1" value="${number}">
   <input type="hidden" id="length2"  name="length2" value="${num}">
   <input type="hidden" id="length3"  name="length3" value="${number2}">
</form>



</body>
</html>










