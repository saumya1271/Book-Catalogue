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
		<h2>List of All Categories</h2>
		<center>
			<h4>
				<a href="${pageContext.request.contextPath}/BookServlet?action=home">Home Page</a>
			</h4>
		</center>

		<table>
			<tr align=center>
				<th>CID</th>
				<th>Category Title</th>
				<th></th>
			</tr>
			<c:forEach var="category" items="${listCategory}">
				<tr align=center>
					<td><c:out value="${category.getCatCid()}" /></td>
					<td><c:out value="${category.getCategoryTitle()}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>