<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- 登陆页面和显示页面在一个页面上 -->
<html>
<head>
<title>Insert title here !</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<style type="text/css">
form {
	width: 300px;
	display: block;
	margin: 200 auto;
}

table {
	display: block;
}

table td {
	padding: 5px;
}
</style>
</head>

<body>
	<c:choose>
		<c:when test="${not empty sessionScope.user}">
   		欢迎你，${sessionScope.user.userName}
   	</c:when>
		<c:otherwise>
			<form method="post"
				action="${pageContext.servletContext.contextPath}/loginServlet">
				<table>
					<tr>
						<td>用户名</td>
						<td><input type="text" name="userName"></td>
					</tr>
					<tr>
						<td>密码</td>
						<td><input type="text" name="password"></td>
					</tr>
					<tr>
						<td><input type="submit" value="登陆"></td>
						<td><input type="reset" value="重置"></td>
					</tr>
				</table>
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>
