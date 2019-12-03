<jsp:include page="../includes/header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section style="text-algin:center">
	<c:set var="book" scope="request" value="${editBook }"></c:set>
	<form action="${pageContext.request.contextPath}/admin/function" method="post">
		<input type="hidden" name="id" value="${book.idbook }"><br>
		Title: <input type="text" name="title" value="${book.title }"><br>
		Image: <input type="text" name="image" value="${book.image }"><br>
		Desc: <input type="text" name="desc" value="${book.desc }"><br>
		Author: <input type="text" name="author" value="${book.author }"><br>
		Price: <input type="number" name="price" value="${book.price }"><br>
		<input type="submit" name="action" value="Edit"> 
	</form>
</section>
<jsp:include page="../includes/footer.jsp"/>