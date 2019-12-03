
<jsp:include page="../includes/header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section style="text-algin:center">
	<table cellpadding="2" cellspacing="2" border="1">
		<tr>
			<th></th>
			<th>Id</th>
			<th>Name</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Sum</th>
		</tr>
		<c:set var="total" value="0"></c:set>
		<c:forEach var="item" items="${sessionScope.cartList }">
			<c:set var="total" value="${total + item.book.price * item.quantity }"></c:set>
			<tr>
				<td align="center">
					<a href="${pageContext.request.contextPath }/cart?action=remove&id=${item.book.idbook }"
					onclick="return confirm('Are you sure?')">Remove</a>
				</td>
				<td>${item.book.idbook }</td>
				<td>${item.book.title }</td>
				<td>${item.book.price }</td>
				<td>
					<form action="${pageContext.request.contextPath }/cart" method="get">
						<input type="hidden" name="id" value=${item.book.idbook }>
						<input type="number" value="${item.quantity }" name="updateQuantity">
						<input type="submit" name="action" value="Update">
					</form>
				</td>
				<td>${item.book.price * item.quantity }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="right">Total</td>
			<td>${total }</td>
		</tr>
	</table>
	<br>
	<a href="${pageContext.request.contextPath }/home">Continue Shopping</a>
</section>
<jsp:include page="../includes/footer.jsp"/>