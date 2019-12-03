<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../includes/header.jsp"/>

<h3>Login Page</h3>
<p style="color: red;">${errorString}</p>
<form action="${pageContext.request.contextPath}/login" method="POST" >
	User Name: <input type="text" name="userName">
	<br>
	Password: <input type="password" name="password">
	<br>
	<input type="submit" value="Login">
</form>

<jsp:include page="../includes/footer.jsp"/>