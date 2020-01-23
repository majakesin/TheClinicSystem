<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Room Search</title>
</head>
<body>

<div >
		<%@ include file="CAHomeTemplate.jsp"%>
		</div>
		

	<ul  class="nav nav-tabs justify-content-center nav-fill"  >
		<li class="nav-item "><a  class="nav-link " style="color:#53e3a6;" href="/rooms" > Kreiranje soba </a></li>
		<li class="nav-item "><a class="nav-link " style="color:#53e3a6;" href="/roomsSearch"><i class="fa fa-search" aria-hidden="true"></i>Pretraga soba</a></li>	
	</ul>
	<br> <br><br>
	
	<div class="container">
	<div class="row">
	<div class="col-md">
	<form:form method="POST" action="/room/search"
		modelAttribute="roomDto">
	<table class="table" style="width:600">
	<tr style="background-color: #53e3a6;">
	<td> <form:input type="text" id="nameDto" class="form-control" placeholder="Ime" path="nameDto" /> </td>
	<td> <form:input type ="text" id="hallNumberDtoid" class="form-control" placeholder="Broj" path="hallNumberDto"/> </td>

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
	<td><h6 style="color:white; letter-spacing: 4px; text-align=center;">Broj:</h6> </td>
	
	</tr>
	<c:forEach var="room" items="${roomsDto}">
	<tr>
	<td><c:out value="${room.nameDto}"/> </td>
	<td><c:out value="${room.hallNumberDto}"/> </td>
	
	</tr>
	</c:forEach>
	</table>
	</div>
	</div>
	

	</div>
</body>
</html>