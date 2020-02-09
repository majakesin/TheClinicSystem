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
	<form:form method="POST" action="/clinic/admin/operations/change/appointment"
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
				<td><form:label path="roomId">Soba:</form:label></td>
				<td><form:select class="form-control" path="roomId">
									<c:forEach items="${allRooms}" var="room">
										<form:option value="${room.idDto}">${room.nameDto} br:${room.hallNumberDto}</form:option>
									</c:forEach>
								</form:select></td>
			</tr>
			
			<tr>
				<td></td>
				<td></td>
				<td><input type="submit" class="btn btn-outline-danger" value="Promeni termin" /></td>
			</tr>
		</table>
	</form:form>