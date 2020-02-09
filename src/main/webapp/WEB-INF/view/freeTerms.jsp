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
			<th colspan=8 style="text-align=center;"><h6 style="color:white; letter-spacing: 4px; text-align=center;"> KLINIKE </h6> </th>
		</tr>
		<tr>
			<td>Naziv</td>
			<td>Adresa</td>
			<td>Ocena</td>
			<td>Telefon</td>
			<td>Termini </td>
			
			<td> </td>
			
		</tr>
		<tr>
			<c:forEach var="klinika" items="${klinikeDto}">
				<tr>
			  		<td><c:out value="${klinika.nameDto}" /></td> 
			 		<td><c:out value="${klinika.adressDto}" /></td> 
					<td><c:out value="${klinika.markDto}" /></td>
					<td><c:out value="${klinika.phoneDto}" /></td>
					<td><a class="btn btn-outline-info"
									href="/clinic/terms/${klinika.idDto}">Izaberi termin</a></td>
					
					
					
					
				
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