<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page buffer="16kb" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Account</title>
</head>
<body>
	<jsp:include page="shared/header.jsp" />
	<div class="container">
		<form:form modelAttribute="user" action="/user/add/user">
			<div class="form-group">
				<label for="exampleInputEmail1">User name</label>
				<form:input type="text" class="form-control" id="username"
					path="username" aria-describedby="emailHelp" placeholder="username" />

			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Email</label>

				<form:input type="email" class="form-control" id="email"
					path="email" aria-describedby="emailHelp" placeholder="Email" />
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Password</label>

				<form:input type="password" class="form-control" id="password"
					path="password" aria-describedby="emailHelp" placeholder="Password" />
			</div>

			<button type="submit" class="btn btn-primary">Submit</button>
		</form:form>
	</div>
</body>
</html>