<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 06.01.2019
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Quản lí tài khoản</title>
</head>
<body>
	<jsp:include page="shared/header.jsp" />
	<form:form modelAttribute="user" action="/user/update">
		<h1 style="text-align: center">Thông tin tài khoản</h1>
		<div class="container">

			<div class="mt-3">
				<div class="md-form">
					<form:input type="hidden" path="id" value="${user.id}" />
					<form:input path="name" cssClass="form-control" placeholder="Tên"
						autofocus="true" cssErrorClass="form-control is-invalid" />
				</div>
			</div>

			<div class="mt-3">
				<div class="md-form">
					<form:input path="surname" cssClass="form-control" placeholder="Họ"
						autofocus="true" cssErrorClass="form-control is-invalid" />
				</div>
			</div>
			<div class="mt-3">
				<div class="md-form">
					<form:input path="phoneNumber" cssClass="form-control"
						placeholder="Số điện thoại" autofocus="true"
						cssErrorClass="form-control is-invalid" />
				</div>
			</div>

			<div class="md-form">
				<div class="form-group">
					<form:input path="city" cssClass="form-control"
						placeholder="Thành phố" autofocus="true"
						cssErrorClass="form-control is-invalid" />
				</div>
			</div>


			<div class="md-form">
				<div class="form-group">
					<form:input path="street" cssClass="form-control"
						placeholder="Đường phố" autofocus="true"
						cssErrorClass="form-control is-invalid" />
				</div>
			</div>


			<div class="md-form">
				<div class="form-group">
					<form:input path="house_number" cssClass="form-control"
						placeholder="Số nhà / đường" autofocus="true"
						cssErrorClass="form-control is-invalid" />
				</div>
			</div>


			<div class="md-form">
				<div class="form-group">
					<form:input path="postcode" cssClass="form-control"
						placeholder="Mã bưu chính" autofocus="true"
						cssErrorClass="form-control is-invalid" />
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6 col-sm-6 col-md-6">
					<input type="submit" class="btn btn-lg btn-primary btn-block"
						value="Đăng kí thông tin" />
				</div>
				<div class="col-xs-6 col-sm-6 col-md-6"></div>
			</div>
		</div>
	</form:form>

	<form:form modelAttribute="email" action="/user/updateuser">
		<div class="container">
			<div class="md-form">
				<div class="form-group">
					<form:input path="email" cssClass="form-control"
						placeholder="Email" autofocus="true"
						cssErrorClass="form-control is-invalid" />
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6 col-sm-6 col-md-6">
					<input type="submit" class="btn btn-lg btn-primary btn-block"
						value="Xác nhận" />
				</div>
				<div class="col-xs-6 col-sm-6 col-md-6"></div>
			</div>
		</div>

	</form:form>
	<jsp:include page="shared/footer.jsp" />
</body>
</html>
