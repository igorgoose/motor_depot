<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale" var="bundle"/>
<html>
<head>
    <title><fmt:message bundle="${bundle}" key="signup.title"/></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/util.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/layout.css">
    <!--===============================================================================================-->
</head>
<body>

<div class="limiter">
    <div class="toolbar-top-container">
        <div class="toolbar-top">
            <form class="toolbar-top-form flex-sb flex-w" action="${pageContext.request.contextPath}/controller"
                  method="post">
                <div class="dropdown toolbar-top-btn">
                    <button class="dropbtn" disabled>
                        <fmt:message bundle="${bundle}" key="button.language"/>
                    </button>
                    <div class="dropdown-content">
                        <button name="language" value="ru">Русский</button>
                        <button name="language" value="en">English</button>
                    </div>
                </div>
                <div class="dropdown toolbar-top-btn">
                    <c:if test="${role_id == 4}">
                        <button class="dropbtn" disabled>
                            <fmt:message bundle="${bundle}" key="button.role.guest"/>
                        </button>
                        <div class="dropdown-content">
                            <button name="address" value="HOME">
                                <fmt:message bundle="${bundle}" key="button.home"/>
                            </button>
                            <button name="address" value="AUTHORIZE">
                                <fmt:message bundle="${bundle}" key="button.authorize"/>
                            </button>
                        </div>
                    </c:if>
                    <c:if test="${role_id < 4}">
                        <button class="dropbtn" disabled>
                                ${username}[
                            <c:if test="${role_id == 3}">
                                <fmt:message bundle="${bundle}" key="button.role.user"/>
                            </c:if>
                            <c:if test="${role_id == 2}">
                                <fmt:message bundle="${bundle}" key="button.role.driver"/>
                            </c:if>
                            <c:if test="${role_id == 1}">
                                <fmt:message bundle="${bundle}" key="button.role.admin"/>
                            </c:if>
                            ]
                        </button>
                        <div class="dropdown-content">
                            <button name="address" value="HOME">
                                <fmt:message bundle="${bundle}" key="button.create_request"/>
                            </button>
                            <c:if test="${role_id < 3}">
                                <button name="address" value="MANAGEMENT">
                                    <fmt:message bundle="${bundle}" key="button.management"/>
                                </button>
                            </c:if>
                            <button name="address" value="USER_DETAILS">
                                <fmt:message bundle="${bundle}" key="button.profile"/>
                            </button>
                            <button name="command" value="log_out">
                                <fmt:message bundle="${bundle}" key="button.logout"/>
                            </button>
                        </div>
                    </c:if>
                </div>
            </form>
        </div>
    </div>
    <div class="container-login100">
        <div class="wrap-login100 p-t-50 p-b-90">
            <form class="login100-form validate-form flex-sb flex-w"
                  action="${pageContext.request.contextPath}/controller" method="post">

					<span class="login100-form-title p-b-51">
						<fmt:message bundle="${bundle}" key="finish_order.title"/>
					</span>
                <div class="content-unit-container">
                    <div class="content-unit-container">
                        <label class="details-label">
                            Order ID: ${order.id}
                        </label>
                    </div>
                    <div class="content-unit-container">
                        <label class="details-label">
                            Customer's name: ${order.user.username}
                        </label>
                    </div>
                    <div class="content-unit-container">
                        <label class="details-label">
                            Car: ${order.car.carName}
                        </label>
                    </div>
                    <div class="content-unit-container">
                        <label class="details-label">
                            From: ${order.departureLocation}
                        </label>
                    </div>
                    <div class="content-unit-container">
                        <label class="details-label">
                            To: ${order.arrivalLocation}
                        </label>
                    </div>
                </div>

                <div class="container-login100-form-btn m-t-17">
                    <label class="input-label p-l-5">
                        <fmt:message bundle="${bundle}" key="finish_order.car_state"/>
                    </label>
                    <select name="car_state" class="custom-select my-select">
                        <option value="READY">
                            <fmt:message bundle="${bundle}" key="finish_order.option.ready"/>
                        </option>
                        <option value="BROKEN">
                            <fmt:message bundle="${bundle}" key="finish_order.option.broken"/>
                        </option>
                    </select>
                </div>


                <input type="hidden" name="order_id" value="${order.id}"/>
                <div class="container-login100-form-btn m-t-17">
                    <button class="table-btn-green" name="command" value="finish_order">
                        <fmt:message bundle="${bundle}" key="button.finish_order"/>
                    </button>
                </div>
                <div class="container-login100-form-btn m-t-17">
                    <button class="table-btn-blue" name="command" value="view_current_order">
                        <fmt:message bundle="${bundle}" key="button.back"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>


</body>
</html>