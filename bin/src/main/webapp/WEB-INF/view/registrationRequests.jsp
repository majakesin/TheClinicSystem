<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
<title>Administrator registration requests</title>
</head>
<body>

	<div >
	 <%@ include file="CCAHomeTemplate.jsp" %>
	</div>

	<div class="container">
		<div class="row">
	 	<div class="col-sm">
			<br> <br> <br>
	
	
	
	</div>
	 <div class="col-sm">
	 <br> <br> <br>
	<table class="table">
		<tr style="background-color: #FF756B;">
			<th colspan=4 style="text-align=center;"><h6 style="color:white; letter-spacing: 4px; text-align=center;"> ZAHTEVI </h6> </th>
		</tr>
		<tr>
			<td>Username</td>
			<td>email</td>
			<td>Option</td>
			<td> </td>
			
		</tr>
		<tr>
			<c:forEach var="user" items="${requestsDto}">
				<tr>
					<td><c:out value="${user.usernameDto}" /></td>
					<td><c:out value="${user.emailDto}" /></td>
					<td><a  class="btn btn-outline-success" href="/requests/accept/${user.idDto}">Accept</a></td>
					<td><a  class="btn btn-outline-success" href="/requests/reject/${user.idDto}">Reject</a></td>
				</tr>
			</c:forEach>
		</tr>

	</table>
	</div>
	</div>
	</div>
</body>
</html>