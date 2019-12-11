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
	<form:form method="POST" action="codebook/create"
		modelAttribute="codeBookDto">
		<table>
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
				<td><input type="submit" class="btn btn-outline-danger" value="Kreiraj " /></td>
			</tr>
		</table>
	</form:form>
	
	</div>
	 <div class="col-sm">
	 <br> <br> <br>
	
	</div>
	</div>
	</div>
	
	<br/>
	<br/>
	<div>
	<table class="table">
		
		<tr style="background-color: #FF756B;">
			<th colspan=8 style="text-align=center;"><h6 style="color:white; letter-spacing: 4px; text-align=center;">SIFRARNIK</h6> </th>
		</tr>
		<tr>
			<td>Sifra dijagnoze</td>
			<td>Naziv dijagnoze</td>
			<td>Opis dijagnoze</td>
			<td>Sifra leka</td>
			<td>Naziv leka</td>
			<td>Opis leka</td>
			<td>Sifra lekara</td>
			<td>Uredi</td>
			<td>Više</td>
		</tr>
		<tr>
			<c:forEach var="codeBook" items="${codeBooksDto}">
				<tr>
					<td><c:out value="${codeBook.diagnosisCodeDto}" /></td>
					<td><c:out value="${codeBook.diagnosisNameDto}" /></td>
					<td><c:out value="${codeBook.diagnosisDescriptionDto}" /></td>
					<td><c:out value="${codeBook.drugCodeDto}" /></td>
					<td><c:out value="${codeBook.drugNameDto}" /></td>
					<td><c:out value="${codeBook.drugDescriptionDto}" /></td>
					<td><c:out value="${codeBook.medicCodeDto}" /></td>
					<td><a class="btn btn-outline-success" href="/administrators/codebook/delete/${codeBook.idDto}">Delete</a></td>
					<td><a class="btn btn-outline-success" href="/administrators/codebook/edit/${codeBook.idDto}">Edit</a></td>
					<td><a class="btn btn-outline-info" href="/administrators/codebook/details/${codeBook.idDto}">Details</a></td>
				</tr>
			</c:forEach>
		</tr>

	</table>
	</div>
	
	
</body>
</html>