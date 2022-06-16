<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 12.12.2018
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng kí tài khoản</title>
    <link rel="stylesheet" href="/css/collapsible_menu.css"/>
</head>
<body>
<c:import url="shared/header.jsp">
    <c:param name="name" value="cont"/>
</c:import>
<br/>
<div class="center">
    <form:form modelAttribute="user">
        <h1 style="text-align:center" class="text-dark">Đăng kí</h1>
        <p class="text-danger"><b>Thông tin bắt buộc</b></p>


        <div class="md-form">
             <form:input path="username" cssClass="form-control" placeholder="Tên tài khoản"
                        required="true" autofocus="true" cssErrorClass="form-control is-invalid"/>
            <form:errors path="username" cssStyle="color:red"/>

        </div>

        <div class="md-form">
            <form:password path="password" cssClass="form-control"
                           required="true" placeholder="Mật khẩu" cssErrorClass="form-control is-invalid"/>
            <form:errors path="password" cssStyle="color:red"/>
        </div>

        <div class="md-form">
            <form:password path="passwordConfirm" cssClass="form-control"
                           cssErrorClass="form-control is-invalid"
                           required="true" placeholder="Nhập lại mật khẩu"/>
        </div>

        <div class="md-form">
            <form:input path="email" cssClass="form-control"
                           required="true" placeholder="Địa chỉ Email" cssErrorClass="form-control is-invalid"/>
            <form:errors path="email" cssStyle="color:red"/>

        </div>


        <div>
            <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" aria-expanded="false"
               aria-controls="collapseExample">
               Thêm thông tin
            </a>

        </div>

        <div class="collapse" id="collapseExample">

            <div class="mt-3">
                <div class="md-form">
                    <form:input path="address.name" cssClass="form-control" placeholder="Tên"
                                autofocus="true" cssErrorClass="form-control is-invalid"/>
                    <form:errors path="address.name" cssStyle="color:red"/>
                </div>
            </div>

            <div class="mt-3">
                <div class="md-form">
                    <form:input path="address.surname" cssClass="form-control" placeholder="Họ"
                                autofocus="true" cssErrorClass="form-control is-invalid"/>
                    <form:errors path="address.surname" cssStyle="color:red"/>
                </div>
            </div>
            <div class="mt-3">
                <div class="md-form">
                    <form:input path="address.phoneNumber" cssClass="form-control" placeholder="Số điện thoại"
                                autofocus="true" cssErrorClass="form-control is-invalid"/>
                 </div>
            </div>

            <div class="mt-3">
                <div class="md-form">
                    <form:input path="address.city" cssClass="form-control" placeholder="Thành phố"
                                autofocus="true" cssErrorClass="form-control is-invalid"/>
                </div>
            </div>


            <div class="mt-3">
                <div class="md-form">
                    <form:input path="address.street" cssClass="form-control" placeholder="Đường phố"
                                autofocus="true" cssErrorClass="form-control is-invalid"/>
                </div>
            </div>


            <div class="mt-3">
                <div class="md-form">
                    <form:input path="address.house_number" cssClass="form-control" placeholder="Số nhà / đường "
                                autofocus="true" cssErrorClass="form-control is-invalid"/>
                </div>
            </div>


            <div class="mt-3">
                <div class="md-form">
                    <form:input path="address.postcode" cssClass="form-control" placeholder="Mã bưu chính"
                                autofocus="true" cssErrorClass="form-control is-invalid"/>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="col-xs-6 col-sm-6 col-md-6">
                <input type="submit" class="btn btn-lg btn-block btn-success " value="Đăng kí"/>
            </div>
            <div class="col-xs-6 col-sm-6 col-md-6">
            </div>
        </div>
    </form:form>
</div>
<br/>
<jsp:include page="shared/footer.jsp"/>
<script>
    var coll = document.getElementsByClassName("collapsible");
    var i;

    for (i = 0; i < coll.length; i++) {
        coll[i].addEventListener("click", function () {
            this.classList.toggle("active");
            var content = this.nextElementSibling;
            if (content.style.display === "block") {
                content.style.display = "none";
            } else {
                content.style.display = "block";
            }
        });
    }
</script>
</body>
</html>