<%--
  Created by IntelliJ IDEA.
  User: Komputer
  Date: 03.01.2019
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trang chủ</title>
</head>
<body>
<jsp:include page="shared/header.jsp"/>
<!DOCTYPE html>
<html lang="en">

<head>
    <style type="text/css">
        html,
        body,
        header,
        .view {
            height: 100%;
        }

        @media (max-width: 740px) {
            html,
            body,
            header,
            .view {
                height: 1000px;
            }
        }

        @media (min-width: 800px) and (max-width: 850px) {
            html,
            body,
            header,
            .view {
                height: 650px;
            }
        }

        @media (min-width: 800px) and (max-width: 850px) {
            .navbar:not(.top-nav-collapse) {
                background: #1C2331 !important;
            }
        }
    </style>
</head>
<body>
<div class="view full-page-intro"
     style="background-image: url('/images/background.jpg'); background-repeat: no-repeat; background-size: cover;">
    <div class="mask rgba-black-light d-flex justify-content-center align-items-center">
        <div class="container">
            <div class="row wow fadeIn">
                <div class="col-md-6 mb-4 white-text text-center text-md-left">
                    <h1 class="display-4 font-weight-bold">Cửa hàng thú cưng Kakadu</h1>
                    <hr class="hr-light">
                    <p>
                        <strong>Có lẽ là cửa hàng thú cưng rẻ nhất trên web</strong>
                    </p>

                    <p class="mb-4 d-none d-md-block">
                        <strong>
                        Trong cửa hàng của chúng tôi, bạn sẽ tìm thấy rất nhiều động vật, thức ăn và phụ kiện cho động vật. Thưởng thức mua sắm của bạn.
                  </strong>
                    </p>

                    <a target="_blank" href="/registration"
                       class="btn btn-indigo btn-lg">Tao tài khoản mới ngay !
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>


<jsp:include page="shared/footer.jsp"/>
</body>
</html>
