<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta charset="ISO-8859-1">
<title>Registration</title>
<style>


body {
   background-color: #00cc66;
}
	.center {
   margin: auto;
  width: 20%;
  
  padding: 10px;
}

</style>
</head>
<body>

	<br> <br> <br> <br>	
	<div  class="center" style="align:center;">
	
	<form:form method="POST" action="/patient/create" modelAttribute="userDto">
		
		<table >
			<tr>
				<td><form:label path="usernameDto">*Korisnicko ime:</form:label></td>
				<td><form:input class="form-control" path="usernameDto" required="required" /></td>
			</tr>
			
			<tr>
				<td><form:label path="passwordDto">*Sifra:</form:label></td>
				<td><form:input class="form-control" type="password"  path="passwordDto"  required="required"/></td>
			</tr>
			<tr>
		
			<tr>
				<td><form:label path="nameDto">Ime:</form:label></td>
				<td><form:input class="form-control" path="nameDto" /></td>
			</tr>
			<tr>
				<td><form:label path="surnameDto">Prezime:</form:label></td>
				<td><form:input  class="form-control" path="surnameDto" /></td>
			</tr>
			<tr>
				<td><form:label path="addressDto">Adresa:</form:label></td>
				<td><form:input class="form-control" path="addressDto" /></td>
			</tr>
			<tr>
				<td><form:label path="cityDto">Grad:</form:label></td>
				<td><form:input class="form-control" path="cityDto" /></td>
			</tr>
			<tr>
				<td><form:label path="cityDto">Drzava:</form:label></td>
				<td><form:input class="form-control" path="countryDto" /></td>
			</tr>
			<tr>
				<td><form:label path="cityDto">*Broj osiguranika:</form:label></td>
				<td><form:input class="form-control" path="insuranceNumberDto" required="required" /></td>
			</tr>
			<tr>
				<td><form:label path="phoneDto">Mobilni telefon:</form:label></td>
				<td><form:input  class="form-control" path="phoneDto" /></td>
			</tr>
			<tr>
				<td><form:label path="emailDto">Email:</form:label></td>
				<td><form:input class="form-control"  type="email" path="emailDto" required="required"/></td>
			</tr>
			
			<tr>
				<td> </td>
			
				<td colspan="2"> <input class="btn btn-outline-success" type="submit" value="Create" /></td>
				
			</tr>
		</table>
	</form:form>
	</div>
</body>
</html>