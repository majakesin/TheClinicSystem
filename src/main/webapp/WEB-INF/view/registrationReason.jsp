<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" ></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title></title>
</head>
<body>

<div >
		<%@ include file="CAHomeTemplate.jsp"%>
	</div>

<br><br><br><br><br><br><br><br>
<div class="d-flex justify-content-lg-center" id="con">
	<div class="user_card">
	<table class="table-lg">
	<form:form method="POST" class="form-control-lg" action="/requests/reason/send"
		modelAttribute="reasonDto">
	<tr>
	<td><label>Email:</label></td>
	
	<td>
	<div class="input-group mb-2">
     <div class="input-group-prepend">
    <div class="input-group-text"><i class="fa fa-envelope text-info"></i></div>
     </div>
	<form:input type="text" id="emailO" width="40" class="form-control"  path="emailDto" readonly="true" />
	</div>
	</td>
	
	<tr>
	
	<tr>
	<td><label>Subjekat:</label></td>
	
	<td>
	<div class="input-group mb-2">
	<div class="input-group-prepend">
    <div class="input-group-text"><i class="fa fa-user text-info"></i></div>
    </div>
	<form:input type="text" class="form-control" width="40" path="subjectDto" />
	</div>
	</td>
	
	</tr>
	
	<tr><td><label>Tekst:</label></td>
	<td>
	<div class="input-group mb-2">
	 <div class="input-group-prepend">
     <div class="input-group-text"><i class="fa fa-comment text-info"></i></div>
     </div>
	<form:textarea class="form-control" row="30" column="40" path="textDto" />
	</div>
	</td>
	</tr>
	
	<tr>
	<td  colspan="2">
	<div class="text-center">
	<input type="submit" id="zakaziPregled" value="Odgovori" class="btn btn-outline-danger">
	</div>
	</td>
	</tr>
	<tr>
	<td colspan="2">
   <div class="text-center">
	<a href="/requests">Vrati se</a>
	</div>
	</td>
	</tr>
	</form:form>
	</table>
	
	</div>
	</div>
	
</body>
</html>