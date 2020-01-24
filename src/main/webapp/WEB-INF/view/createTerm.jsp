<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">





<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
<title>Create Term</title>


</head>
<body>

	<div >
		<%@ include file="CAHomeTemplate.jsp"%>
	</div>
	
	<ul  class="nav nav-tabs justify-content-center nav-fill"  >
		<li class="nav-item "><a  class="nav-link " style="color:#53e3a6;" href="/appointmentRequests" ><i class="fa fa-calendar" aria-hidden="true"></i>Zahtevi </a></li>
		<li class="nav-item "><a class="nav-link " style="color:#53e3a6;" href="/operations"><i class="fa fa-scissors"></i> Operacije</a></li>
		<li class="nav-item "><a class="nav-link " style="color:#53e3a6;" href="/createTerm"><i class="fa fa-calendar" aria-hidden="true"></i>  Kreiraj termin</a></li>
	</ul>
<div class="container">
	<div class="row">
	 <div class="col-sm">
		<br> <br> <br>
	<form:form method="POST" action="/createTerm/create"
		modelAttribute="appointmentDto">
		<table>
			<tr>
				<td><form:label path="dateDto">Datum:</form:label></td>
				<td><form:input type="date" class="form-control" placeholder="Datum" path="dateDto"  /></td>
			</tr>
			<tr>
				<td><form:label path="timeDto">Vreme:</form:label></td>
				<td><form:input type="time" class="form-control" placeholder="Vreme" path="timeDto" /></td>

			<tr>
				<td><form:label path="roomDto">Soba:</form:label></td>
				<td>
					<form:select class="form-control" path="roomDto">				
							<c:forEach items="${allRooms}" var="room">
								<form:option value="${room.nameDto} br:${room.hallNumberDto}"></form:option>
							</c:forEach>
					</form:select>
				</td>
			</tr>
			<tr>
				<td><form:label path="dateDto">Tip pregleda:</form:label></td>
				<td>
					<form:select class="form-control" path="typeDto">
						<form:option value="Operacija"></form:option>
						<form:option value="Pregled"></form:option>
					</form:select>
				</td>
			</tr>
			<tr>
				<td><form:label path="priceDto">Cena:</form:label></td>
				<td><form:input class="form-control" placeholder="Cena" path="priceDto" /></td>
			
				
			</tr>
			<tr>
				<td><form:label path="discountDto">Popust:</form:label></td>
				<td><form:input class="form-control" placeholder="Popust" path="discountDto" /></td>
			
				
			</tr>
				
				<br/>
				<br/>
			<tr>
				<td><form:label path="roomDto">Doktor:</form:label></td>
				<td>
				<form:select class="form-control" path="doctorDto">				
							<c:forEach items="${allMedics}" var="medic">
								<form:option value="${medic.idDto}" >${medic.roleDto}: ${medic.nameDto} ${medic.surnameDto}</form:option>
							</c:forEach>
				</form:select>
				</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td><input type="submit" class="btn btn-outline-danger" value="Kreiraj" /></td>
			</tr>
		</table>
	</form:form>
	
	</div>
	 <div class="col-sm">
	 <br> <br> <br>
	<table class="table">
		
		<tr style="background-color: #53e3a6;">
			<th colspan=8 style="text-align=center;"><h6 style="color:white; letter-spacing: 4px; text-align=center;"> TERMINI PREGLEDA </h6> </th>
		</tr>
		<tr>
			<td>Datum</td>
			<td>Vreme</td>
			<td>Sala</td>
			<td>Tip pregleda</td>
			<td>Cena</td>
			<td>Popust</td>
			<td>Doctor</td>
			
			<td> </td>
			
		</tr>
		<tr>
			<c:forEach var="term" items="${termsDto}">
				<tr>
			  		<td><c:out value="${term.dateDto}" /></td> 
			 		<td><c:out value="${term.timeDto}" /></td> 
					<td><c:out value="${term.roomDto}" /></td>
					<td><c:out value="${term.typeDto}" /></td>
					<td><c:out value="${term.priceDto}" /></td>
					<td><c:out value="${term.discountDto}" /></td>
					<td><c:out value="${term.doctorDto}" /></td>
					<td><a class="btn btn-outline-success" href="/createTerm/delete/${term.idDto}">Delete</a></td>
					
				
				</tr>
			</c:forEach>
		</tr>

	</table>
	</div>
	</div>
	</div>
	
	
	
	
</body>
</html>