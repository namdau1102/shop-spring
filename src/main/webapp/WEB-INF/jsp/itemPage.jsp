<%@ page import="com.project.shop.models.Item" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 05.01.2019
  Time: 00:54
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
    <br/>
    <div class="row">
        <!-- /.col-lg-3 -->
        <div class="col-lg-9">
            <c:forEach items="${animal.images}" var="image">
                <img style="max-width: 200px; height:200px" class="image" src="${image}" alt="">
            </c:forEach>
            <div class="card mt-4">
                <div class="card-body">
                    <h3 class="card-title">${animal.name}</h3>
                    <h4><fmt:formatNumber type="currency" value="${animal.price}"/></h4>
                    <p class="card-text">${animal.description}
                    <ul>
                        <li>Danh mục: ${animal.type.category}</li>
                        <c:if test="${animal['class'].simpleName == 'Pet'}">
                        <li>Tuổi: ${animal.age} miesięcy</c:if></li>
                        <c:if test="${animal['class'].simpleName == 'Feed'}">
                            <li>Ilość w paczace: ${animal.gram}g</li>
                        </c:if>
                        <c:if test="${animal['class'].simpleName == 'Accessory'}">
                            <li>Marka: ${animal.brand}</li>
                        </c:if>
                    </ul>
                </div>
            </div>
            <!-- /.card -->

            <div class="card card-outline-secondary my-4">
                <div class="card-header">
                    Mua hàng
                </div>
                <div class="card-body">
                    <c:if test="${animal['class'].simpleName == 'Pet'}"><c:set var="type"
                                                                               value="/pet/buy?id=${animal.id}"/>
                    </c:if>
                    <c:if test="${animal['class'].simpleName == 'Feed'}"><c:set var="type"
                                                                                value="/feed/buy?id=${animal.id}"/>
                    </c:if>
                    <c:if test="${animal['class'].simpleName == 'Accessory'}"><c:set var="type"
                                                                                     value="/accessory/buy?id=${animal.id}"/>
                    </c:if>

                    <sec:authorize access=" isAuthenticated()">
                        <!-- <a href="${type}" class="btn btn-success">Kup teraz</a>-->
                        <form:form method="POST" action="${type}"
                                   modelAttribute="cart_item">
                            <c:if test="${animal['class'].simpleName != 'Pet'}">
                                <form:input maxlength="5" size="5" type="number" min="1" step="1"
                                            path="amount"/>
                            </c:if>
                            <c:if test="${animal['class'].simpleName == 'Pet'}">
                                <form:input type="hidden" value="1" path="amount"/>
                            </c:if>
                            <button type="submit" class="btn btn-success">Mua ngay</button>
                        </form:form>

                    </sec:authorize>
                    <sec:authorize access="isAnonymous()"><a href="/login">Đăng nhập</a>, Hãy đăng nhập để mua hàng</sec:authorize>
                </div>
            </div>
            <!-- /.card -->
        </div>
        <!-- /.col-lg-9 -->
    </div>
</div>
</body>
<!-- /.container -->

<!-- Footer -->


<!-- Bootstrap core JavaScript -->
<script src="http://code.jquery.com/jquery-1.12.3.min.js"></script>
<script src="/js/simple-lightbox.min.js"></script>

<jsp:include page="shared/footer.jsp"/>
</body>
</html>
