<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 03.01.2019
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Danh sách động vật</title>
<link rel="stylesheet" href="/css/product_layout.css" />
</head>
<body>
	<jsp:include page="shared/header.jsp" />
	<div class="container" style="width: 60%;">
		<br>
		<c:if test="${empty pet_list.content}">
        Không có
    </c:if>
		<form:form id="searchForm" modelAttribute="searchForm" method="POST">
			<div class="row">
				<div class="form-group col-md-6">
					<label for="phrase">Tên tìm kiếm:</label>
					<form:input path="phrase" cssClass="form-control"
						cssErrorClass="form-control is-invalid" />
					<form:errors path="phrase" cssClass="error text-danger"
						element="div" />
				</div>

				<div class="form-group col-md-3">
					<label for="phrase">Giá từ:</label>
					<form:input path="priceMin" cssClass="form-control"
						cssErrorClass="form-control is-invalid" />
					<form:errors path="priceMin" cssClass="error text-danger"
						element="div" />
				</div>
				<div class="form-group col-md-3">
					<label for="phrase">Giá đến:</label>
					<form:input path="priceMax" cssClass="form-control"
						cssErrorClass="form-control is-invalid" />
					<form:errors path="priceMax" cssClass="error text-danger"
						element="div" />
				</div>
			</div>
			<div class="row">

				<div class="form-group col-md-8"></div>

				<div class="form-group col-md-8">
					<c:if test="${searchForm.isEmpty() eq false}">
						<a href="/pet/clear" class="btn btn-success"> <span
							class="glyphicon glyphicon-refresh"></span>Lọc
						</a>
					</c:if>
				</div>

				<div class="form-group col-md-8">
					<button type="submit" class="btn btn-info">
						<span class="glyphicon glyphicon-search"></span> Tìm kiếm
					</button>
				</div>
			</div>
		</form:form>
	</div>

	<div class="container">

		<div id="content" class="my-5">
			<div id="filterbar" class="collapse">
				<div class="box border-bottom">
					<div class="form-group text-center">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-success form-check-label"> <input
								class="form-check-input" type="radio"> Reset
							</label> <label class="btn btn-success form-check-label active">
								<input class="form-check-input" type="radio" checked>
								Apply
							</label>
						</div>
					</div>
					<div>
						<label class="tick">New <input type="checkbox"
							checked="checked"> <span class="check"></span>
						</label>
					</div>
					<div>
						<label class="tick">Sale <input type="checkbox"> <span
							class="check"></span>
						</label>
					</div>
				</div>
				<div class="box border-bottom">
					<div class="box-label text-uppercase d-flex align-items-center">
						Outerwear
						<button class="btn ml-auto" type="button" data-toggle="collapse"
							data-target="#inner-box" aria-expanded="false"
							aria-controls="inner-box" id="out" onclick="outerFilter()">
							<span class="fas fa-plus"></span>
						</button>
					</div>
					<div id="inner-box" class="collapse mt-2 mr-1">
						<div class="my-1">
							<label class="tick">Windbreaker <input type="checkbox"
								checked="checked"> <span class="check"></span>
							</label>
						</div>
						<div class="my-1">
							<label class="tick">Jumpsuit <input type="checkbox">
								<span class="check"></span>
							</label>
						</div>
						<div class="my-1">
							<label class="tick">Jacket <input type="checkbox">
								<span class="check"></span>
							</label>
						</div>
						<div class="my-1">
							<label class="tick">Coat <input type="checkbox">
								<span class="check"></span>
							</label>
						</div>
						<div class="my-1">
							<label class="tick">Raincoat <input type="checkbox">
								<span class="check"></span>
							</label>
						</div>
						<div class="my-1">
							<label class="tick">Handbag <input type="checkbox"
								checked> <span class="check"></span>
							</label>
						</div>
						<div class="my-1">
							<label class="tick">Warm vest <input type="checkbox">
								<span class="check"></span>
							</label>
						</div>
						<div class="my-1">
							<label class="tick">Wallets <input type="checkbox"
								checked> <span class="check"></span>
							</label>
						</div>
					</div>
				</div>
				<div class="box border-bottom">
					<div class="box-label text-uppercase d-flex align-items-center">
						season
						<button class="btn ml-auto" type="button" data-toggle="collapse"
							data-target="#inner-box2" aria-expanded="false"
							aria-controls="inner-box2">
							<span class="fas fa-plus"></span>
						</button>
					</div>
					<div id="inner-box2" class="collapse mt-2 mr-1">
						<div class="my-1">
							<label class="tick">Spring <input type="checkbox"
								checked="checked"> <span class="check"></span>
							</label>
						</div>
						<div class="my-1">
							<label class="tick">Summer <input type="checkbox">
								<span class="check"></span>
							</label>
						</div>
						<div class="my-1">
							<label class="tick">Autumn <input type="checkbox" checked>
								<span class="check"></span>
							</label>
						</div>
						<div class="my-1">
							<label class="tick">Winter <input type="checkbox">
								<span class="check"></span>
							</label>
						</div>
						<div class="my-1">
							<label class="tick">Rainy <input type="checkbox">
								<span class="check"></span>
							</label>
						</div>
					</div>
				</div>
				<div class="box border-bottom">
					<div class="box-label text-uppercase d-flex align-items-center">
						price
						<button class="btn ml-auto" type="button" data-toggle="collapse"
							data-target="#price" aria-expanded="false" aria-controls="price">
							<span class="fas fa-plus"></span>
						</button>
					</div>
					<div class="collapse" id="price">
						<div class="middle">
							<div class="multi-range-slider">
								<input type="range" id="input-left" min="0" max="100" value="10">
								<input type="range" id="input-right" min="0" max="100"
									value="50">
								<div class="slider">
									<div class="track"></div>
									<div class="range"></div>
									<div class="thumb left"></div>
									<div class="thumb right"></div>
								</div>
							</div>
						</div>
						<div
							class="d-flex align-items-center justify-content-between mt-2">
							<div>
								<span id="amount-left" class="font-weight-bold"></span> uah
							</div>
							<div>
								<span id="amount-right" class="font-weight-bold"></span> uah
							</div>
						</div>
					</div>
				</div>
				<div class="box">
					<div class="box-label text-uppercase d-flex align-items-center">
						size
						<button class="btn ml-auto" type="button" data-toggle="collapse"
							data-target="#size" aria-expanded="false" aria-controls="size">
							<span class="fas fa-plus"></span>
						</button>
					</div>
					<div id="size" class="collapse">
						<div class="btn-group d-flex align-items-center flex-wrap"
							data-toggle="buttons">
							<label class="btn btn-success form-check-label"> <input
								class="form-check-input" type="checkbox"> 80
							</label> <label class="btn btn-success form-check-label"> <input
								class="form-check-input" type="checkbox" checked> 92
							</label> <label class="btn btn-success form-check-label"> <input
								class="form-check-input" type="checkbox" checked> 102
							</label> <label class="btn btn-success form-check-label"> <input
								class="form-check-input" type="checkbox" checked> 104
							</label> <label class="btn btn-success form-check-label"> <input
								class="form-check-input" type="checkbox" checked> 106
							</label> <label class="btn btn-success form-check-label"> <input
								class="form-check-input" type="checkbox" checked> 108
							</label>
						</div>
					</div>
				</div>
			</div>
			<div id="products">
				<div class="row mx-0">
					<c:forEach var="pet" items="${pet_list.content}">
						<div class="col-lg-4 col-md-6" style="padding-top: 12px">
							<div class="card d-flex flex-column align-items-center">
								<div class="product-name">${pet.name }</div>
								<div class="card-img">
									<a href="<c:url value="/pet/${pet.id}"/>"> <img
										src="${pet.images[0]}" alt="">

									</a>
								</div>
								<div class="card-body pt-5">
									<div class="text-muted text-center mt-auto">Available
										Colors</div>


									<div
										class="d-flex align-items-center justify-content-center colors my-2">
										<div class="btn-group" data-toggle="buttons"
											data-tooltip="tooltip" data-placement="right"
											title="choose color">
											<label class="btn btn-red form-check-label"> <input
												class="form-check-input" type="radio" autocomplete="off">
											</label> <label class="btn btn-blue form-check-label active">
												<input class="form-check-input" type="radio"
												autocomplete="off">
											</label> <label class="btn btn-green form-check-label"> <input
												class="form-check-input" type="radio" autocomplete="off">
											</label> <label class="btn btn-orange form-check-label"> <input
												class="form-check-input" type="radio" autocomplete="off">
											</label> <label class="btn btn-pink form-check-label"> <input
												class="form-check-input" type="radio" autocomplete="off">
											</label>
										</div>

									</div>

									<div
										class="d-flex align-items-center price justify-content-center">
										<div class="del mr-2">
											<span class="text-dark">0</span>
										</div>
										<div class="font-weight-bold">${pet.price}VNĐ</div>
									</div>
									<div class="d-flex">
										<div class=" align-items-center">
											<security:authorize access="isAuthenticated()">
												<form:form method="POST" action="/pet/buy?id=${pet.id}"
													modelAttribute="cart_item">
													<form:input maxlength="5" size="5" type="number" min="1"
														value="0" step="1" path="amount" />
													<button type="submit" class="btn btn-success">Mua
														ngay</button>
												</form:form>
												<security:authorize access="hasRole('ROLE_ADMIN')">
													<input class="btn peach-gradient" style="height: 45px"
														type="button"
														onclick="location.href='/pet/edit/${pet.id}'"
														value="Sửa sản phẩm" />
												</security:authorize>
											</security:authorize>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<c:set var="page" value="${pet_list}" scope="request" />
				<c:set var="mainUrl" value="pet/list" scope="request" />
			</div>

		</div>

	</div>

	<jsp:include page="shared/footer.jsp" />
</body>
</html>
