<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">

              input.hidden {
    position: absolute;
    left: -9999px;
}
</style>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<br><br><br><br><br>
	
	<div class="container">
	<div class="row">
	
        
        
       <div class="col-md-10 ">


        
           
    
    
            <div class="col-sm-20"><div align="center">
              <h4 style="color:#53e3a6;">${clinicDto.nameDto} </h4>
              
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
	<td><h6>Lista doktora:</h6> </td>
	<td><a class="btn btn-outline-info" href="/listaDoktoraKlinike/${clinicDto.idDto}">Lista doktora</a></td>
	</tr>
	 
	<td colspan="2"><a  href="/listaKlinikaProfil">Vrati se</a></td>
	</tr>
	<tr>
	</tr>
	<tr>
	</table>    
</body>
</html>