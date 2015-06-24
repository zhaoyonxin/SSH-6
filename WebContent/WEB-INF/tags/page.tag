<%@ tag pageEncoding="UTF-8"%><%@ attribute name="page"
	type="com.example.utils.Page" required="true"%><%@ attribute
	name="queryString" type="java.lang.String" required="true"%><%@ taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><c:set var="ctx"
	value="${pageContext.request.contextPath}"></c:set>
<div id="page" style="text-align: center; padding: 6px 6px 8px 0;">
	共${page.totalElements}条记录 &nbsp;当前第${page.number}页
	&nbsp;共${page.totalPages}页 &nbsp;
	<c:if test="${page.number>1}">
		<a href='?${queryString}&pageNum=1'>首页</a>&nbsp;<a
			href="?${queryString}&pageNum=${page.number-1}">上一页</a>&nbsp;</c:if>
	<c:if test="${page.number<page.totalPages}">
		<a href='?${queryString}&pageNum=${page.number+1}'>下一页</a>&nbsp;<a
			href='?${queryString}&pageNum=${page.totalPages}'>末页</a> &nbsp;</c:if>
	&nbsp;转到 <input id="pageNum" type="text" size='1' /> 页 &nbsp;
</div>
<script type="text/javascript">
	$(function() {
		$("#pageNum").change(function() {
			var page = $(this).val();
			page = $.trim(page);
			if (window.isNaN(page)) {
				alert("输入不合法,请重新输入");
				$(this).val("");
				return;
			}
			var pageNum = parseInt(page);
			if (pageNum<1 || pageNum>parseInt("${page.totalPages}")) {
				alert("输入不合法,请重新输入");
				$(this).val("");
				return;
			}
			window.location.href = "?${queryString}&pageNum=" + pageNum;
		});
	});
</script>
