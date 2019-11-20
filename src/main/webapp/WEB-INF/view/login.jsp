<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<!DOCTYPE html>
<html>


<head>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<title>Login</title>

<script type="text/javascript">
	$(document).ready(function(){
		
		var username = $(usernameDto).val();
	});
	
	

</script>

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
	top: 50%;
	left: 0;
	width: 100%;
	height: 400px;
	margin-top: -200px;
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
	max-width: 600px;
	margin: 0 auto;
	padding: 80px 0;
	height: 400px;
	text-align: center;
	
	h1{
		font-size: 40px;
		font-color: white;
		font-weight: 200;
	}
}


	padding: 20px 0;
	position: relative;
	z-index: 2;
	
	input{
		display: block;
		appearance: none;
		outline: 0;
		border: 1px solid fade(white, 40%);
		background-color: fade(white, 20%);
		width: 250px;
		
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
	
	#login{
		font-color :#53e3a6 ;
		appearance: none;
		outline: 0;
		background-color: white;
		border: 0;
		padding: 10px 15px;
		color: @prim;
		border-radius: 3px;
		width: 250px;
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
		<h1>Dobrodošli</h1>
		
		<form:form method="POST"  class="form" action="/patient/login/${username}" modelAttribute="userDto">
			<form:input class="form-control" id="usernameDto" placeholder="Korisničko ime" path="usernameDto" required="required"/>
			<br>
			<form:input class="form-control" type="password" placeholder="Lozinka" path="passwordDto" required="required"/>
			<br>
			<input type="submit"  id="login" value= "Uloguj se">
			<br>
			<a href="/registracija">Registruj se</a>
			</form:form>
		
	</div>
	
	
</div>


</body>
</html>