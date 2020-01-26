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
	
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<title>Registration</title>

<style type="text/css">
.error {
	color: red;
}
</style>

</head>
<body>


	<div >
		<%@ include file="nurseHome.jsp"%>
	</div>


		  
   
   
   	<form:form method="POST" action="/doctors/records/save"
		modelAttribute="medicalRecordDto"> 

	<table class="table table-striped">
	

	<tr>
	<td><h6>Visina:</h6> </td>
	<td><form:input class="form-control"  path="heightDto" type="text"  /> 
		<form:errors path="heightDto" cssClass="error" />
	</td>
	</tr>
	<tr>
	<tr>
	
		
		
	</tr>
	
	
	<tr>
	<td><h6>Tezina:</h6> </td>
	<td><form:input class="form-control"  path="weightDto" type="text" />
		<form:errors path="weightDto" cssClass="error" />
	 </td>
	</tr>
	<tr>
		
	</tr>
	<tr>
	<td><h6>Krvna grupa:</h6> </td>
	<td><form:input class="form-control"  path="bloodType" type="text"  />
		<form:errors path="bloodType" cssClass="error" />
	 </td>
	</tr>
	<tr>
		
	</tr>
	<tr>
	
	<td><h6>Alergija:</h6> </td>
	<td><form:textarea class="form-control"  path="allergy" type="text"  />
	<form:errors path="allergy" cssClass="error" />
	</td>
	</tr>
	<tr>
	
	</tr>
	
	
	<tr>
	
	<td><input type="submit" class="btn btn-outline-danger" value="Potvrdi" /></td>
	</tr>
	</table>    
	
	</form:form>


	
</body>
</html>