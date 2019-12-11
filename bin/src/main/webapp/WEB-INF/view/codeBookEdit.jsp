<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
<title>Administrator codebook</title>
<style>

body {
 background-image: url("pokusaj.jpg");
  background-repeat:  no-repeat;
 }

</style>
</head>
<body>

	<div >
		<%@ include file="CCAHomeTemplate.jsp"%>
	</div>
	
	<div class="container">
	<div class="row">
	 <div class="col-sm">
		<br> <br> <br>
	<form:form method="POST" action="/administrators/codebook/edit/create"
		modelAttribute="codeBookDto">
		<table>
			<tr>
				<td><form:hidden path="idDto"/>
			</tr>
			<tr>
				<td><form:label path="diagnosisCodeDto">Sifra dijagnoze:</form:label></td>
				<td><form:input class="form-control" placeholder="Diagnose code" path="diagnosisCodeDto" /></td>
			</tr>
			<tr>
				<td><form:label path="diagnosisNameDto">Naziv dijagnoze:</form:label></td>
				<td><form:input  class="form-control" placeholder="Diagnose name" path="diagnosisNameDto" /></td>
			</tr>
			<tr>
				<td><form:label path="diagnosisDescriptionDto">Opis dijagnoze:</form:label></td>
				<td><form:input  class="form-control" placeholder="Diagnose description" path="diagnosisDescriptionDto" /></td>
			</tr>
			<tr><td></td></tr>
			<tr><td></td></tr>
			<tr>
				<td><form:label path="drugCodeDto">Sifra leka:</form:label></td>
				<td><form:input class="form-control" placeholder="Drug code" path="drugCodeDto" /></td>
			</tr>
			<tr>
				<td><form:label path="drugNameDto">Naziv leka:</form:label></td>
				<td><form:input class="form-control" placeholder="Drug name" path="drugNameDto" /></td>
			</tr>
			
			<tr>
				<td><form:label path="drugDescriptionDto">Opis leka:</form:label></td>
				<td><form:input class="form-control" placeholder="Drug description" path="drugDescriptionDto" /></td>
			</tr>
			<br/>
			<br/>
			<tr>
				<td><form:label path="medicCodeDto">Kod lekara:</form:label></td>
				<td><form:input class="form-control" placeholder="Medic code" path="medicCodeDto" /></td>
			</tr>
			

			<tr>
				<td></td>
				<td></td>
				<td><input type="submit" class="btn btn-outline-warning" value="Izmeni" /></td>
			</tr>
		</table>
	</form:form>
	
	</div>
	 <div class="col-sm">
	 <br> <br> <br>
	
	</div>
	</div>
	</div>
