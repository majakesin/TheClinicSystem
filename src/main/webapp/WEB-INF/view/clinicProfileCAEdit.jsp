<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clinic Edit</title>
<style type="text/css">

              input.hidden {
    position: absolute;
    left: -9999px;
}

#profile-image1 {
    cursor: pointer;
  
     width: 100px;
    height: 100px;
      border-radius: 25px;
	border:2px solid #53e3a6 ;}
	.tital{ font-size:16px; font-weight:500;}
	 .bot-border{ border-bottom:1px #f8f8f8 solid;  margin:5px 0  5px 0}
</style>
</head>
<body>


	<div >
		<%@ include file="CAHomeTemplate.jsp"%>
	</div>
	<br> <br>
	<div class="container">
	<div class="row">
	
        
        
       <div class="col-md-10 ">


		
		    
            <div class="col-sm-20"><div align="center">
            <h4 style="color:#53e3a6;">${clinicDto.nameDto}</h4>
              </div>            
            </div>
            <div class="clearfix"></div>
            <hr style="margin:10px 0 0px 0;">
   
   
   	<form:form method="POST" action="/clinic/edit/ca"
		modelAttribute="clinicDto"> 
	<form:hidden class="form-control" path="idDto"  />
	<table class="table table-striped">
	
	<tr>
	<td><h6>Ime: </h6></td>
	<td><form:input class="form-control" path="nameDto" type="text"  /></td>
	</tr>
	<tr>
	<td><h6>Adresa:</h6> </td>
	<td><form:input class="form-control" path="adressDto" type="text"  /> </td>
	</tr>
	
	<tr>
	<td><h6>Telefon:</h6> </td>
	<td><form:input class="form-control"  path="phoneDto" type="text" /> </td>
	</tr>
	<tr>
	<td><h6>Opis:</h6> </td>
	<td><form:input class="form-control"  path="descriptionDto" type="text"  /> </td>
	</tr>
	<tr>
	
	<td><h6>Ocena:</h6> </td>
	<td><form:textarea class="form-control"  path="markDto" type="text"  /></td>
	</tr>
	<tr>
	
	
	<td><input type="submit" class="btn btn-outline-danger" value="Potvrdi" /></td>
	</tr>
	</table>    

	
	</form:form>
</div>
</html>