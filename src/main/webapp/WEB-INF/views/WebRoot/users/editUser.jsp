
<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="<c:url value="/static/style/gov_style_10.css" />">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>修改用户信息</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="<c:url value="/static/js/js/jquery-1.8.0.js"/>"></script> 
	<script type="text/javascript" src="<c:url value="/static/js/js/My97DatePicker/WdatePicker.js"/>"></script>
<script type="text/javascript">
function cbt_res()
{
	document.getElementById("stuForm").reset();
	return false;
}
</script>

  </head>
  
  <body>
<form name="stuForm" method="post" action="updateUser">
<table border="0" width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="100%" height="27" bgcolor="#E3EBFE"><table border="0" width="100%"
    cellspacing="0" cellpadding="0" height="27">
        <tr>
          <td width="3%"><img src="/risk/static/images/desktop/icon-main-001.gif" width="29" height="27"></td>
          <td width="47%"><table border="0" width="503" cellspacing="0" cellpadding="0" height="22">
                <tr>
					<td width="100%" class="f3">用户信息管理>>修改用户信息</td>
                </tr>
            </table></td>
          <td width="50%"></td>
        </tr>
      </table></td>
  </tr>
</table>
<table align="center" width="50%" border="0" cellspacing="1" cellpadding="3">
	<tr>
		<td align="center" class="tr4"> 
			<table border="0" cellpadding="3" cellspacing="1" class="table3">
							<tr class="tr2">
			<td  class="tr1">用户姓名:</td>
					<td><input type="text" name="username" value="${users.username}"></td>
			</tr>
				<tr class="tr2">
					<td  class="tr1">出生日期:</td>
					<td><input class="Wdate" type="text" name="birday" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${users.birdayS}"><font color=red> &lt;- 点击选择日期</font></td>	
					</tr>
					<tr class="tr2">
					<td  class="tr1">证件类型:</td> 
					<td>
						<select name="certitype">
							<option value="1"${selectState1}>身份证</option>
							<option value="0"${selectState2}>其他</option>
						</select>
					</td>					
				</tr>	
					<tr class="tr2">
					<td  class="tr1">证件号码:</td>
					<td><input type="text" name="certicobe" value="${users.certicobe}"></td>	
									
				</tr>	
					<tr class="tr2">
					<td  class="tr1">联系电话:</td>
                   <td><input type="text" name="cellphone" value="${users.cellphone}" ></td>				
                   </tr>
                   <tr class="tr2">
					<td  class="tr1">手机:</td>
                   <td><input type="text" name="telephone" value="${users.telephone}"></td>				
                   </tr>
				<tr class="tr2">
			<td  class="tr1">QQ:</td>
					<td><input type="text" name="userqq" value="${users.userqq}"></td>
			</tr>
				<tr class="tr2">
					<td  class="tr1">邮箱:</td>
					<td><input type="text" name="email" value="${users.email}"></td>
					</tr>
					
					<tr class="tr2">
					<td  class="tr1">注册时间:</td>
					<td><input class="Wdate" type="text" name="registertime" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${users.registertimeS}"><font color=red> &lt;- 点击选择日期</font></td>				
				</tr>	
					<tr class="tr2">
					<td  class="tr1">用户类型:</td>
					<td><input type="text" name="usertype" value="${users.usertype}"></td>	
									
				</tr>	
					<tr class="tr2">
					<td  class="tr1">用户状态:</td>
                    <td>
						<select name="userstate">
							<option value="1"${selectsynState1}>在职</option>
							<option value="0"${selectsynState2}>离职</option>
						</select>
					</td> 
                   </tr>                                     
					<tr class="tr2">
					<td  class="tr1">密保问题:</td>
                   <td><input type="text" name="pwdquestion" value="${users.pwdquestion}"></td>				
                   </tr>
                   <tr class="tr2">
					<td  class="tr1">密保答案:</td>
                   <td><input type="text" name="pwdanswer" value="${users.pwdanswer}"></td>				
                   </tr>
                   <tr class="tr2">
					<td  class="tr1">备注:</td>
                   <td><input type="text" name="note" value="${users.note}"></td>				
                   </tr>
			
			
				<tr class="tr10">
    	  			<td align="center" colspan="4">
	   					<input type=image src="/risk/static/images/pub/lzoa_pub_save.gif" width="67" height="19" style="cursor:hand;border:0px" ;return false;">&nbsp;&nbsp;		
						<input type="image" src="/risk/static/images/pub/lzoa_pub_reset.gif" width="67" height="19" style="cursor:hand" onClick="return cbt_res();">
	   					<a href="javascript:history.back(-1);"><img border="0" src="/risk/static/images/pub/lzoa_pub_back.gif"/></a>         
	  				</td>
  				</tr>
			</table>
		</td>
    </tr>
</table>
       
 <input type="hidden" name="userid" id="userid" value="${users.userid}"/>
                       
</form> 
</body>
</html>
