<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 26.01.2019
  Time: 20:12
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
        <form:form modelAttribute="animal" action="/pet/update" enctype="multipart/form-data">
        <form:input type="hidden" value="${animal.id}" path="id"/>
        <br/>

        <div class="form-group">
            Nazwa
            <form:input path="name" cssClass="form-control" placeholder="Nazwa"
                        autofocus="true" cssErrorClass="form-control is-invalid"/>
        </div>
        <form:errors path="name" cssStyle="color:red"/>

        <div class="form-group">
           Opis
            <form:textarea rows="10" cols="10" path="description" cssClass="form-control"
                           placeholder="Opis" cssErrorClass="form-control is-invalid"/>
        </div>
        <form:errors path="description" cssStyle="color:red"/>

        <div class="form-group">
            Cena
            <form:input path="price" cssClass="form-control"
                        placeholder="Cena" cssErrorClass="form-control is-invalid"/>
        </div>
        <form:errors path="price" cssStyle="color:red"/>


        <form:input type="hidden" path="amount" value="1"/>
        <div class="form-group">
            Wiek
            <form:input path="age" cssClass="form-control"
                        placeholder="Wiek" cssErrorClass="form-control is-invalid"/>
        </div>
        <form:errors path="age" cssStyle="color:red"/>

        <br/>
        <div class="form-group"><label for="type.id">Rodzaj</label><form:select path="type.id"
                                                                                cssClass="form-control">
            <form:options items="${item_types}" itemLabel="category" itemValue="id"/>
        </form:select>
            <br/>
            <form:input path="deleted" value="false" type="hidden"/>

            Zdjęcia
            <div class="form-group"><input type="file" name="multipartFile"/></div>
            <div class="form-group"><input type="file" name="multipartFile"/></div>
            <div class="form-group"><input type="file" name="multipartFile"/></div>


            <div class="row">

                <div class="col-xs-6 col-sm-6 col-md-6">
                    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Zapisz"/>
                </div>

            </div>
            </form:form>
            <div class="col-xs-6 col-sm-6 col-md-6">
                <button class="btn peach-gradient" onclick="location.href='/pet/delete/${animal.id}'">Usuń przedmiot!</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
