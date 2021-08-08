<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2 style="text-align: center">
		<c:out value="Welcome ${uname}!" />
	</h2>
	<br>
	<c:out value="Click " />
	<a href="${pageContext.request.contextPath}/BookServlet?action=home">Here</a>
	<c:out value=" to go to Home Page." />
	<br>
	<c:out value="Click " />
	<a href="${pageContext.request.contextPath}/login.jsp">Here</a>
	<c:out value=" to Logout" />
</body>
</html>