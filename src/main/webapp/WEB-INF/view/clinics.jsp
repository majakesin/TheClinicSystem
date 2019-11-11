<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clinics</title>
</head>
<body>
	<h2>Register clinic</h2>

	<form:form method="POST" action="/clinics/create"
		modelAttribute="clinicDto">
		<table>
			<tr>
				<td><form:label path="nameDto">Name:</form:label></td>
				<td><form:input path="nameDto" /></td>
			</tr>
			<tr>
				<td><form:label path="adressDto">Address:</form:label></td>
				<td><form:input path="adressDto" /></td>
			</tr>
			<tr>
				<td><form:label path="phoneDto">Phone:</form:label></td>
				<td><form:input path="phoneDto" /></td>
			</tr>
			<tr>
				<td><form:label path="descriptionDto">Description:</form:label></td>
				<td><form:input path="descriptionDto" /></td>
			</tr>

			<tr>
				<td></td>
				<td></td>
				<td><input type="submit" value="Create clinic" /></td>
			</tr>
		</table>
	</form:form>
	<br />
	<br />
	<h2>Registered clinics</h2>

	<table>
		<tr>
			<th>Name</th>
			<th>Address</th>
			<th>Phone</th>
			<th>Description</th>
			<th>Option</th>
		</tr>
		<tr>
			<c:forEach var="clinic" items="${clinicsDto}">
				<tr>
					<td><c:out value="${clinic.nameDto}" /></td>
					<td><c:out value="${clinic.adressDto}" /></td>
					<td><c:out value="${clinic.phoneDto}" /></td>
					<td><c:out value="${clinic.descriptionDto}" /></td>
					<td><a href="/clinics/delete/${clinic.idDto}">Delete</a></td>
					<td><a href="/clinics/edit/${clinic.idDto}">Edit</a></td>
					<td><a href="/clinics/details/${clinic.idDto}">Details</a></td>
				</tr>
			</c:forEach>
		</tr>

	</table>
	<br/>
	<br/>
	
	
	
</body>
</html>