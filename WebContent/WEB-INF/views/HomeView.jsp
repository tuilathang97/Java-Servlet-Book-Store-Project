<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../includes/header.jsp"/>

<section style="text-algin:center">
	<table cellpadding="2" cellspacing="2" border="1">
		<tr>
			<th>Title</th>
			<th>Image</th>
			<th>Author</th>
			<th>Description</th>
			<th>Price</th>
		</tr>
		
		<c:forEach var="book" items="${bookList }">
			<tr>
				<td>${book.title }</td>
				<td><img src="${pageContext.request.contextPath }/images/${book.image}" width="120" height="120"/></td>
				<td>${book.author}</td>
				<td>${book.desc }</td>
				<td>${book.price }</td>
				<td><a href="${pageContext.request.contextPath }/cart?action=buy&id=${book.idbook }">Buy</a></td>
			<tr>
		</c:forEach>
		
	</table>
</section>

<jsp:include page="../includes/footer.jsp"/>