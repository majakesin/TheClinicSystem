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

	
</body>
</html>