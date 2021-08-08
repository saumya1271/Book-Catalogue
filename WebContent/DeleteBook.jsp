<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="font-family: arial, serif;">
	<div align="center" cellpadding=10>
		<h2>List of All Books</h2>
		<center>
			<h4>
				<a href="${pageContext.request.contextPath}/BookServlet?action=home">Home Page</a>
			</h4>
		</center>

		<table>
			<tr align=center>
				<th>BID</th>
				<th>Book Title</th>
				<th>Description</th>
				<th>Author</th>
				<th>Published Date</th>
				<th>Isbn</th>
				<th>Price</th>
				<th>No. of Pages</th>
				<th>CID</th>
				<th></th>

			</tr>
			<c:forEach var="book" items="${listBook}">
				<tr align=center>
					<td><c:out value="${book.getBid()}" /></td>
					<td><c:out value="${book.getBookTitle()}" /></td>
					<td><c:out value="${book.getDescription()}" /></td>
					<td><c:out value="${book.getAuthor()}" /></td>
					<td><c:out value="${book.getPublishedDate()}" /></td>
					<td><c:out value="${book.getIsbn()}" /></td>
					<td><c:out value="${book.getPrice()}" /></td>
					<td><c:out value="${book.getNoofPages()}" /></td>
					<td><c:out value="${book.getCid()}" /></td>

					<td>|<a href="${pageContext.request.contextPath}/BookServlet?action=delete&id=<c:out value='${book.getBid()}' />" class="delete">Delete</a>|

					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		var deleteButton = document.querySelectorAll(".delete");

		for (var i = 0; i < deleteButton.length; i++) {
			deleteButton[i]
					.addEventListener(
							"click",
							function(evt) {
								if (confirm('Are you sure you want to delete this? This action cannot be undone.')) {
								} else {
									evt.preventDefault();
								}
							});
		}
	</script>
</body>
</html>