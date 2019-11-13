<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Registration</title>
</head>
<body>


	<div class="container">
		<%@ include file="Template.jsp"%>
	</div>

	<h1 th:text="#{label.form.title}">form</h1>
	<form action="/" th:object="${user}" method="POST" enctype="utf8">
		<div>
			<label th:text="#{label.user.firstName}">first</label> <input
				th:field="*{firstName}" />
			<p th:each="error: ${#fields.errors('firstName')}" th:text="${error}">Validation
				error</p>
		</div>
		<div>
			<label th:text="#{label.user.lastName}">last</label> <input
				th:field="*{lastName}" />
			<p th:each="error : ${#fields.errors('lastName')}" th:text="${error}">Validation
				error</p>
		</div>
		<div>
			<label th:text="#{label.user.email}">email</label> <input
				type="email" th:field="*{email}" />
			<p th:each="error : ${#fields.errors('email')}" th:text="${error}">Validation
				error</p>
		</div>
		<div>
			<label th:text="#{label.user.password}">password</label> <input
				type="password" th:field="*{password}" />
			<p th:each="error : ${#fields.errors('password')}" th:text="${error}">Validation
				error</p>
		</div>
		<div>
			<label th:text="#{label.user.confirmPass}">confirm</label> <input
				type="password" th:field="*{matchingPassword}" />
		</div>
		<button type="submit" th:text="#{label.form.submit}">submit</button>
	</form>

</body>
</html>