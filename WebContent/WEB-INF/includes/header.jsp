<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:set var="loginedUser" scope="session" value="${loginedUser}"/>
	<header>
		<h1>My Book Store</h1>
	</header>
	<nav>
		<ul>
			<li><a href="<c:url value='/home'/>">Home</a></li>
			
			<c:choose>
				<c:when test="${not empty loginedUser.role}">
					<c:if test="${loginedUser.role == 'Admin' }">
						<li><a href="<c:url value='/admin/function?action=insert'/>">Insert Book</a>
					</c:if>
					<c:if test="${loginUser.role == 'User' }">
						<li><a href="<c:url value='/cart'/>">Show Cart</a></li>
					</c:if>
					<li>Hello ${loginedUser.userName }</li>	
					<li><a href="<c:url value='/logout'/>">Logout</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="<c:url value='/login'/>">Login</a></li>
					<li><a href="<c:url value='/cart'/>">Show Cart</a></li>
				</c:otherwise>
			</c:choose>

		</ul>
	</nav>