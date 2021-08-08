<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h4>
						<a href="${pageContext.request.contextPath}/BookServlet?action=home">Home Page</a>
		</h4>
	</center>
	<div align="center">
		<c:if test="${book != null}">
			<form action="${pageContext.request.contextPath}/BookServlet" method="post">
				<input type="hidden" name="action" value="view">
		</c:if>
		<c:if test="${book == null}">
			<form action="${pageContext.request.contextPath}/BookServlet?action=insert" method="post">
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${book != null}">
 Book Details
 </c:if>
				</h2>
			</caption>
			<c:if test="${book != null}">
				<input type="hidden" name="id" value="<c:out value='${book.getBid()}' />" />
			</c:if>
			<tr>
				<th>BID:</th>
				<td><input type="number" name="bid" size="45" required value="<c:out value='${book.getBid()}' />" /></td>
			</tr>
			<tr>
				<th>Book Title:</th>
				<td><input type="text" name="booktitle" size="45" required value="<c:out value='${book.getBookTitle()}' />" /></td>
			</tr>
			<tr>
				<th>Description:</th>
				<td><input type="text" name="description" size="45" required value="<c:out value='${book.getDescription()}' />" /></td>
			</tr>
			<tr>
				<th>Author:</th>
				<td><input type="text" name="author" size="45" required value="<c:out value='${book.getAuthor()}' />" /></td>
			</tr>
			<tr>
				<th>Published Date:</th>
				<td><input type="text" name="publisheddate" size="45" required value="<c:out value='${book.getPublishedDate()}' />" /></td>
			</tr>
			<tr>
				<th>Isbn:</th>
				<td><input type="number" name="isbn" min="1" required value="<c:out value='${book.getIsbn()}' />" /></td>
			</tr>
			<tr>
				<th>Price:</th>
				<td><input type="number" name="price" min="1" required value="<c:out value='${book.getPrice()}' />" /></td>
			</tr>
			<tr>
				<th>No. of Pages:</th>
				<td><input type="number" name="noofpages" min="1" required value="<c:out value='${book.getNoofPages()}' />" /></td>
			</tr>
			<tr>
				<th>Category ID:</th>
				<td><input type="number" name="cid" min="1" required value="<c:out value='${book.getCid()}' />" /></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>