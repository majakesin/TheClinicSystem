<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Clinics</title>
</head>
<body>

    <div>
		<%@ include file="patientHome.jsp" %>
	</div>
<br> <br>

<div>
	<div class="row">
	<div class="col-md">
	<form:form method="POST" action="/clinics/search"
		modelAttribute="clinicDto">
	<table class="table" style="width:600">
	<tr style="background-color: #53e3a6;">
	<td> <form:input type="text" id="adressDto" class="form-control" placeholder="Adresa klinike" path="adressDto" /> </td>
	
	<td> <form:input type="text" id="markDto" class="form-control" placeholder="Ocena klinike" path="markDto" /> </td>
	
	<td> <input type="submit" class="btn btn-outline-success"> </td>
	
	
	
	</table>
	</form:form>
	</div>
	</div>
	
	<div class="row">
	<div class="col-md">
	<table class="table table-striped">
	
	<tr style="background-color: #53e3a6;">
	<td><h6 style="color:white;  letter-spacing: 4px; text-align=center;">Naziv klinike </h6></td>
	<td><h6 style="color:white; letter-spacing: 4px; text-align=center;">Adresa klinike </h6> </td>
	<td><h6 style="color:white; letter-spacing: 4px; text-align=center;">Prosečna ocena</h6> </td>

	</tr>
	<c:forEach var="var" items="${clinicsDto}">
	<tr>
	<td><c:out value="${var.nameDto}"/> </td>
	<td><c:out value="${var.adressDto}"/> </td>
	<td><c:out value="${var.markDto}"/> </td>
	</tr>
	</c:forEach>
	</table>
	</div>
	</div>
	
	
</div>


</body>
</html>