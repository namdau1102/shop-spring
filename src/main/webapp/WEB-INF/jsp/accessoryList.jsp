<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 03.01.2019
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Phụ kiện</title>
    <link rel="stylesheet" href="/css/product_layout.css"/>
</head>
<body>
<jsp:include page="shared/header.jsp"/>
<c:if test="${empty accessory_list.content}">
    <div class="container" style="width: 60%;">
        Không có kết quả
    </div>
</c:if>
<form:form id="searchForm" modelAttribute="searchForm" method="POST">
    <div class="container" style="width: 60%;">
        <br/>
        <div class="row">
            <div class="form-group col-md-6">
                <label for="phrase">Tên:</label>
                <form:input path="phrase" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                <form:errors path="phrase" cssClass="error text-danger" element="div"/>
            </div>

            <div class="form-group col-md-3">
                <label for="phrase">Giá từ:</label>
                <form:input path="priceMin" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                <form:errors path="priceMin" cssClass="error text-danger" element="div"/>
            </div>
            <div class="form-group col-md-3">
                <label for="phrase">Giá đến:</label>
                <form:input path="priceMax" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                <form:errors path="priceMax" cssClass="error text-danger" element="div"/>
            </div>
        </div>
        <div class="row">

            <div class="form-group col-md-8"></div>

            <div class="form-group col-md-2">
                <c:if test="${searchForm.isEmpty() eq false}">
                    <a href="/pet/clear" class="btn btn-success">
                        <span class="glyphicon glyphicon-refresh"></span> Không có giá trị
                    </a>
                </c:if>
            </div>

            <div class="form-group col-md-2">
                <button type="submit" class="btn btn-info">
                    <span class="glyphicon glyphicon-search"></span> Lọc
                </button>
            </div>
        </div>
    </div>
</form:form>
<c:if test="${!empty accessory_list.content}">
    <br/>

    <div class="container">
        <div class="text-danger font-weight-bold">${info}</div>
        <div class="row">

            <!-- /.col-lg-3 -->

            <div class="col-lg-12">

                <div class="row">
                    <c:forEach var="acc" items="${accessory_list.content}">

                        <div class="col-lg-3 col-md-6 mb-4">
                            <div class="card h-75" style="min-height: 450px">
                                <div style="height:170px">
                                    <a href="<c:url value="/accessory/${acc.id}"/>"><img
                                            style="max-width:100%;width:100%; height:100%;"
                                            class="img-center card-img-top"
                                            src="${acc.images[0]}"
                                            alt=""></a>
                                </div>
                                <div class="card-body">
                                    <h4 class="card-title">
                                        <a href="<c:url value="/accessory/${acc.id}"/>">${fn:substring(acc.name,0,25)}</a>
                                    </h4>
                                    <h5><fmt:formatNumber value="${acc.price}" type="currency"
                                                          currencySymbol="PLN"/></h5>
                                    <p class="card-text">${fn:substring(acc.description,0,50)}</p>
                                </div>

                                <security:authorize access="isAuthenticated()">
                                    <div class="card-footer">
                                        <div class="row">
                                            <form:form method="POST" action="/accessory/buy?id=${acc.id}"
                                                       modelAttribute="cart_item">
                                                <form:input maxlength="5" size="5" type="number" min="1" step="1"
                                                            placeholder="Ilość"
                                                            path="amount"/>

                                                <button type="submit" class="btn btn-success">Kup</button>
                                                <security:authorize access="hasRole('ROLE_ADMIN')">
                                                    <input class="btn peach-gradient" type="button"
                                                           onclick="location.href='/accessory/edit/${acc.id}'"
                                                           value="Edytuj"/>
                                                </security:authorize>
                                            </form:form>

                                        </div>
                                    </div>
                                </security:authorize>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <c:set var="page" value="${accessory_list}" scope="request"/>
        <c:set var="mainUrl" value="accessory/list" scope="request"/>
        <jsp:include page="shared/pagination.jsp"/>
    </div>
</c:if>


<jsp:include page="shared/footer.jsp"/>
</body>
</html>
