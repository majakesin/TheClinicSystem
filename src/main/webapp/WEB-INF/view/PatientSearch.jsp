<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PatientSearch</title>
</head>
<body>

	

	<div>
	<div class="row">
	<div class="col-md">
	<form:form method="POST" action="/patient/search"
		modelAttribute="patientDto">
	<table class="table" style="width:600">
	<tr style="background-color: #53e3a6;">
	<td> <form:input type="text" id="nameDto" class="form-control" placeholder="Ime" path="nameDto" /> </td>
	<td> <form:input type ="text" id="surnameDto" class="form-control" placeholder="Prezime" path="surnameDto"/> </td>
	<td> <form:input type="text" id="insuranceNumberDto" class="form-control" placeholder="JDBP" path="insuranceNumberDto"/> </td>
	<td> <input type="submit" class="btn btn-outline-success"> </td>
	
	</table>
	</form:form>
	</div>
	</div>
	<div class="row">
	<div class="col-md">
	<table class="table table-striped">
	
	<tr style="background-color: #53e3a6;">
	<td><h6 style="color:white;  letter-spacing: 4px; text-align=center;">Ime: </h6></td>
	<td><h6 style="color:white; letter-spacing: 4px; text-align=center;">Prezime:</h6> </td>
	<td><h6 style="color:white; letter-spacing: 4px; text-align=center;"> Jedinstveni maticni broj </h6></td>
	</tr>
	<c:forEach var="patient" items="${patientsDto}">
	<tr>
	<td><c:out value="${patient.nameDto}"/> </td>
	<td><c:out value="${patient.surnameDto}"/> </td>
	<td><c:out value="${patient.insuranceNumberDto}"/> </td>
	<td><a href="${pageContext.request.contextPath}/doctors/records/${patient.idDto}">Pregled pacijenta</a>
	</tr>
	</c:forEach>
	</table>
	</div>
	</div>
	

	</div>

</body>
</html>