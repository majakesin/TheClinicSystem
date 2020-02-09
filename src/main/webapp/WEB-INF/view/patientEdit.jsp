<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >    
<meta charset="UTF-8">
<title>Nurse</title>

<style type="text/css">
.error {
	color: red;
}
</style>

</head>
<body>


	<div >
		<%@ include file="patientProfileTemplate.jsp"%>
	</div>
	
	<div class="container">
	<div class="row">
	 <div class="col-sm">
		<br> <br> <br>
		
	<form:form method="POST" action="/patientProfile/edit/create/"
		modelAttribute="userDto">
	<form:hidden path="passwordDto"/>
		<table>
			<tr><td><form:hidden path="idDto"/></td></tr>
			<tr><td><form:hidden path="roleDto"/></td></tr>
			
			<tr>
				<td><form:label path="nameDto">Ime:</form:label></td>
				<td><form:input class="form-control" placeholder="Name" path="nameDto" /></td>
			</tr>
			<tr>
				<td><form:label path="surnameDto">Prezime:</form:label></td>
				<td><form:input class="form-control" placeholder="Surname" path="surnameDto" /></td>
			</tr>
<!--  			<tr>
				<td><form:label path="usernameDto">Korisničko ime:</form:label></td>
				<td><form:input class="form-control" path="usernameDto" /></td>
			</tr> -->
			<tr>
				<td><form:label path="addressDto">Adresa:</form:label></td>
				<td><form:input class="form-control" path="addressDto" /></td>
			</tr>
			<tr>
				<td><form:label path="cityDto">Grad:</form:label></td>
				<td><form:input class="form-control" path="cityDto" /></td>
			</tr>
			<tr>
				<td><form:label path="countryDto">Država:</form:label></td>
				<td><form:input class="form-control" path="countryDto" /></td>
			</tr>
			<tr>
				<td><form:label path="phoneDto">Telefon:</form:label></td>
				<td><form:input class="form-control" path="phoneDto" /></td>
			</tr>
			
			<tr>
				<td><form:label path="emailDto">Email:</form:label></td>
				<td><form:input class="form-control" path="emailDto" />
				<form:errors path="emailDto" cssClass="error" /></td>
			</tr>
			
			<tr>
				<td></td>
				<td></td>
				<td><input type="submit" class="btn btn-outline-warning" value="Izmeni" /></td>
			</tr>
		</table>
	</form:form>
	</div>