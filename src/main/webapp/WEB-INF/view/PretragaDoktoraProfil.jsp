<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<br><br><br><br>
	<div>
	<div class="row">
	<div class="col-md">
	<form:form method="POST" action="/doctors/search3"
		modelAttribute="doctorDto">
	<table class="table" style="width:600">
	<tr style="background-color: #53e3a6;">
	<td> <form:input type="text" id="nameDto" class="form-control" placeholder="Ime" path="nameDto" /> </td>
	<td> <form:input type ="text" id="surnameDto" class="form-control" placeholder="Prezime" path="surnameDto"/> </td>
	<td> <form:input type="text" id="markDto" class="form-control" placeholder="Ocena doktora" path="markDto"/> </td>
	
	<td>
					<form:select class="form-control" path="tipPregledaDto">
						<form:option value="None"></form:option>
						<form:option value="Ginekologija"></form:option>
						<form:option value="Urologija"></form:option>
						<form:option value="Stomatologija"></form:option>
						<form:option value="Hirurgija"></form:option>
						<form:option value="Kardiologija"></form:option>
					</form:select>
				</td>
	
	<td> <input type="submit" class="btn btn-outline-success"> </td>
	
	</table>
	</form:form>
	</div>
	</div>
	<div class="row">
	<div class="col-md">
	<table class="table table-striped">
	
	<tr style="background-color: #53e3a6;">
	<td><h6 style="color:white;  letter-spacing: 4px; text-align=center;">Ime </h6></td>
	<td><h6 style="color:white; letter-spacing: 4px; text-align=center;">Prezime</h6> </td>
	<td><h6 style="color:white; letter-spacing: 4px; text-align=center;">Ocena doktora </h6></td>
	<td><h6 style="color:white; letter-spacing: 4px; text-align=center;">Zakaži termin</h6></td>
	</tr>
	
	<c:forEach var="doctor" items="${doctorsDto}">
	<tr>
	<td><c:out value="${doctor.nameDto}"/> </td>
	<td><c:out value="${doctor.surnameDto}"/> </td>
	<td><c:out value="${doctor.markDto}"/> </td>
	<td><select class="form-control" >
						<option value="null"></option>
						<option value="08:00:AM">08:00:AM</option>
						<option value="08:30:AM">08:30:AM</option>
						<option value="09:00:AM">09:00:AM</option>
						<option value="09:30:AM">09:30:AM</option>
						<option value="10:30:AM">10:30:AM</option>
						<option value="11:00:AM">11:00:AM</option>
						<option value="11:30:AM">11:30:AM</option>
						<option value="12:00:PM">12:00:PM</option>
						<option value="12:30:PM">12:30:PM</option>
						<option value="01:00:PM">01:00:PM</option>
						<option value="01:30:PM">01:30:PM</option>
						<option value="02:00:PM">02:00:PM</option>
						<option value="02:30:PM">02:30:PM</option>
						<option value="03:00:PM">03:00:PM</option>
						<option value="03:30:PM">03:30:PM</option>
					</select>
				</td>
	<td><a class="btn btn-outline-info" href="/kreirajNepredef2/${doctor.idDto}">Zakaži</a></td>
	</tr>
	</c:forEach>
	</table>
	</div>
	</div>
	

	</div>


</body>
</html>