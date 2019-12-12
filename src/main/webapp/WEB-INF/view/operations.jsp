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
<title>Doctors</title>


</head>
<body>

	<div>
		<%@ include file="CAHomeTemplate.jsp"%>
	</div>
	<br />
	<input type="text" class="form-control" id="myInput"
		placeholder="Search for terms..">
	<table class="table" id="appointmentTable">

		<tr style="background-color: #53e3a6;">
			<th colspan=8 style=""><h6
					style="color: white; letter-spacing: 4px;">TERMINI</h6></th>
		</tr>
		<tr>
			<td>Datum</td>
			<td>Vreme</td>
			<td>Soba</td>
			<td>Tip</td>
		</tr>
		<tr>
			<c:forEach var="term" items="${appointmentsList}">
				<tr>
					<td><c:out value="${term.dateDto}" /></td>
					<td><c:out value="${term.timeDto}" /></td>
					<td><c:out value="${term.roomDto}" /></td>
					<td><c:out value="${term.typeDto}" /></td>
				</tr>
			</c:forEach>
		</tr>

	</table>

	<br />
	<br />

	<input type="text" class="form-control" id="myInput2"
		placeholder="Search for doctors..">
	<table class="table" id="doctorsTable">

		<tr style="background-color: #53e3a6;">
			<th colspan=8 style=""><h6
					style="color: white; letter-spacing: 4px;">MEDICINSKO OSOBLJE
				</h6></th>
		</tr>
		<tr>
			<td>Ime</td>
			<td>Prezime</td>
			<td>Biografija</td>


		</tr>
		<tr>
			<c:forEach var="user" items="${doctorsList}">
				<tr>
					<td><c:out value="${user.nameDto}" /></td>
					<td><c:out value="${user.surnameDto}" /></td>
					<td><c:out value="${user.biographyDto}" /></td>
					<td><input type="checkbox" value="${user.nameDto}"></td>
				</tr>
			</c:forEach>
		</tr>

	</table>

	<input type="button" id="createOperation" value="Zakazi operaciju" />


	<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="/js/operations.js"></script>




</body>
</html>