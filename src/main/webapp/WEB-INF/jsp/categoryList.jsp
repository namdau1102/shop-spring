<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 27.01.2019
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Danh sach danh muc</title>
</head>
<body>
	<jsp:include page="shared/header.jsp" />

	<div class="container">

		<c:if test="${!empty categories}">
			<a href="/user/add/categories">
				<button class="btn btn-primary">Add category</button>
			</a>
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Ten danh muc</th>
						<th scope="col">Chức năng</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${categories}" var="user">
						<tr>
							<td>${user.id }</td>
							<td>${user.category}</td>
							<td>
								<form:form  method="POST" action="delete/categories/${user.id }">
									<button type="submit" class="btn btn-danger">Delete</button>
								</form:form> <a class="text-success" href="edit/categories/${user.id }">
									<button class="btn btn-primary">Edit</button>
							</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</c:if>

		<c:if test="${empty categories}">
        Không có danh muc
    </c:if>
	</div>
	<jsp:include page="shared/footer.jsp" />
</body>
</html>
