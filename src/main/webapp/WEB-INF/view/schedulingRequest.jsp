<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">




<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >

<style>

body
{
    background-image:url('https://i.pinimg.com/236x/f1/4e/9c/f14e9cc84854358f08917ad8a5568765--white-wallpaper-iphone-white-background-wallpaper.jpg');;
     background-position: cover;
 	 background-repeat: no-repeat;
  	background-size: cover;
  	background-height:100%
}






</style>
</head>
<body>

<div >
		<%@ include file="CAHomeTemplate.jsp"%>
	</div>
	
	 <ul  class="nav nav-tabs justify-content-center nav-fill"  >
		<li class="nav-item "><a  class="nav-link " style="color:#53e3a6;" href="/appointmentRequests" ><i class="fa fa-calendar" aria-hidden="true"></i>Zahtevi </a></li>
		<li class="nav-item "><a class="nav-link " style="color:#53e3a6;" href="/operations"><i class="fa fa-scissors"></i> Operacije</a></li>
		<li class="nav-item "><a class="nav-link " style="color:#53e3a6;" href="/createTerm"><i class="fa fa-calendar" aria-hidden="true"></i>  Kreiraj termin</a></li>
	</ul>	
		

<div class="container">
	<br> <br> <br> <br>
    <div class="row">
        <div class="col-md-12 col-md-offset-8">
        
        
            <h3 class="text-center">
                        Zahtevi za pregled </h3>
        
            
 
 
 <table class="table table-striped table-condensed">
                  <thead>
                  <tr>
                      <th>Datum </th>
                      <th>Vreme </th>
                      <th>Sala </th>
                      
                      <th>Tip pregleda </th>
                      <th>Cena </th>
                      <th>Popust </th>
                      <th> </th>
                      <th> </th>
                     
                  </tr>
              </thead>   
              <tbody>
                <tr>
                    
                  <c:forEach var="request" items="${appointmentDto}">
				<tr>
					<td><c:out value="${request.dateDto}" /></td>
					<td><c:out value="${request.timeDto}" /></td>
					<td><c:out value="${request.roomDto}" /></td>
					<td><c:out value="${request.typeDto}" /></td>
					<td><c:out value="${request.priceDto}" /></td>
					<td><c:out value="${request.discountDto}" /></td>
					
					<td><a  class="btn btn-outline-success" href="clinic/admin/operations/${request.idDto}">Rezervisi salu</a></td>
				</tr>
			</c:forEach>  
                    
                                                        
                </tr>
           
        		  
              </tbody>
              </table>
 
    </div>
	
</div>
              
          
</body>
</html>