<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Administrator registration</title>
</head>
<body>

	<h2>Register administrator</h2>
	<form:form method="POST" action="/administrators/create"
		modelAttribute="userDto">
		<table>
			<tr>
				<td><form:label path="usernameDto">Username:</form:label></td>
				<td><form:input path="usernameDto" /></td>
			</tr>
			<tr>
				<td><form:label path="passwordDto">Password:</form:label></td>
				<td><form:input path="passwordDto" /></td>
			</tr>
			<tr>
				<td>
					<form:select path="roleDto">
						<form:option value="CCA"></form:option>
						<form:option value="CA"></form:option>
					</form:select>
				</td>
			</tr>
			
			
			<tr>
				<td></td>
				<td></td>
				<td><input type="submit" value="Register administrator" /></td>
			</tr>
		</table>
	</form:form>
	
	
	<table>
		<tr>
			<th>Username</th>
			<th>Password</th>
			<th>Role</th>
			
		</tr>
		<tr>
			<c:forEach var="user" items="${usersDto}">
				<tr>
					<td><c:out value="${user.usernameDto}" /></td>
					<td><c:out value="${user.passwordDto}" /></td>
					<td><c:out value="${user.roleDto}" /></td>
					<td><a href="/clinics/delete/${user.idDto}">Delete</a></td>
				</tr>
			</c:forEach>
		</tr>

	</table>

</body>
</html>