<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>

body
{
    background-color: #1b1b1b;
    padding-top: 40px;
}
.form-signin {
    max-width: 480px;
    padding: 15px;
    margin:100px;
    margin-top:70px;
  }

.input-group-addon
{
    background-color: rgb(50, 118, 177);
    border-color: rgb(40, 94, 142);
    color: rgb(255, 255, 255);
}

.form-signup input[type="text"],.form-signup input[type="password"] { border: 1px solid rgb(50, 118, 177); }
.fullscreen_bg {
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    background-size: cover;
    background-position: 50% 50%;
    background-image: url('https://mares-med.com/profiles/recruiter/themes/cloudy/images/search-container-img.jpg');
    background-repeat:repeat;
  }

</style>
<div id="fullscreen_bg" class="fullscreen_bg"/>
 <form class="form-signin">
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
        <div class="panel panel-default">
        <div class="panel panel-primary">
        
            <h3 class="text-center">
                        Zahtevi za pregled </h3>
        
        <div class="panel-body">    
 
 
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
					
					<td><a  class="btn btn-outline-success" href="/appointmentRequests/accept/${request.idDto}">Prihvati</a></td>
					<td><a  class="btn btn-outline-success" href="/appointmentRequests/reject/${request.idDto}">Odbij</a></td>
				</tr>
			</c:forEach>  
                    
                                                        
                </tr>
           
        
              </tbody>
              
  </div>
       </div>
        </div>
    </div>
</div>
</form>
              
            </table>
