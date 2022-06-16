<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 06.01.2019
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GIỏ hàng</title>
</head>
<body>
<jsp:include page="shared/header.jsp"/>
<div class="container">
    <c:if test="${!empty error}">
      Bị lỗi ${error }
    </c:if>
</div>
<c:if test="${!empty cart_items.items}">
    <div class="container">
        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col">Tên mặt hàng</th>
                <th scope="col">Giá tiền</th>
                <th scope="col">Số lượng</th>
                <th scope="col">Tổng tiền</th>
                <th scope="col">Chức năng</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${cart_items.items}">

                <tr>
                    <th scope="row">${item.item.name}</th>
                    <td>${item.item.price}</td>
                    <td>${item.amount}<c:if test="${item.item.type.category == 'Wąż'}">1</c:if></td>
                    <td><c:if test="${item.item.type.category == 'Wąż'}">${item.item.price}</c:if>
                        <c:if test="${item.item.type.category != 'Wąż'}">${item.item.price * item.amount}</c:if></td>
                    <td>
                        <button class="btn btn-danger" onclick="location.href='/delete/${item.item.id}'">Xoá sản phẩm!</button>
                    </td>
                </tr>

            </c:forEach>
            </tbody>
        </table>
        <button class="btn peach-gradient" onclick="location.href='/order'">Đặt hàng ngay!</button>
    </div>
</c:if>
<c:if test="${empty cart_items.items}">
    <div class="container">
       Không có sản phẩm trong giỏ hàng
    </div>
</c:if>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
