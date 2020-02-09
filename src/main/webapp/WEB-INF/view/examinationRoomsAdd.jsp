<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">




<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Doctors</title>


</head>
<body>

	<div>
		<%@ include file="CAHomeTemplate.jsp"%>
	</div>
	<ul  class="nav nav-tabs justify-content-center nav-fill"  >
		<li class="nav-item "><a  class="nav-link " style="color:#53e3a6;" href="/appointmentRequests" ><i class="fa fa-calendar" aria-hidden="true"></i>Zahtevi </a></li>
		<li class="nav-item "><a class="nav-link " style="color:#53e3a6;" href="operationList"><i class="fa fa-scissors"></i> Operacije</a></li>
		<li class="nav-item "><a class="nav-link " style="color:#53e3a6;" href="/createTerm"><i class="fa fa-calendar" aria-hidden="true"></i>  Kreiraj termin</a></li>
	</ul>
	
	<form:form method="POST" action="/examination/rooms/create>
	<table class="table">
		<tr>
		<td><a href="${pageContext.request.contextPath}/roomsSearch">Rezervisi salu</a></td></tr>
	</table>

	<br/>
	<br/>
	
	<table class="table">
		<tr><td>Rezervisana sala</td></tr>
		<tr><td>${selectedRoom.nameDto} broj:${selectedRoom.hallNumberDto}</td></tr>
	</table>

</body>
</html>