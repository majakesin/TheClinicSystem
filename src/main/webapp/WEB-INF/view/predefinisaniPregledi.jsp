<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
.grid-container {
  display: grid;
  grid-template-row: 20px 20px 20px 20x;
  grid-gap: 10px;
  
  padding: 10px;
}

.grid-container > div {
 
 
  text-align: center;
  font-size: 30px;
}
.row {
	padding-bottom:10px;
}
</style>
<meta charset="UTF-8">
<title></title>
</head>
<body>


	<div >
		<%@ include file="patientProfileTemplate.jsp"%>
	</div>
	
	<br><br>
	
	<div class="row">
	
	<c:forEach var="termin" items="${terminiDto}">
	<div class="col-sm-4">
  	<div class="card border-danger mx-sm-1 p-3">
  	<div class="text-danger text-center mt-2"><i class="fa fa-calendar-plus-o" aria-hidden="true"></i></div>
  	<div class="text-danger text-center mt-1"><h5>TERMIN</h5></div>
  	<div class="text-danger text-center mt-1"><h6>Datum: ${termin.dateDto }</h6></div>
  	<div class="text-danger text-center mt-1"><h6>Vreme: ${termin.timeDto }</h6></div>
  	<div class="text-danger text-center mt-1"><h6>Soba: ${termin.roomDto }</h6></div>
  	<div class="text-danger text-center mt-1"><h6>Tip pregleda: ${termin.typeDto }</h6></div>
  	<div><a class="btn btn-outline-danger" href="/zakazan/${termin.idDto}">Zakaži</a> </div>
  	</div>
  	</div>
	</c:forEach>
	</div>
</body>
</html>