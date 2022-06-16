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
    <title>Xác nhân mã</title>
    <link rel="stylesheet" href="/css/collapsible_menu.css"/>
</head>
<body>
<c:import url="shared/header.jsp">
    <c:param name="name" value="cont"/>
</c:import>
<br/>
<div class="center">
    <form:form modelAttribute="email">
        <h1 style="text-align:center" class="text-dark">Xác nhận đổi mật khẩu</h1>
        <p class="text-danger"><b>Vui lòng nhập mã gửi về email : ${emailRestPass }</b></p>
        <p class="text-danger"><b>Mã chỉ hoạt động chỉ sau 1 phút </b></p>
		<p class="text-danger"> <b>${error }</b> </p>
        <div class="md-form">
             <form:input path="code" cssClass="form-control" placeholder="Mã xác nhận"
                        required="true" autofocus="true" cssErrorClass="form-control is-invalid"/>
            <form:errors path="code" cssStyle="color:red"/>

        </div>
        <div class="row">
            <div class="col-xs-6 col-sm-6 col-md-6">
                <input type="submit" class="btn btn-lg btn-block btn-success " value="Xác nhận"/>
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
