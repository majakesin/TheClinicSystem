<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Clinics</title>
</head>
<body>

    <div>
		<%@ include file="patientHome.jsp" %>
	</div>
		<ul  class="nav nav-tabs justify-content-center nav-fill"  >
		<li class="nav-item "><a  class="nav-link " style="color:#53e3a6;" href="/clincsSearchDateType" > Zakaži pregled</a></li>
		<li class="nav-item "><a class="nav-link " style="color:#53e3a6;" href="/listaKlinikaProfil"> Profili klinika</a></li>
		
	</ul>
<br> <br>

<div>
	<div class="row">
	
	<div class="col-md">
	<form:form method="POST" action="/terms/search"
		modelAttribute="termDto">
	<table class="table" style="width:600">
	<tr style="background-color: #53e3a6;">
	<td> <form:input type="date" id="dateDto" class="form-control" placeholder="Datum pregleda" path="dateDto" /> </td>
	
	<td>
					<form:select class="form-control" path="typeDto">
						<form:option value="None"></form:option>
						<form:option value="Ginekologija"></form:option>
						<form:option value="Urologija"></form:option>
						<form:option value="Stomatologija"></form:option>
						<form:option value="Hirurgija"></form:option>
						<form:option value="Kardiologija"></form:option>
					</form:select>
				</td>
	
	<td> <input type="submit" class="btn btn-outline-success"> </td>
	
	<td><a class="btn btn-outline-success" href="/clinicsSearch">Dodaj kriterijum pretrage</a>  </td>
	
	</table>
	</form:form>
	</div>
	<div class="alert alert-danger" role="alert">
		<h4 class="alert-heading">
		Ako označite vikend kao datum vaš zahtev biće odbijen.
		</h4>
 		Vikend je neradan!
	</div>
	</div>
	
	<div class="row">
	<div class="col-md">
	<table class="table table-striped">
	
	<tr style="background-color: #53e3a6;">
	<td><h6 style="color:white;  letter-spacing: 4px; text-align=center;">Naziv klinike </h6></td>
	<td><h6 style="color:white; letter-spacing: 4px; text-align=center;">Adresa klinike </h6> </td>
	<td><h6 style="color:white; letter-spacing: 4px; text-align=center;">Prosečna ocena</h6> </td>
	<td><h6 style="color:white; letter-spacing: 4px; text-align=center;">Zakaži</h6> </td>
	</tr>
	<c:forEach var="var" items="${termsDto}">
	<tr>
	<td><c:out value="${var.nameDto}"/> </td>
	<td><c:out value="${var.adressDto}"/> </td>
	<td><c:out value="${var.markDto}"/> </td>
	<td><a class="btn btn-outline-info" href="/zakaziNepredefinisani1/${var.idDto}">Zakaži</a></td>
	</tr>
	</c:forEach>
	</table>
	</div>
	</div>
	
	
</div>


</body>
</html>