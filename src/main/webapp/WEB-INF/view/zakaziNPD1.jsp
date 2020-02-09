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
						<option value="08:00:AM">08:00:AM</option>
						<option value="08:30:AM">08:30:AM</option>
						<option value="09:00:AM">09:00:AM</option>
						<option value="09:30:AM">09:30:AM</option>
						<option value="10:30:AM">10:30:AM</option>
						<option value="11:00:AM">11:00:AM</option>
						<option value="11:30:AM">11:30:AM</option>
						<option value="12:00:PM">12:00:PM</option>
						<option value="12:30:PM">12:30:PM</option>
						<option value="01:00:PM">01:00:PM</option>
						<option value="01:30:PM">01:30:PM</option>
						<option value="02:00:PM">02:00:PM</option>
						<option value="02:30:PM">02:30:PM</option>
						<option value="03:00:PM">03:00:PM</option>
						<option value="03:30:PM">03:30:PM</option>
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