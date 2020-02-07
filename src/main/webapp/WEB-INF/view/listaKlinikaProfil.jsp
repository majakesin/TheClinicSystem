<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>


<meta charset="UTF-8">
<title></title>
</head>
<body>
	<div>
		<%@ include file="patientHome.jsp" %>
	</div>
	<ul  class="nav nav-tabs justify-content-center nav-fill"  >
		<li class="nav-item "><a  class="nav-link " style="color:#53e3a6;" href="/clincsSearchDateType" > Zakaži pregled</a></li>
		<li class="nav-item "><a class="nav-link " style="color:#53e3a6;" href="/listaKlinikaProfil"> Profili klinika</a></li>
		
	</ul>
	<div class="container">
		<table class="table">
		
		<tr style="background-color: #53e3a6;">
			<th colspan=8 style="text-align=center;"><h6 style="color:white; letter-spacing: 4px; text-align=center;"> KLINIKE </h6> </th>
		</tr>
		<tr>
			<td>Naziv</td>
			<td>Adresa</td>
			<td>Ocena</td>
			<td>Telefon</td>
			<td></td>
			
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
									href="/profilKlinikePacijent/${klinika.idDto}">Profil</a></td>
					
					
					
					
				
				</tr>
			</c:forEach>
		</tr>

	</table>
	
	
	</div>
</body>
</html>