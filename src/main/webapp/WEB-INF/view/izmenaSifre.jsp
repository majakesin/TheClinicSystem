<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Izmeni sifru</title>
</head>
<body>

	<br><br><br><br><br><br><br><br><br><br>
	<div class="d-flex justify-content-center" id="con">
	<table>
	<form:form method="POST" action="/izmeniSifruAkcija"
		modelAttribute="UserDto">
		<tr>
		<td><label>Nova šifra:</label></td>
		<td>
		<div class="input-group mb-2">
	 		<div class="input-group-prepend">
     		<div class="input-group-text"><i class="fa fa-key" aria-hidden="true"></i></div>
    	 	</div>
		<form:input type="password" class="form-control" path="passwordDto" /></div> </td>
		
		</tr>
		<tr>
		<td><label>Potvrdi šifru:</label></td>
		<td>
		<div class="input-group mb-2">
	 		<div class="input-group-prepend">
     		<div class="input-group-text"><i class="fa fa-key" aria-hidden="true"></i></div>
    	 	</div>
		<form:input type="password" class="form-control" path="pomocnaSifraDto" /></div> </td>
		
		</tr>
		<tr>
		<td colspan="2"><input type="submit" value="Izmeni" class="btn btn-outline-danger"></td>
		</tr>
		
		</form:form>
	</table>
	</div>

</body>
</html>