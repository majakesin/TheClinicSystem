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
		<li class="nav-item "><a class="nav-link " style="color:#53e3a6;" href="/operations"><i class="fa fa-scissors"></i> Operacije</a></li>
		<li class="nav-item "><a class="nav-link " style="color:#53e3a6;" href="/createTerm"><i class="fa fa-calendar" aria-hidden="true"></i>  Kreiraj termin</a></li>
	</ul>
	
	<br />
	<div>
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
	
	</div>
	<input type="text" class="form-control" id="myInput2"
		placeholder="Search for doctors..">
	<table class="table" id="doctorsTable">

		<tr style="background-color: #53e3a6;">
			<th colspan=8 style=""><h6
					style="color: white; letter-spacing: 4px;">MEDICINSKO OSOBLJE
				</h6></th>
		</tr>
		<tr>
			<td>Ime</td>
			<td>Prezime</td>
			<td>Biografija</td>


		</tr>
		<tr>
			<c:forEach var="user" items="${doctorsList}">
				<tr>
					<td><c:out value="${user.nameDto}" /></td>
					<td><c:out value="${user.surnameDto}" /></td>
					<td><c:out value="${user.biographyDto}" /></td>
					<td><input type="checkbox" value="${user.idDto}"></td>
				</tr>
			</c:forEach>
		</tr>

	</table>

	<input type="button" class="btn btn-outline-danger" id="createOperation" value="Zakazi operaciju" />


	<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="/js/operations.js"></script>




</body>
</html>