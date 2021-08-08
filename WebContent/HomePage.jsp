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
		<h2>Home Page</h2>
		<center>
			<h4>
				<a href="${pageContext.request.contextPath}/BookServlet?action=list">&nbsp List All Books &nbsp</a> &nbsp 
				<a href="${pageContext.request.contextPath}/BookServlet?action=new">&nbsp Add New Book &nbsp</a> &nbsp 
				<a href="${pageContext.request.contextPath}/BookServlet?action=deletebook">&nbsp Delete Book &nbsp</a> &nbsp 
				<a href="${pageContext.request.contextPath}/BookServlet?action=editbook">&nbsp Edit Book &nbsp</a> <br> &nbsp 
				<a href="${pageContext.request.contextPath}/CategoryServlet?action=list">&nbsp List All Categories &nbsp</a> &nbsp 
				<a href="${pageContext.request.contextPath}/CategoryServlet?action=new">&nbsp Add New Category &nbsp</a> &nbsp 
				<a href="${pageContext.request.contextPath}/CategoryServlet?action=deletecat">&nbsp Delete Category &nbsp</a> &nbsp 
				<a href="${pageContext.request.contextPath}/CategoryServlet?action=editcat">&nbsp Edit Category &nbsp</a>
			</h4>
			<h4>
			<a href="${pageContext.request.contextPath}/login.jsp">Logout</a>
			</h4>
		</center>

		<form action="${pageContext.request.contextPath}/BookServlet?action=search" method="post">
			<input type="hidden" name="action" value="search">

			<table cellpadding="5">
				<tr>
					<td><input type="number" name="id" min="1" />
				</tr>

				</td>
				<td colspan="2" align="center"><input type="submit"
					value="Search" /></td>
				</tr>

			</table>
		</form>
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
		</table>
	</div>
</body>
</html>