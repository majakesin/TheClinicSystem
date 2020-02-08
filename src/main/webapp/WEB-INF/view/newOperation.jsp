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
<title>Create Term</title>


</head>
<body>

	<div>
		<%@ include file="DoctorHomePage.jsp"%>
	</div>


	<div class="container">
		<div class="row">
			<div class="col-sm">
				<br> <br> <br>
				<form:form method="POST"
					action="/doctors/records/pacient/examination/free/room"
					modelAttribute="roomDto">
					<table>


						<tr>
						<td>Izaberi sobu</td>
							<td><form:select class="form-control" path="idDto">
									<c:forEach items="${allRooms}" var="room">
										<form:option value="${room.idDto}">${room.nameDto} br:${room.hallNumberDto}</form:option>
									</c:forEach>
								</form:select></td>
						</tr>

						<tr>
							<td></td>
							<td></td>
							<td><input type="submit" class="btn btn-outline-danger"
								value="Oslobodi" /></td>
						</tr>
					</table>
					
				</form:form>
			</div>
		</div>
	</div>
	<br>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-sm">
				<br> <br> <br>
				<form:form method="POST"
					action="/doctors/records/pacient/examination/new/create"
					modelAttribute="appointmentDto">
					<table>
						<tr>
							<td><form:label path="dateDto">Datum:</form:label></td>
							<td><form:input type="date" class="form-control"
									placeholder="Datum" path="dateDto" /></td>
						</tr>
						<tr>
							<td><form:label path="timeDto">Vreme:</form:label></td>
							<td><form:input type="time" class="form-control"
									placeholder="Vreme" path="timeDto" /></td>
						<tr>
							<td><form:label path="dateDto">Tip pregleda:</form:label></td>
							<td><form:select class="form-control" path="typeDto">
									<form:option value="Ginekologija"></form:option>
									<form:option value="Urologija"></form:option>
									<form:option value="Stomatologija"></form:option>
									<form:option value="Hirurgija"></form:option>
									<form:option value="Kardiologija"></form:option>
								</form:select></td>
						</tr>

						<tr>
							<td>Tip odrzavanja pregleda:</td>
							<td><form:select class="form-control"
									path="operationTypeDto">
									<form:option value="Operacija"></form:option>
									<form:option value="Pregled"></form:option>
								</form:select></td>
						</tr>

						<tr>
							<td><form:label path="priceDto">Cena:</form:label></td>
							<td><form:input class="form-control" placeholder="Cena"
									path="priceDto" /></td>


						</tr>
						<tr>
							<td><form:label path="discountDto">Popust:</form:label></td>
							<td><form:input class="form-control" placeholder="Popust"
									path="discountDto" /></td>


						</tr>

						<br />
						<br />

						<tr>
							<td></td>
							<td></td>
							<td><input type="submit" class="btn btn-outline-danger"
								value="Kreiraj" /></td>
						</tr>
					</table>
					<h2>
						<a href="/patientSearch/doctor" class="btn btn-outline-danger">NASTAVI
							DALJE BEZ ZAKAZIVANJA</a>
					</h2>
				</form:form>
			</div>
		</div>
	</div>