<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >    
<meta charset="UTF-8">
<title>Nurse</title>
<style type="text/css">
.error {
	color: red;
}
</style>

</head>
<body>


	<div >
		<%@ include file="CAHomeTemplate.jsp"%>
	</div>
	
	<ul  class="nav nav-tabs justify-content-center nav-fill"  >
		<li class="nav-item "><a  class="nav-link " style="color:#53e3a6;" href="/doctors" ><i class="fa fa-user-md" aria-hidden="true"></i> Doktori</a></li>
		<li class="nav-item "><a class="nav-link " style="color:#53e3a6;" href="/nurse"><i class="fa fa-user-md" aria-hidden="true"></i> Sestre</a></li>
		<li class="nav-item "><a class="nav-link " style="color:#53e3a6;" href="/doctorsSearch"><i class="fa fa-search" aria-hidden="true"></i>Pretraga doktora</a></li>	
	</ul>
	
	<div class="container">
	<div class="row">
	 <div class="col-sm">
		<br> <br> <br>
		
	<form:form method="POST" action="/nurse/create"
		modelAttribute="userDto">
		<table>
			<tr>
				<td><form:label path="nameDto">Ime:</form:label></td>
				<td><form:input class="form-control" placeholder="Name" path="nameDto" />
				<form:errors path="nameDto" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="surnameDto">Prezime:</form:label></td>
				<td><form:input class="form-control" placeholder="Surname" path="surnameDto" />
				<form:errors path="surnameDto" cssClass="error" /></td>

			<tr>
				<td><form:label path="usernameDto">Korisničko ime:</form:label></td>
				<td><form:input class="form-control" path="usernameDto" />
				<form:errors path="usernameDto" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="passwordDto">Šifra:</form:label></td>
				<td><form:input class="form-control" path="passwordDto" />
				<form:errors path="passwordDto" cssClass="error" /></td>
			</tr>
			
			<tr>
				<td><form:label path="emailDto">Email:</form:label></td>
				<td><form:input class="form-control" path="emailDto" />
				<form:errors path="emailDto" cssClass="error" /></td>
			</tr>
			
			<tr>
					<!--    <td><form:label path="clinicDto"> Izaberi kliniku:</form:label></td>  -->
							<td colspan="2"><form:select class="form-control"
									path="clinicDto">
									<form:option value="null">Izaberi kliniku</form:option>
									<c:forEach items="${allClinics}" var="clinic">
										<form:option value="${clinic.idDto}">${clinic.nameDto}</form:option>
									</c:forEach>
								</form:select></td>
						</tr>
			
			<tr>
				<td></td>
				<td></td>
				<td><input type="submit" class="btn btn-outline-danger" value="Kreiraj" /></td>
			</tr>
		</table>
	</form:form>
	</div>

	 <div class="col-sm">
	 <br> <br> <br>
	<table class="table">
		
		<tr style="background-color: #53e3a6;">
			<th colspan=8 style="text-align=center;"><h6 style="color:white; letter-spacing: 4px; text-align=center;"> MEDICINSKO OSOBLJE </h6> </th>
		</tr>
		<tr>
			<td>Ime</td>
			<td>Adresa</td>
			<td>Mobilni telefon</td>
			
			
			<td>Izbrši</td>
			<td>Izmeni</td>
			<td>Više</td>
		</tr>
		<tr>
			<c:forEach var="user" items="${nurseDto}">
				<tr>
					<td><c:out value="${user.nameDto}" /></td>
					<td><c:out value="${user.surnameDto}" /></td>
					<td><c:out value="${user.phoneDto}" /></td>
					<td><a class="btn btn-outline-success" href="/nurse/delete/${user.idDto}">Delete</a></td>
					<td><a class="btn btn-outline-success" href="/nurse/edit/${user.idDto}">Edit</a></td>
					<td><a class="btn btn-outline-info" href="/nurse/details/${user.idDto}">Details</a></td>
				</tr>
			</c:forEach>
		</tr>

	</table>
	</div>
	</div>
	</div>
	
	
	
	

</body>
</html>