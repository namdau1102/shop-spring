<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 03.01.2019
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Thay đổi mật khẩu</title>
</head>
<body>
	<jsp:include page="shared/header.jsp" />

	<security:authorize access="isAuthenticated()">
		<jsp:forward page="/" />
	</security:authorize>
	<br />

	<div class="container padding-bottom-3x mb-2 mt-5">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10">
				<div class="forgot">
					<h2>Thay đổi mật khẩu</h2>
					<p>Bạn hãy đặt 1 mật khẩu dễ nhớ - bảo mật cao!</p>
				</div>
				<form:form modelAttribute="useremail" action="/user/comletionPass"
					method="post">

					<div class="md-form">
						<label for="password">Nhập mật khẩu mới</label>
						<form:input path="password" cssClass="form-control"
							required="true" autofocus="true"
							cssErrorClass="form-control is-invalid" />
						<p class="text-danger">${errorPass }</p>
					</div>
					<div class="md-form">
						<label for="completionpassword">Nhập lại mật khẩu mới</label>
						<form:input path="completionpassword" cssClass="form-control"
							required="true" autofocus="true"
							cssErrorClass="form-control is-invalid" />
						<p class="text-danger">${errorPass }</p>

					</div>
					<div class="form-group">
						<security:csrfInput />
						<button type="submit" class="btn btn-success">Thay đổi
							mật khẩu</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<br />
	<jsp:include page="shared/footer.jsp" />
</body>
</html>
