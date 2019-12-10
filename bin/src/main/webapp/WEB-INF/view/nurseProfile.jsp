<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<title>Registration</title>
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
		<%@ include file="nurseHome.jsp"%>
	</div>

	<div class="container">
	<div class="row">
	
        
        
       <div class="col-md-10 ">

<div class="panel panel-default">
  <div class="panel-heading" style="background-colo:#53e3a6">  <h4 ></h4></div>
   <div class="panel-body">
       
    <div class="box box-info">
        
            <div class="box-body">
                     <div class="col-sm-30">
                     <div  align="center"> <img alt="User Pic" src="https://icon-library.net/images/no-profile-picture-icon-female/no-profile-picture-icon-female-0.jpg" id="profile-image1" class="img-circle img-responsive"> 
                
                <input id="profile-image-upload" class="hidden" type="file">
<div style="color:#999;" >click here to change profile image</div>
                <!--Upload Image Js And Css-->
                     
                     </div>
              
              <br>
    
              <!-- /input-group -->
            </div>
    
    
            <div class="col-sm-20"><div align="center">
            <h4 style="color:#53e3a6;">${userDto.nameDto} ${userDto.surnameDto}</h4> <a class="btn btn-outline-info" href="/nurseProfile/edit/${userDto.idDto}">Uredi</a> 
              
              </div>            
            </div>
            <div class="clearfix"></div>
            <hr style="margin:10px 0 0px 0;">
    

	<table class="table table-striped">
	<tr>
	<td><h6>Ime: </h6></td>
	<td>${userDto.nameDto} </td>
	</tr>
	<tr>
	<td><h6>Prezime:</h6> </td>
	<td>${userDto.surnameDto} </td>
	</tr>
	<tr>
	<td><h6>Grad:</h6> </td>
	<td>${userDto.cityDto} </td>
	</tr>
	<tr>
	<td><h6>Telefon:</h6> </td>
	<td>${userDto.phoneDto} </td>
	</tr>
	<tr>
	<td><h6>Email:</h6> </td>
	<td>${userDto.emailDto} </td>
	</tr>
	<tr>
	<td><h6>Biografija:</h6> </td>
	<td>${userDto.biographyDto} </td>
	</tr>
	<tr>
	</tr>
	<tr>
	</table>    
	<br> <br> <br>
	 
</div>  
</div>
    <script>
              $(function() {
    $('#profile-image1').on('click', function() {
        $('#profile-image-upload').click();
    });
});       
              </script> 
       
       
       
       
       
       
       
       
       
   </div>

	
</body>
</html>