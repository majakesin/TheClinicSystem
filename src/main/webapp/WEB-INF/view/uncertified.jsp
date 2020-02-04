<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">





<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
<title>Anamnesis</title>


</head>
<body>

	<div >
		<%@ include file="nurseHome.jsp"%>
	</div>
	
	
<div class="container">
	<div class="row">
	 <div class="col-sm">
		<br> <br> <br>
	
	
	</div>
	 <div class="col-sm">
	 <br> <br> <br>
	<table class="table">
		
		<tr style="background-color: #53e3a6;">
			<th colspan=8 style="text-align=center;"><h6 style="color:white; letter-spacing: 4px; text-align=center;"> ISTORIJA BOLESTI </h6> </th>
		</tr>
		<tr>
			<td>Kod leka</td>
			<td>Lek</td>
			<td>Opis</td>
			
			
			
		</tr>
		<tr>
			<c:forEach var="certified" items="${allUncertified}">
				<tr>
			  		<td><c:out value="${certified.drugCode}" /></td> 
			 		<td><c:out value="${certified.drugName}" /></td> 
					<td><c:out value="${certified.describe}" /></td>
					
					<td><a class="btn btn-outline-success" href="/nurse/prescriptions/certified/${certified.id}">Overi</a></td>
				</tr>
			</c:forEach>
		</tr>

	</table>
	</div>
	</div>
	</div>
	
	
	
	
</body>
</html>