<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/gov_style_10.css">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>更新系统用户信息失败</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
function cbt_local()
{
	province.options[province.selectedIndex].value=province.options[province.selectedIndex].text;
	city.options[city.selectedIndex].value=city.options[city.selectedIndex].text;
	district.options[district.selectedIndex].value=district.options[district.selectedIndex].text;
}
</script>

  </head>
  
  <body>
<form method="post" action="listSystemuser.action">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="<%=request.getContextPath()%>/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="100%" cellspacing="0" cellpadding="0">
                <tr>
					<td width="100%" class="f3">系统用户信息管理</td>
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
					<td  class="tr1" align="center">更新系统用户信息失败！</td>
				</tr>
			</table>
		</td>
    </tr>
    <tr>
		<td align="center" class="tr4"><a href="listSystemuser.action">返回</a></td> 
	</tr>
</table>
</form> 
</body>
</html>
