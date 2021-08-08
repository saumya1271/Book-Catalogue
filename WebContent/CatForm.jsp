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
		<c:if test="${category != null}">
			<form action="${pageContext.request.contextPath}/CategoryServlet" method="post">
				<input type="hidden" name="action" value="update">
		</c:if>
		<c:if test="${category == null}">
			<form action="${pageContext.request.contextPath}/CategoryServlet?action=insert" method="post">
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${category != null}">
 Edit Category
 </c:if>
					<c:if test="${category == null}">
 Add New Category
 </c:if>
				</h2>
			</caption>
			<c:if test="${category != null}">
				<input type="hidden" name="id" value="<c:out value='${category.getCatCid()}' />" />
			</c:if>
			<tr>
				<th>Category Title:</th>
				<td><input type="text" placeholder="eg: Fantasy, Thriller" name="categorytitle" size="45" required value="<c:out value='${category.getCategoryTitle()}' />" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Save" /></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>