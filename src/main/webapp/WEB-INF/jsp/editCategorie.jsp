<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Categorie</title>
</head>
<body>
	<jsp:include page="shared/header.jsp" />
	<div class="container">
		<form:form modelAttribute="type" action="/user/update/categories/${type.id }">
			<div class="form-group">
				<label for="exampleInputEmail1">Name Categorie</label> 
				<form:input
					type="text" class="form-control" value="${type.category }" id="category" path="category"
					aria-describedby="emailHelp" placeholder="Enter email"/>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form:form>
	</div>
</body>
</html>