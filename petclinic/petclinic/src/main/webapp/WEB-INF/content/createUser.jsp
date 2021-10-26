<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Create User</title>
</head>
<body>
	<form:form modelAttribute="user" method="post">
		Firstname : <form:input path="firstName"/>
		Lasname   : <form:input path="lastName"/>
		<form:button name="submit">Create</form:button>
	</form:form>
</body>
</html>