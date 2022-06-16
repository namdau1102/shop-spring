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
<title>Cấp lại mật khẩu</title>
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
					<h2>Đặt lại mật khẩu</h2>
					<p>Bạn hãy đặt 1 mật khẩu dễ nhớ - bảo mật cao!</p>


				</div>
				<form:form modelAttribute="email" action="/user/restpassword"  method="POST" class="card mt-4">
					<div class="card-body">
						<div class="form-group">
							<label for="email-for-pass">Nhập Email </label>
							<form:input path="email"  cssClass="form-control"
                           required="true" placeholder="Địa chỉ Email" cssErrorClass="form-control is-invalid"/>    
                                       <form:errors path="email" cssStyle="color:red"/>    
                                       		<p class="text-danger"> <b>${mess }</b> </p>
                                                    
                        <small class="form-text text-muted">Email bạn sử dụng để đăng
								kí tài khoản .</small>
						</div>
					</div>
					<div class="card-footer">
						<button class="btn btn-success" type="submit">Xác nhận</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<br />
	<jsp:include page="shared/footer.jsp" />
</body>
</html>
