<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>


</style>
<meta charset="UTF-8">
<title></title>
</head>
<body>

	<div>
		<%@ include file="DoctorHomePage.jsp"%>
	</div>
	<br> <br> <br> <br> <br>
	<div class="d-flex justify-content-center" id="con">
	<table>
	<form:form method="POST" action="/kreirajZahtevGodisnji"
		modelAttribute="VacationReqDto">
	<tr>
	<td><label>Ime:</label></td>
	<td><form:input type="text" class="form-control"  path="nameDto" readonly="true" /> </td>
	</tr>
	<tr>
	<td><label>Prezime:</label></td>
	<td><form:input type="text" class="form-control"  path="surnameDto" readonly="true" /> </td>
	</tr><tr>
	<td><label>Korisničko ime:</label></td>
	<td><form:input type="text" class="form-control"  path="usernameDto" readonly="true" /> </td>
	</tr>
	<tr>
	<tr>
	<td><label>Email:</label></td>
	<td><form:input type="text" class="form-control"  path="emailDto" readonly="true" /></td>
	
	</tr>
	<tr>
	<td><label>Početak godišnjeg odmora:</label></td>
	<td><form:input type="date" class="form-control"  path="pocetakGodisnjegDto"/></td>
	</tr>
	<tr>
	<td><label>Kraj godišnjeg odmora:</label></td>
	<td><form:input type="date" class="form-control"  path="krajGodisnjegDto"/></td>
	
	</tr>
	
	<tr></tr>
	<tr><td><input type="submit" id="zakaziPregled" value="Zakazi" class="btn btn-outline-danger"> </td> </tr>
	</form:form>
	</table>
	
	</div>
	

</body>
</html>