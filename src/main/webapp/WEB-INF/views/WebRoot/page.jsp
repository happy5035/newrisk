<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">

function submitUrl(page){
	var percount=${pageBean.pagecount };
	var url="${pageBean.url}&page="+page+"&percount="+percount;
	url=encodeURI(url); 
	window.location.href=url;
}

function goToPage(){
	var page=document.getElementById("go");
	var rule = /^[0-9]+$/;
	if(!rule.test(page.value)){
		alert("请输入有效的页码!");
		page.focus();
		return;
	}
	var pageCount = ${pageBean.totalpage };
	if(page.value>pageCount){
		alert("输入的页码过大!");
		page.focus();
		return;
	}
	if(page.value<1){
		alert("输入的页码必须大于0!");
		page.focus();
		return;
	}
	submitUrl(page.value);
}
</script>
<c:if test="${pageBean.hasFirst}">
	<a href="#" onclick="submitUrl('${pageBean.first }')">首页</a>
</c:if>
<c:if test="${!pageBean.hasFirst}">
	首页
</c:if>
<c:if test="${pageBean.hasPreview}">
	<a href="#" onclick="submitUrl('${pageBean.preview }')">上一页</a>
</c:if>
<c:if test="${!pageBean.hasPreview}">
	上一页


</c:if>
<c:if test="${pageBean.hasNext}">
	<a href="#" onclick="submitUrl('${pageBean.next }')">下一页</a>
</c:if>
<c:if test="${!pageBean.hasNext}">
	下一页


</c:if>
<c:if test="${pageBean.hasEnd}">
	<a href="#" onclick="submitUrl('${pageBean.end }')">未页</a>
</c:if>
<c:if test="${!pageBean.hasEnd}">
	未页
</c:if>
到第<input type="text" id="go" size="4" maxlength="5" style="width: 20px" value="${pageBean.current}" />页


<input type="button" class="btn7" value="GO" onclick="goToPage();" />
共${pageBean.totalpage }页&nbsp;&nbsp;${pageBean.pagecount }条/页&nbsp;&nbsp;共${pageBean.total}条


