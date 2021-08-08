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
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>