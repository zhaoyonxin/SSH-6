<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<%@include file="/commons/common.jsp" %><%@taglib tagdir="/WEB-INF/tags" prefix="tags" %>
		<title>用户管理列表</title>
		<link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css"/>
		<link rel="stylesheet" href="${ctx}/static/css/user-list.css"/>
	</head>
	<body>
		<%@include file="/commons/head.jsp" %>

    <div class="container-fluid">
      <div class="row">
        <%@include file="/commons/left.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">Dashboard</h1>

          <div class="row placeholders">
            <div class="col-xs-6 col-sm-3 placeholder">
              <img data-src="holder.js/200x200/auto/sky" class="img-responsive" alt="Generic placeholder thumbnail">
              <h4>Label</h4>
              <span class="text-muted">Something else</span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
              <img data-src="holder.js/200x200/auto/vine" class="img-responsive" alt="Generic placeholder thumbnail">
              <h4>Label</h4>
              <span class="text-muted">Something else</span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
              <img data-src="holder.js/200x200/auto/sky" class="img-responsive" alt="Generic placeholder thumbnail">
              <h4>Label</h4>
              <span class="text-muted">Something else</span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
              <img data-src="holder.js/200x200/auto/vine" class="img-responsive" alt="Generic placeholder thumbnail">
              <h4>Label</h4>
              <span class="text-muted">Something else</span>
            </div>
          </div>

          <h2 class="sub-header">用户列表</h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                 <th>ID</th>
				<th>用户名</th>
				<th>邮箱</th>
				<th>手机</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${page.content}" var="user">
					<tr>
						<td>${user.id}</td>
						<td>${user.username}</td>
						<td>${user.email}</td>
						<td>${user.phone}</td>
					</tr>
				</c:forEach>
              </tbody>
            </table>
            <tags:page page="${page}" queryString="${queryString}"></tags:page>
          </div>
        </div>
      </div>
    </div>
	</body>
</html>