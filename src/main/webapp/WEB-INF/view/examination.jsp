<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<title>Examination</title>

<style type="text/css">
.error {
	color: red;
}
</style>

<body>

	<div>
		<%@ include file="nurseHome.jsp"%>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-sm-6">
				<br> <br> <br>
				<form:form method="POST" name="form1"
					action="/doctors/records/pacient/examination/create"
					modelAttribute="anamnesisDto">
					<table>
						<tr>
							<td><form:label path="report">Izvestaj</</form:label></td>
						</tr>
						<tr>
							<td><form:textarea path="report" class="form-control"
									placeholder="Izvestaj" /> <small id="emailHelp"
								class="form-text text-muted">Ovo polje sluzi za unosenje
									izvestaja o pregledu</small>
									
									
						</tr>
						<tr>
							<td><form:label path="diagnosisId">Dijagnoza</form:label></td>
						</tr>
						<tr>
							<td><form:select class="form-control" path="diagnosisId">
									<c:forEach items="${allCodebook}" var="code">
										<form:option value="${code.idDto}">${code.diagnosisNameDto} sifra:${code.diagnosisCodeDto}</form:option>
									</c:forEach>
								</form:select></td>
						</tr>
						
						<tr>
							<td><form:label path="diagnosisDate">Datum</form:label></td>
						</tr>

						<tr>
							<td><form:input type="date" class="form-control"
									placeholder="Datum" path="diagnosisDate" /></td>
						</tr>
						<tr>
							<td><a class="btn btn-outline-success"
								href="/doctors/records/pacient/examination/prescription/${prescription.pacientId}">Dodaj
									recept</a></td>
						</tr>
						<tr>

							<td>
								<table class="table table-hover">
									<tr>
										<th>Opis</th>
										<th>Naziv leka</th>
										<th>Kod leka</th>
									</tr>


									<c:forEach var="drug" items="${prescriptionsDto}">
										<tr>
											<td>${drug.describe}</td>
											<td>${drug.drugName}</td>
											<td>${drug.drugCode}</td>
											<td><a class="btn btn-outline-success"
									href="/doctors/records/pacient/examination/delete/prescription/${drug.id}">Izbrisi</a></td>
										</tr>
									</c:forEach>

								</table>
							</td>
						</tr>

						<tr>
							<td><input type="submit" class="btn btn-outline-danger"
								value="Zavrsi pregled" /></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
	</div>


</body>