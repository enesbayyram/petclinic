<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Anasayfa</title>
</head>
<body>
	<table>
		<tr style="font-weight: bold;">
			<th>Id</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Username</th>
			<th>Password</th>
			<th>BirhOfDate</th>
		</tr>
		<c:forEach items="${users}" var="user" varStatus="status">
			<tr bgcolor="${status.index%2==0 ? 'white' : 'yellow'}">
				<td>${user.id}</td>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
				<td>${user.userDetail.username}</td>
				<td>${user.userDetail.password}</td>
				<td>${user.userDetail.birthOfDate}</td>
			</tr>
		</c:forEach>
	</table>	
</body>
</html>