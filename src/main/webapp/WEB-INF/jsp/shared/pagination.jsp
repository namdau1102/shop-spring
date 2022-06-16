<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 05.01.2019
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:url var="firstPageUrl" value="${mainUrl}?page=0&size=${page.size}"/>
<c:url var="prevPageUrl" value="${mainUrl}?page=${page.number - 1}&size=${page.size}"/>
<c:url var="nextPageUrl" value="${mainUrl}?page=${page.number + 1}&size=${page.size}"/>
<c:url var="lastPageUrl" value="${mainUrl}?page=${page.totalPages - 1}&size=${page.size}"/>
<c:set var="list" value="${page}"/>

<nav aria-label="Page navigation example">
    <ul class="pagination pg-blue">
        <li ${list.first?'class="page-item disabled"':'page-item'}>
            <a href="${list.first?"#":firstPageUrl}" class="page-link">
                <span>Pierwsza</span>
            </a>
        </li>

        <li ${list.first?'class="page-item disabled"':'page-item'}>
            <a href="${list.first?'#':prevPageUrl}" class="page-link">
                <span>&laquo;</span>
            </a>
        </li>

        <c:forEach var="pageIdx" begin="${0}" end="${list.totalPages-1}">
            <c:url var="pageUrl" value="/${mainUrl}?page=${pageIdx}&size=${list.size}"/>
            <li ${pageIdx == list.number?'class="page-item active"':'page-item'}>
                <a href="${pageUrl}" class="page-link">${pageIdx+1}</a>
            </li>
        </c:forEach>

        <li ${list.last?'class="page-item disabled"':'page-item'}>
            <a href="${list.last?'#':nextPageUrl}" class="page-link">
                <span>&raquo;</span>
            </a>
        </li>

        <li ${list.last?'class="page-item disabled"':'page-item'}>
            <a href="${list.last?"#":lastPageUrl}" class="page-link">
                <span>Ostatnia</span>
            </a>
        </li>

     
        <c:forEach var="size" items="${pageSizes}">
            <c:url var="pageUrl" value="/${mainUrl}?page=${list.number}&size=${size}"/>
            <li class="page-item">
                <a href="${pageUrl}" class="page-link">${size}</a>
            </li>
        </c:forEach>
    </ul>
</nav>