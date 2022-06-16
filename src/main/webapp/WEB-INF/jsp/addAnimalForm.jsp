<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 04.01.2019
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sklep zoologiczny</title>
</head>
<body>
<jsp:include page="shared/header.jsp"/>

<div id="main" class="container">

    <form:form method="POST" modelAttribute="animal" enctype="multipart/form-data">

        <div class="form-group"><label for="name">Nazwa</label><form:input path="name" cssClass="form-control"/>
            <form:errors path="name" cssStyle="color:red"/>
        </div>

        <div class="form-group"><label for="description">Opis</label><form:input path="description"
                                                                                 cssClass="form-control"/>
            <form:errors path="description" cssStyle="color:red"/>
        </div>

        <div class="form-group"><label for="type.id">Type</label><form:select path="type.id" cssClass="form-control">
            <form:options items="${type_list}" itemLabel="category" itemValue="id"/>
        </form:select>
        </div>

        <div class="form-group"><label for="price">Cena</label><form:input path="price" cssClass="form-control"/>
            <form:errors path="price" cssStyle="color:red"/>
        </div>

        <div class="form-group"><label for="amount">Ilość</label><form:input path="amount" cssClass="form-control"/>
            <form:errors path="amount" cssStyle="color:red"/>
        </div>

        <div class="form-group"><input type="file" name="multipartFile"/></div>
        <div class="form-group"><input type="file" name="multipartFile"/></div>
        <div class="form-group"><input type="file" name="multipartFile"/></div>

        <button type="submit" class="btn green-button">Done</button>

    </form:form>
</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
