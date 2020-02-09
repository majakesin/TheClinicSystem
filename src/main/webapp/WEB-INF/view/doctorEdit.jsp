<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">




<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
<title>Doctors</title>


</head>
<body>

	<div >
		<%@ include file="CAHomeTemplate.jsp"%>
	</div>
	
	
<div class="container">
	<div class="row">
	 <div class="col-sm">
		<br> <br> <br>
	<form:form method="POST" action="/doctors/edit/create"
		modelAttribute="userDto">
		<table>
			<tr>
				<td><form:hidden path="idDto"/></td>
			</tr>
			<tr>
				<td><form:label path="nameDto">Ime:</form:label></td>
				<td><form:input class="form-control" placeholder="Name" path="nameDto" />
				<form:errors path="nameDto" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="surnameDto">Prezime:</form:label></td>
				<td><form:input class="form-control" placeholder="Surname" path="surnameDto" />
				<form:errors path="surnameDto" cssClass="error" /></td>

		<!--  	<tr>
				<td><form:label path="usernameDto">Korisničko ime:</form:label></td>
				<td><form:hidden class="form-control" path="usernameDto" readonly="true" />
				<form:errors path="usernameDto" cssClass="error" /></td>
			</tr> -->
			<tr>
				<td><form:label path="passwordDto">Šifra:</form:label></td>
				<td><form:input class="form-control" path="passwordDto" />
				<form:errors path="passwordDto" cssClass="error" /></td>
			
				
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td><input type="submit" class="btn btn-outline-warning" value="Izmeni" /></td>
			</tr>
		</table>
	</form:form>
	
	</div>