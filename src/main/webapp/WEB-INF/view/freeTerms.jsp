<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">




<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
<title>Free Terms</title>


</head>
<body>

	<div >
		<%@ include file="patientProfileTemplate.jsp"%>
	</div>
	
	
<div class="container">
	<div class="row">
	 <div class="col-sm">
		<br> <br> <br>
	<form:form modelAttribute="appointmentDto">
		
		<table class="table">
		
		<tr style="background-color: #53e3a6;">
			<th colspan=8 style="text-align=center;"><h6 style="color:white; letter-spacing: 4px; text-align=center;"> SLOBODNI TERMINI PREGLEDA </h6> </th>
		</tr>
		<tr>
			<td>Datum</td>
			<td>Vreme</td>
			<td>Sala</td>
			<td>Tip pregleda</td>
			<td>Cena</td>
			<td>Popust</td>
			
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
					
					<td><a class="btn btn-outline-success" href="/appointmentRequests/${term.idDto}">Zakaži</a></td>
					
				
				</tr>
			</c:forEach>
		</tr>

	</table>
		
	</form:form>
	
	</div>
	
	</div>
	</div>
	
	
	
	
</body>
</html>