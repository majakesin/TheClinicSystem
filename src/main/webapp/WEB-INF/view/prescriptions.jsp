<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">





<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Create prescriptions</title>


</head>
<body>

	<div>
		<%@ include file="nurseHome.jsp"%>
	</div>

	<ul class="nav nav-tabs justify-content-center nav-fill">
		<li class="nav-item "><a class="nav-link "
			style="color: #53e3a6;" href="/calendar"><i
				class="fa fa-calendar" aria-hidden="true"></i>Calendar </a></li>
		<li class="nav-item "><a class="nav-link "
			style="color: #53e3a6;" href="/doctors/records/pacient/examination"><i
				class="fa fa-scissors"></i> Pregled</a></li>

	</ul>
	<div class="container">
		<div class="row">
			<div class="col-sm">
				<br> <br> <br>
				<form:form method="POST"
					action="/doctors/records/pacient/examination/prescription/create"
					modelAttribute="prescriptionDto">
					<table>

						<tr>
							<td><form:label path="describe">Opis</form:label></td>
						</tr>

						<tr>
							<td><form:textarea path="describe" class="form-control"
									placeholder="Opis" /> <small id="emailHelp"
								class="form-text text-muted">Ovo polje sluzi za unosenje
									detaljnog opisa recepta</small>
						</tr>
						<tr>
							<td><form:label path="describe">Kod leka</form:label></td>
						</tr>
						<tr>
							<td><form:select class="form-control" path="drugCode">
									<c:forEach items="${allCodebook}" var="codeBook">
										<form:option value="${codeBook.drugCodeDto}">${codeBook.drugCodeDto}</form:option>
									</c:forEach>
								</form:select></td>
						</tr>
						
						<tr>
							<td><form:label path="describe">Naziv leka</form:label></td>
						</tr>
						<tr>
							<td><form:select class="form-control" path="drugName">
									<c:forEach items="${allCodebook}" var="codeBook">
										<form:option value="${codeBook.drugNameDto}">${codeBook.drugNameDto}</form:option>
									</c:forEach>
								</form:select></td>
						</tr>
						
						
						<tr>
							<td></td>
							<td></td>
							<td><input type="submit" class="btn btn-outline-danger"
								value="Kreiraj recept" /></td>
						</tr>
					</table>
				</form:form>

			</div>
			<div class="col-sm">
				<br> <br> <br>
				<table class="table">

					<tr style="background-color: #53e3a6;">
						<th colspan=8 style=""><h6
								style="color: white; letter-spacing: 4px;">RECEPTI
							</h6></th>
					</tr>
					<tr>
						<td>Kod leka</td>
						<td>Naziv leka</td>
						<td>Pacient</td>
					</tr>
					<tr>
						<c:forEach var="prescription" items="${prescriptionsDto}">
							<tr>
								<td><c:out value="${prescription.drugCode}" /></td>
								<td><c:out value="${prescription.drugCode}" /></td>
								<td><c:out value="${prescription.pacientId}" /></td>

							</tr>
						</c:forEach>
					</tr>

				</table>
			</div>
		</div>
	</div>




</body>
</html>