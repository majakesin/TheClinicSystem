<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
<title>Clinics</title>
<style>

body {
 background-image: url("pokusaj.jpg");
  background-repeat:  no-repeat;
 }

</style>
</head>
<body>

	<div >
		<%@ include file="CCAHomeTemplate.jsp"%>
	</div>
	
	<div class="container">
	<div class="row">
	 <div class="col-sm">
		<br> <br> <br>
	<form:form method="POST" action="/clinics/create"
		modelAttribute="clinicDto">
		<table>
			<tr>
				<td><form:label path="nameDto">Name:</form:label></td>
				<td><form:input class="form-control" placeholder="Name" path="nameDto" /></td>
			</tr>
			<tr>
				<td><form:label path="adressDto">Address:</form:label></td>
				<td><form:input  class="form-control" placeholder="Address" path="adressDto" /></td>
			</tr>
			<tr>
				<td><form:label path="phoneDto">Phone:</form:label></td>
				<td><form:input  class="form-control" placeholder="Phone" path="phoneDto" /></td>
			</tr>
			<tr>
				<td><form:label path="descriptionDto">Description:</form:label></td>
				<td><form:textarea class="form-control" path="descriptionDto" /></td>
			</tr>

			<tr>
				<td></td>
				<td></td>
				<td><input type="submit" class="btn btn-outline-danger" value="Create clinic" /></td>
			</tr>
		</table>
	</form:form>
	
	</div>
	 <div class="col-sm">
	 <br> <br> <br>
	<table class="table">
		
		<tr style="background-color: #00cc66;">
			<th colspan=8 style="text-align=center;"><h6 style="color:white; letter-spacing: 4px; text-align=center;"> KLINIKE </h6> </th>
		</tr>
		<tr>
			<td>Name</td>
			<td>Address</td>
			<td>Phone</td>
			<td>Description</td>
			
			<td>Delete</td>
			<td>Edit</td>
			<td>More</td>
		</tr>
		<tr>
			<c:forEach var="clinic" items="${clinicsDto}">
				<tr>
					<td><c:out value="${clinic.nameDto}" /></td>
					<td><c:out value="${clinic.adressDto}" /></td>
					<td><c:out value="${clinic.phoneDto}" /></td>
					<td><c:out value="${clinic.descriptionDto}" /></td>
					<td><a class="btn btn-outline-success" href="/clinics/delete/${clinic.idDto}">Delete</a></td>
					<td><a class="btn btn-outline-success" href="/clinics/edit/${clinic.idDto}">Edit</a></td>
					<td><a class="btn btn-outline-info" href="/clinics/details/${clinic.idDto}">Details</a></td>
				</tr>
			</c:forEach>
		</tr>

	</table>
	</div>
	</div>
	</div>
	
	
	
</body>
</html>