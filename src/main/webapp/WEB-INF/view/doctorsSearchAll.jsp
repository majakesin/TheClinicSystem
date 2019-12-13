<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Doctors Search All </title>
</head>
<body>

	<div>
		<%@ include file="CAHomeTemplate.jsp" %>
	</div>
	<ul  class="nav nav-tabs justify-content-center nav-fill"  >
		<li class="nav-item "><a  class="nav-link " style="color:#53e3a6;" href="/doctors" ><i class="fa fa-user-md" aria-hidden="true"></i> Doktori</a></li>
		<li class="nav-item "><a class="nav-link " style="color:#53e3a6;" href="/nurse"><i class="fa fa-user-md" aria-hidden="true"></i> Sestre</a></li>
		<li class="nav-item "><a class="nav-link " style="color:#53e3a6;" href="/doctorsSearch"><i class="fa fa-search" aria-hidden="true"></i>Pretraga doktora</a></li>	
	</ul>
	<br> <br> <br>
	<div class="container">
		<%@ include file="doctorsSearch.jsp" %>
	</div>

</body>
</html>