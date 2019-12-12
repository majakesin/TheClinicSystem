<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<title>Medical records</title>
</head>
<body>

	<div>
		<%@ include file="CAHomeTemplate.jsp"%>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-sm mr-5">
				<select id="changePacient" class="browser-default custom-select">
					<option selected>Select pacient</option>
					<c:forEach items="${pacientsDto}" var="pacient">
						<option value="${pacient.idDto}">${pacient.nameDto}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>


	<div class="container">
		<div class="row">
			<div class="col-sm">
				<table id="pacientRecords">

					
					
				</table>

			</div>

		</div>

	</div>


	<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="/js/records.js"></script>
</body>

</html>