<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style type="text/css">

              input.hidden {
    position: absolute;
    left: -9999px;
}
</style>
</head>
<body>


	<div >
		<%@ include file="CAHomeTemplate.jsp"%>
	</div>
	<br>	<br>
	<div class="container">
	<div class="row">
	
        
        
       <div class="col-md-10 ">


        
           
    
    
            <div class="col-sm-20"><div align="center">
            <h4 style="color:#53e3a6;">${clinicDto.nameDto} </h4> <a class="btn btn-outline-info" href="/clinicProfileCA/edit/${clinicDto.idDto}">Uredi</a> 
              
              </div>            
            </div>
            <div class="clearfix"></div>
            <hr style="margin:10px 0 0px 0;">
    

	<table class="table table-striped">
	<tr>
	<td><h6>Ime: </h6></td>
	<td>${clinicDto.nameDto} </td>
	</tr>
	<tr>
	<td><h6>Adresa:</h6> </td>
	<td>${clinicDto.adressDto} </td>
	</tr>
	
	<tr>
	<td><h6>Telefon:</h6> </td>
	<td>${clinicDto.phoneDto} </td>
	</tr>
	<tr>
	<td><h6>Opis:</h6> </td>
	<td>${clinicDto.descriptionDto} </td>
	</tr>
	<tr>
	<td><h6>Ocena:</h6> </td>
	<td>${clinicDto.markDto} </td>
	</tr>
	<tr>
	</tr>
	<tr>
	</table>    
	<br> <br> <br>
	 
</div>  
</div>

  </body>

</html>