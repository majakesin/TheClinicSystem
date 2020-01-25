<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title></title>
</head>
<body>

	<div >
		<%@ include file="CAHomeTemplate.jsp"%>
	</div>
	
	
	
<div class="container">
	<br> <br> <br> <br>
    <div class="row">
        <div class="col-md-12 col-md-offset-8">
        
        
            <h3 class="text-center">
                        Zahtevi za godišnji odmor</h3>
        
            
 
 
 <table class="table table-striped table-condensed">
                  <thead>
                  <tr>
                      <th>Ime </th>
                      <th>Vreme </th>
                      <th>Korisničko ime </th>
                      
                      <th>Početak odmora </th>
                      <th>Kraj odmora </th>
                     
                      <th> </th>
                      <th> </th>
                     
                  </tr>
              </thead>   
              <tbody>
                <tr>
                    
                  <c:forEach var="request" items="${vqRegsDto}">
				<tr>
					<td><c:out value="${request.nameDto}" /></td>
					<td><c:out value="${request.surnameDto}" /></td>
					<td><c:out value="${request.usernameDto}" /></td>
					<td><c:out value="${request.roleDto}" /></td>
					<td><c:out value="${request.pocetakGodisnjegDto}" /></td>
					<td><c:out value="${request.krajGodisnjegDto}" /></td>
					
					
					<td><a  class="btn btn-outline-success" href="/VqReqRequests/accept/${request.idDto}">Prihvati</a></td>
					<td><a  class="btn btn-outline-success" href="/zahtevGodisnji/odbij/${request.idDto}">Odbij</a></td>
				</tr>
			</c:forEach>  
                    
                                                        
                </tr>
           
        		  
              </tbody>
              </table>
  
    </div>
	
</div>	
</body>
</html>