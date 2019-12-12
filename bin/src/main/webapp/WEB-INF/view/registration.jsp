<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta charset="UTF-8">
<title>Registration</title>

<style type="text/css">



@prim: #53e3a6;

*{
	box-sizing: border-box;
	margin: 0;
	padding: 0;
	
	font-weight: 300;
}

body{
	
	color: white;
	font-weight: 300;
	
	::-webkit-input-placeholder { /* WebKit browsers */
		
			color:    white;
		font-weight: 300;
	}
	:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
		
		 color:    white;
		 opacity:  1;
		font-weight: 300;
	}
	::-moz-placeholder { /* Mozilla Firefox 19+ */
		
		 color:    white;
		 opacity:  1;
		font-weight: 300;
	}
	:-ms-input-placeholder { /* Internet Explorer 10+ */
		
		 color:    white;
		font-weight: 300;
	}
}

.wrapper{
	background: #50a3a2;
background: -webkit-linear-gradient(top left, #50a3a2 0%, #53e3a6 100%);
background: -moz-linear-gradient(top left, #50a3a2 0%, #53e3a6 100%);
background: -o-linear-gradient(top left, #50a3a2 0%, #53e3a6 100%);
background: linear-gradient(to bottom right, #50a3a2 0%, #53e3a6 100%);
	
	position: absolute;
	top: 0%;
	
	width: 100%;
	height: 100%;
	margin-top: 0px;
	overflow: hidden;
	
	&.form-success{
		.container{
			h1{
				transform: translateY(85px);
			}
		}
	}
}

.container{
	max-width: 400px;
	margin: 0 auto;
	padding: 0px 0;
	height: 100%;
	text-align: center;
	
	h1{
		font-size: 40px;
		font-color: white;
		font-weight: 200;
	}
}


	
	
	input{
		display: block;
		appearance: none;
		outline: 0;
		border: 1px solid fade(white, 40%);
		background-color: fade(white, 20%);
		width: 150px;
		
		border-radius: 3px;
		padding: 10px 15px;
		margin: 0 auto 10px auto;
		display: block;
		text-align: center;
		font-size: 18px;
		
		color: white;
		
		transition-duration: 0.25s;
		font-weight: 300;
		
		&:hover{
			background-color: fade(white, 40%);
		}
		
		&:focus{
			background-color: white;
			width: 300px;
			
			color: @prim;
		}
	}
	
	#registracija{
		font-color :#53e3a6
		appearance: none;
		outline: 0;
		background-color: white;
		border: 0;
		padding: 10px 15px;
		color: @prim;
		border-radius: 3px;
		width: 150px;
		cursor: pointer;
		font-size: 18px;
		transition-duration: 0.25s;
		
		&:hover{
			background-color: rgb(245, 247, 249);
		}
	}



@-webkit-keyframes square {
  0%   { transform: translateY(0); }
  100% { transform: translateY(-700px) rotate(600deg); }
}
@keyframes square {
  0%   { transform: translateY(0); }
  100% { transform: translateY(-700px) rotate(600deg); }
}


</style>
</head>
<body>

	<div class="wrapper">
	<div class="container">
	<h1>Registracija</h1>
	
	<form:form method="POST" action="/patient/create" modelAttribute="userDto">
				
				
		
				<form:input class="form-control" placeholder=" * Korisničko ime" path="usernameDto" required="required" />
			
				<form:input class="form-control" type="password" placeholder=" * Lozinka"  path="passwordDto"  required="required"/>
		
				<form:input class="form-control" placeholder=" Ime " path="nameDto" />
			
			
				<form:input  class="form-control"  placeholder= " Prezime" path="surnameDto" />
			
				<form:input class="form-control" placeholder= " Adresa" path="addressDto" />
			

				<form:input class="form-control" placeholder=" Grad" path="cityDto" />
			
				
				<form:input class="form-control" placeholder = " Država" path="countryDto" />
			
			
				
				<form:input class="form-control" path="insuranceNumberDto" placeholder =" * Broj osiguranika " required="required" />
			
				
				<form:input  class="form-control" placeholder=" Telefon" path="phoneDto" />
			
				<form:input class="form-control"  type="email" placeholder= " * Email" path="emailDto" required="required"/>
			
				<br>
				 <input  type="submit" id="Registracija" value="Registruj se" />
				
				
	</form:form>
	</div>
	</div>
</body>
</html>