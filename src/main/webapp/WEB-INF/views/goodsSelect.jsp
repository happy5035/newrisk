
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>goodsSelect</title>
</head>
<body>   
   <table border="1">
   <tr>
        <td>${SelectedGoodsId}</td>
  	    <td>${SelectedGoodsName}</td>
   		<td>${SelectedGoodsPrice}</td>
   		<td>${SelectedGoodsCode}</td>
   </tr>
   </table>
</body>
</html>