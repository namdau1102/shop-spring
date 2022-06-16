<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 06.01.2019
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đơn hàng</title>
</head>
<body>
<jsp:include page="shared/header.jsp"/>
<div class="container">
    <c:if test="${!empty orders}">
        <c:forEach var="orders_list" items="${orders}">

            <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col">ID đặt hàng</th>
                <th scope="col">Tên và họ</th>
                <th scope="col">Địa chỉ</th>
                <th scope="col">Số điện thoại</th>
                <th scope="col">Sản phẩm</th>
                <th scope="col">Tình trạng</th>
                <th scope="col">Hủy bỏ</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="orr" items="${orders_list}">
                <td scope="row">${orr.orderId}</td>
                <td>${orr.user.address.name} ${orr.user.address.surname}</td>
                <td> ${orr.user.address.city} ${orr.user.address.postcode}
                        ${orr.user.address.street} ${orr.user.address.house_number}</td>
                <td>${orr.user.address.phoneNumber}</td>
                <td>${orr.item.name}</td>
                <td>
                    <c:if test="${orr.orderType.orderStatus == 'ORDER_ORDERED'}">
                        <p class="text-danger font-weight-bold">Đã đặt hàng</p>
                    </c:if>
                    <c:if test="${orr.orderType.orderStatus == 'ORDER_COMPLETED'}">
                        <p class="text-success font-weight-bold">Đặt hàng thanh công</p>
                    </c:if>

                    <c:if test="${orr.orderType.orderStatus == 'ORDER_CANCELED'}">
                        <p class="text-warning font-weight-bold">Đơn hàng bị huỷ</p>
                    </c:if>
                </td>
                <td><c:if test="${orr.orderType.orderStatus == 'ORDER_ORDERED'}"><a
                        href="/cancel/${orr.orderId.intValue()}?us=0">Hủy bỏ</a>
                </c:if>
                </td>
                </tbody>

            </c:forEach>

        </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty orders}">
        Không có đơn hàng nào
    </c:if>
</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
