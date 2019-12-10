<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		<div >
		<%@ include file="CAHomeTemplate.jsp"%>
		</div>
		
		
		<div class="container">
	<div class="row">
	 <div class="col-sm">
		<br> <br> <br>
		
	<form:form method="POST" action="/room/create"
		modelAttribute="roomDto">
		<table>
			<tr>
				<td><form:label path="nameDto">Ime:</form:label></td>
				<td><form:input class="form-control" placeholder="" path="nameDto" /></td>
			</tr>
			<tr>
				<td><form:label path="hallNumberDto">Broj:</form:label></td>
				<td><form:input class="form-control" placeholder="" path="hallNumberDto" /></td>

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
			<th colspan=8 style="text-align=center;"><h6 style="color:white; letter-spacing: 4px; text-align=center;"> SOBE </h6> </th>
		</tr>
		<tr>
			<td>Broj</td>
			<td>Ime</td>
			
			<td>Izbrši</td>
			<td>Izmeni</td>
			<td>Više</td>
		</tr>
		<tr>
			<c:forEach var="soba" items="${roomsDto}">
				<tr>
					<td><c:out value="${soba.nameDto}" /></td>
					<td><c:out value="${soba.hallNumberDto}" /></td>
					
					<td><a class="btn btn-outline-success" href="/room/delete/${soba.idDto}">Delete</a></td>
					<td><a class="btn btn-outline-success" href="/room/edit">Edit</a></td>
					<td><a class="btn btn-outline-info" href="/room/details/${soba.idDto}">Details</a></td>
				</tr>
			</c:forEach>
		</tr>

	</table>
	</div>
	</div>
	</div>
	
	
</body>
</html>