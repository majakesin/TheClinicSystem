<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>

	<br><br><br><br><br><br><br><br>
<div class="d-flex justify-content-lg-center" id="con">


	<div>
	
  	<div class="text-danger text-center mt-2"><h3>TERMIN</h3></div>
  	
  	<div class="card border-danger mx-sm-1 p-3">
  	<div class="text-danger text-center mt-1"><h6>Tip pregleda   : ${doktor.tipPregledaDto}</h6></div>
  	
  	<form:form method="POST" action="/zakazanNP1/${doktor.idDto}"
		modelAttribute="docVreme">	
  	
  		<div class="text-danger text-center mt-1"><h6>Odaberi vreme  : </h6>
  
		
		<form:select id="vreme" class="form-control" path="vremePregledaDto">
						<option value="null"></option>
						<option value="08:00">08:00</option>
						<option value="08:30">08:30</option>
						<option value="09:00">09:00</option>
						<option value="09:30">09:30</option>
						<option value="10:30">10:30</option>
						<option value="11:00">11:00</option>
						<option value="11:30">11:30</option>
						<option value="12:00">12:00</option>
						<option value="12:30">12:30</option>
						<option value="13:00">13:00</option>
						<option value="13:30">13:30</option>
						<option value="14:00">14:00</option>
						<option value="14:30">14:30</option>
						<option value="15:00">15:00</option>
						<option value="15:30">15:30</option>
					</form:select>
				</div>
				
				<div class="text-danger text-center mt-1"><h6>Potvrdi datum pregleda   :</h6></div>
				<div class="text-danger text-center mt-1"><form:input type="date" class="form-control" path="datumPregledaDto"/></div>
			<div class="text-danger text-center mt-1"> <input type="submit" class="btn btn-outline-danger" value="Zakazi"></div>
			</form:form>
		</div>
	</div>	
</body>
</html>