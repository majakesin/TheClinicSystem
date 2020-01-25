<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
<title>Administrator registration</title>
</head>
<body>

	<div >
	 <%@ include file="CCAHomeTemplate.jsp" %>
	</div>
	
	<ul  class="nav nav-tabs justify-content-center nav-fill"  >
		<li class="nav-item "><a  class="nav-link " style="color:#53e3a6;" href="/cca" ><i class="fa fa-user-md" aria-hidden="true"></i> Administrator kliničkog centra</a></li>
		<li class="nav-item "><a class="nav-link " style="color:#53e3a6;" href="/ca"><i class="fa fa-user-md" aria-hidden="true"></i> Administrator klinike</a></li>
	
	</ul>
	
	
	<div class="container">
		<div class="row">
	 	<div class="col-sm">
			<br> <br> <br>
	
	<form:form method="POST" action="/administrators/create"
		modelAttribute="userDto">
		<table>
			<tr>
				<td><form:label path="usernameDto">Username:</form:label></td>
				<td><form:input class="form-control" placeholder="Username" path="usernameDto" /></td>
			</tr>
			<tr>
				<td><form:label path="passwordDto">Password:</form:label></td>
				<td><form:input type="password" class="form-control" placeholder="Password" path="passwordDto" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<form:select class="form-control" path="roleDto">
				<!-- <form:option value="Clinic Centar Administrator"></form:option> -->
						<form:option value="Clinic Administrator"></form:option>
					</form:select>
				</td>
			</tr>
			
			<tr>
					<!--    <td><form:label path="clinicDto"> Izaberi kliniku:</form:label></td>  -->
							<td colspan="2"><form:select class="form-control"
									path="clinicDto">
									
									<c:forEach items="${allClinics}" var="clinic">
										<form:option value="${clinic.idDto}">${clinic.nameDto}</form:option>
									</c:forEach>
								</form:select></td>
						</tr>
	
			
			
			<tr>
				<td></td>
				<td></td>
				<td><input type="submit" class="btn btn-outline-danger" value="register" /></td>
			</tr>
		</table>
	</form:form>
	
	</div>
	 <div class="col-sm">
	 <br> <br> <br>
	<table class="table">
		<tr style="background-color: #53e3a6;">
			<th colspan=4 style="text-align=center;"><h6 style="color:white; letter-spacing: 4px; text-align=center;"> ADMINISTRATORI </h6> </th>
		</tr>
		<tr>
			<td>Username</td>
			<td>Password</td>
			<td>Role</td>
			<td> </td>
			
		</tr>
		<tr>
			<c:forEach var="user" items="${usersDto}">
				<tr>
					<td><c:out value="${user.usernameDto}" /></td>
					<td><c:out value="${user.passwordDto}" /></td>
					<td><c:out value="${user.roleDto}" /></td>
					<td><a  class="btn btn-outline-success" href="administrators/user/delete/${user.idDto}">Delete</a></td>
				</tr>
			</c:forEach>
		</tr>

	</table>
	</div>
	</div>
	</div>

</body>
</html>