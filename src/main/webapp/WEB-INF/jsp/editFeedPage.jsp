<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 27.01.2019
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sklep zoologiczny</title>
</head>
<body>

<jsp:include page="shared/header.jsp"/>
<div class="container">

    <div class="card-body">
        <form:form modelAttribute="animal" action="/feed/update" enctype="multipart/form-data">
        <form:input type="hidden" value="${animal.id}" path="id"/>
        <br/>

        <div class="form-group">
            Tên
            <form:input path="name" cssClass="form-control" placeholder="Nazwa"
                        autofocus="true" cssErrorClass="form-control is-invalid"/>
        </div>
        <form:errors path="name" cssStyle="color:red"/>

        <div class="form-group">
            Mô tả
            <form:textarea rows="10" cols="10" path="description" cssClass="form-control"
                           placeholder="Opis" cssErrorClass="form-control is-invalid"/>
        </div>
        <form:errors path="description" cssStyle="color:red"/>

        <div class="form-group">
            Giá
            <form:input path="price" cssClass="form-control"
                        placeholder="Cena" cssErrorClass="form-control is-invalid"/>
        </div>
        <form:errors path="price" cssStyle="color:red"/>


        <div class="form-group">
            Số lượng
            <form:input path="amount" cssClass="form-control"
                        placeholder="Ilość" cssErrorClass="form-control is-invalid"/>
        </div>
        <form:errors path="amount" cssStyle="color:red"/>

        <div class="form-group">
            Ngữ 0w 
            <form:input path="gram" cssClass="form-control"
                        placeholder="Gramatura" cssErrorClass="form-control is-invalid"/>
        </div>
        <form:errors path="gram" cssStyle="color:red"/>

        <br/>
        <div class="form-group"><label for="type.id">Danh mục</label><form:select path="type.id"
                                                                                cssClass="form-control">
            <form:options items="${item_types}" itemLabel="category" itemValue="id"/>
        </form:select>
            <form:input path="deleted" value="false" type="hidden"/>

            <br/>

            Hình ảnh
            <div class="form-group"><input type="file" name="multipartFile"/></div>
            <div class="form-group"><input type="file" name="multipartFile"/></div>
            <div class="form-group"><input type="file" name="multipartFile"/></div>


            <div class="row">

                <div class="col-xs-6 col-sm-6 col-md-6">
                    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Cập nhật"/>
                </div>

            </div>
            </form:form>

            <div class="col-xs-6 col-sm-6 col-md-6">
                <input class="btn peach-gradient" type="button"
                       onclick="location.href='/feed/delete/${animal.id}'" value="Xoá"/>
            </div>
        </div>
    </div>
</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
