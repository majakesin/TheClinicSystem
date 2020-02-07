<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
.grid-container {
  display: grid;
  grid-template-columns: auto auto auto;
  grid-template-rows: 220px 1000px;
  grid-gap: 70px;
 
  padding: 10px;
}

.grid-container > div {
  
  text-align: center;
  padding: 20px 0;
  
}
</style>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<div>
		<%@ include file="patientHome.jsp" %>
	</div>
	
	<div class="grid-container">
  <div>
  	<div class="card border-danger mx-sm-1 p-3">
  	<div class="text-danger text-center mt-2"><i class="fa fa-hospital-o" aria-hidden="true"></i></div>
  	<div class="text-danger text-center mt-1"><h3>${clinicDto.nameDto }</h3></div>
  	<div class="text-danger text-center mt-1"><h5>${clinicDto.adressDto }</h5></div>
  	<div class="text-danger text-center mt-1"><h5>${clinicDto.phoneDto }</h5></div>
  	<div class="text-danger text-center mt-1"><h3>${clinicDto.markDto}</h3></div>
  	</div>
  
  </div>
  <div>
  	<div class="row">
	<div class="col-md">
	<form:form method="POST" action="/doctors/search2/${clinicDto.idDto}"
		modelAttribute="doctorDtoP">
	<table class="table" style="width:600">
	<tr style="background-color: #53e3a6;">
	<td> <form:input type="text" id="nameDto" class="form-control" placeholder="Ime" path="nameDto" /> </td>
	<td> <form:input type ="text" id="surnameDto" class="form-control" placeholder="Prezime" path="surnameDto"/> </td>
	<td> <form:input type="text" id="markDto" class="form-control" placeholder="Ocena doktora" path="markDto"/> </td>
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
	<td><h6 style="color:white; letter-spacing: 4px; text-align=center;">Vreme </h6></td>
	<td><h6 style="color:white; letter-spacing: 4px; text-align=center;">Zakaži</h6></td>
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
			
		<td><a class="btn btn-outline-success" href="/kreirajNP1/${doctor.idDto}">Zakaži</a></td>
	</tr>
	</c:forEach>
	</table>
	</div>
	</div>
	
  
  
   </div>
   
</div>
<br> <br>
</body>
</html>