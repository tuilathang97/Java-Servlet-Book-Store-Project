<jsp:include page="../includes/header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section style="text-algin:center">
	<form action="${pageContext.request.contextPath}/admin/function" method="post">
		Title: <input type="text" name="title" value=""><br>
		Image: <input type="text" name="image" value=""><br>
		Desc: <input type="text" name="desc" value=""><br>
		Author: <input type="text" name="author" value=""><br>
		Price: <input type="number" name="price" value=""><br>
		<input type="submit" name="action" value="Insert"> 
	</form>
</section>
<jsp:include page="../includes/footer.jsp"/>