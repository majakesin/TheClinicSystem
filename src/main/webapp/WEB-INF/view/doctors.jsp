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
</head>
<body>

	<div >
		<%@ include file="CAHomeTemplate.jsp"%>
	</div>
	
	
<div class="container">
	<div class="row">
	 <div class="col-sm">
		<br> <br> <br>
	<form:form method="POST" action="/doctors/create"
		modelAttribute="userDto">
		<table>
			<tr>
				<td><form:label path="nameDto">Ime:</form:label></td>
				<td><form:input class="form-control" placeholder="Name" path="nameDto" /></td>
			</tr>
			<tr>
				<td><form:label path="surnameDto">Prezime:</form:label></td>
				<td><form:input class="form-control" placeholder="Surname" path="surnameDto" /></td>

			<tr>
				<td><form:label path="usernameDto">Korisnicko ime:</form:label></td>
				<td><form:textarea class="form-control" path="usernameDto" /></td>
			</tr>
			<tr>
				<td><form:label path="passwordDto">Sifra:</form:label></td>
				<td><form:textarea class="form-control" path="passwordDto" /></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td><input type="submit" class="btn btn-outline-danger" value="Create Doctor" /></td>
			</tr>
		</table>
	</form:form>
	
	</div>
	 <div class="col-sm">
	 <br> <br> <br>
	<table class="table">
		
		<tr style="background-color: #00cc66;">
			<th colspan=8 style="text-align=center;"><h6 style="color:white; letter-spacing: 4px; text-align=center;"> MEDICINSKO OSOBLJE </h6> </th>
		</tr>
		<tr>
			<td>Ime</td>
			<td>Adresa</td>
			<td>Mobilni telefon</td>
			<td>Biografija</td>
			
			<td>Obrisi</td>
			<td>Izmeni</td>
			<td>Vise</td>
		</tr>
		<tr>
			<c:forEach var="user" items="${doctorsDto}">
				<tr>
					<td><c:out value="${user.nameDto}" /></td>
					<td><c:out value="${user.surnameDto}" /></td>
					<td><c:out value="${user.phoneDto}" /></td>
					<td><c:out value="${user.biographyDto}" /></td>
					<td><a class="btn btn-outline-success" href="/doctors/delete/${user.idDto}">Delete</a></td>
					<td><a class="btn btn-outline-success" href="/doctors/edit/${user.idDto}">Edit</a></td>
					<td><a class="btn btn-outline-info" href="/clinics/details/${user.idDto}">Details</a></td>
				</tr>
			</c:forEach>
		</tr>

	</table>
	</div>
	</div>
	</div>
	
	
	
	
</body>
</html>