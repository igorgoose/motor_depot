<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale" var="bundle"/>
<html>
<head>
    <title>
        ${user.username}'s <fmt:message bundle="${bundle}" key="button.profile"/>
    </title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/icons/car-icon.png"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/bootstrap-4.4.1-dist/css/bootstrap.min.css">
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
</head>
<body>
<div class="limiter">
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
                <c:if test="${role.id == 4}">
                    <button class="dropbtn" disabled>
                        <fmt:message bundle="${bundle}" key="button.role.guest"/>
                    </button>
                    <div class="dropdown-content">
                        <form action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="address" value="HOME">
                            <button name="command" value="redirect">
                                <fmt:message bundle="${bundle}" key="button.create_request"/>
                            </button>
                        </form>
                        <form action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="address" value="AUTHORIZE">
                            <button name="command" value="redirect">
                                <fmt:message bundle="${bundle}" key="button.authorize"/>
                            </button>
                        </form>
                    </div>
                </c:if>
                <c:if test="${role.id < 4}">
                    <button class="dropbtn" disabled>
                            ${username}[
                        <c:if test="${role.id == 3}">
                            <fmt:message bundle="${bundle}" key="button.role.user"/>
                        </c:if>
                        <c:if test="${role.id == 2}">
                            <fmt:message bundle="${bundle}" key="button.role.driver"/>
                        </c:if>
                        <c:if test="${role.id == 1}">
                            <fmt:message bundle="${bundle}" key="button.role.admin"/>
                        </c:if>
                        ]
                    </button>
                    <div class="dropdown-content">
                        <form action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="address" value="HOME">
                            <button name="command" value="redirect">
                                <fmt:message bundle="${bundle}" key="button.create_request"/>
                            </button>
                        </form>
                        <c:if test="${role.id < 3}">
                            <form action="${pageContext.request.contextPath}/controller">
                                <input type="hidden" name="address" value="MANAGEMENT">
                                <button name="command" value="redirect">
                                    <fmt:message bundle="${bundle}" key="button.management"/>
                                </button>
                            </form>
                        </c:if>
                        <form action="${pageContext.request.contextPath}/controller">
                            <button name="command" value="view_profile">
                                <fmt:message bundle="${bundle}" key="button.profile"/>
                            </button>
                        </form>
                        <button name="command" value="log_out">
                            <fmt:message bundle="${bundle}" key="button.logout"/>
                        </button>
                    </div>
                </c:if>
            </div>
        </form>
        <c:if test="${message != null}">
                    <span class="login100-form-title" style="font-size: 14px; margin-top: 5px">
                            ${message}
                    </span>
        </c:if>
    </div>
    <div class="ultimate-container">
        <c:if test="${role.id == 1 && ((requestScope.user != null && sessionScope.user.id != requestScope.user.id) ||
         (that_user != null && that_user.id != sessionScope.user.id))}">
            <form class="toolbar-top-form flex-sb flex-w" action="${pageContext.request.contextPath}/controller"
                  method="post">
                <div class="menu-bar">
                    <div class="btn-wrapper">
                        <button class="menu-bar-button" name="command" value="user_details">
                            <fmt:message bundle="${bundle}" key="button.user_info"/>
                        </button>
                    </div>
                    <div class="btn-wrapper">
                        <button class="menu-bar-button" name="command" value="view_user_requests">
                            <fmt:message bundle="${bundle}" key="button.users_requests"/>
                        </button>
                    </div>
                    <div class="btn-wrapper">
                        <button class="menu-bar-button" name="command" value="view_user_orders">
                            <fmt:message bundle="${bundle}" key="button.users_orders"/>
                        </button>
                    </div>
                    <div class="btn-wrapper">
                        <c:if test="${that_user != null}">
                            <input type="hidden" name="user_id" value="${that_user.id}"/>
                            <c:if test="${that_user.status.id != 3}">
                                <button class="menu-bar-button-dangerous" name="command" value="block">
                                    <fmt:message bundle="${bundle}" key="button.block"/>
                                </button>
                            </c:if>
                            <c:if test="${that_user.status.id == 3}">
                                <button class="menu-bar-button-green" name="command" value="unblock">
                                    <fmt:message bundle="${bundle}" key="button.unblock"/>
                                </button>
                            </c:if>
                        </c:if>
                        <c:if test="${requestScope.user != null}">
                            <input type="hidden" name="user_id" value="${requestScope.user.id}"/>
                            <c:if test="${requestScope.user.status.id == 1}">
                                <button class="menu-bar-button-dangerous" name="command" value="block">
                                    <fmt:message bundle="${bundle}" key="button.block"/>
                                </button>
                            </c:if>
                            <c:if test="${requestScope.user.status.id == 2}">
                                <button class="menu-bar-button-disabled" name="command" value="block" disabled>
                                    <fmt:message bundle="${bundle}" key="button.block"/>
                                </button>
                            </c:if>
                            <c:if test="${requestScope.user.status.id == 3}">
                                <button class="menu-bar-button-green" name="command" value="unblock">
                                    <fmt:message bundle="${bundle}" key="button.unblock"/>
                                </button>
                            </c:if>
                        </c:if>
                    </div>
                </div>
            </form>
        </c:if>
        <c:if test="${(requestScope.user == null && that_user == null) || sessionScope.user.id == requestScope.user.id || sessionScope.user.id == that_user.id}">
            <form class="toolbar-top-form flex-sb flex-w" action="${pageContext.request.contextPath}/controller"
                  method="post">
                <input type="hidden" name="user_id" value="${user.id}"/>
                <div class="menu-bar">
                    <div class="btn-wrapper">
                        <button class="menu-bar-button" name="command" value="view_profile">
                            <fmt:message bundle="${bundle}" key="button.profile"/>
                        </button>
                    </div>
                    <div class="btn-wrapper">
                        <button class="menu-bar-button" name="command" value="view_user_requests">
                            <fmt:message bundle="${bundle}" key="button.my_requests"/>
                        </button>
                    </div>
                    <div class="btn-wrapper">
                        <button class="menu-bar-button" name="command" value="view_user_orders">
                            <fmt:message bundle="${bundle}" key="button.my_orders"/>
                        </button>
                    </div>
                </div>
            </form>
        </c:if>
        <div class="content-container">
            <c:if test="${requestScope.orders == null && requestScope.requests == null}">
                <div class="content-unit-container">
                    <label class="details-label">
                        ID: <c:if test="${that_user != null}">
                        ${that_user.id}
                    </c:if>
                        <c:if test="${that_user == null}">
                            ${user.id}
                        </c:if>
                    </label>
                </div>
                <div class="content-unit-container">
                    <label class="details-label">
                        <fmt:message bundle="${bundle}" key="th.username"/>: <c:if test="${that_user != null}">
                        ${that_user.username}
                    </c:if>
                        <c:if test="${that_user == null}">
                            ${user.username}
                        </c:if>
                    </label>
                </div>
                <div class="content-unit-container">
                    <label class="details-label">
                        <fmt:message bundle="${bundle}" key="th.email"/>: <c:if test="${that_user != null}">
                        ${that_user.email}
                    </c:if>
                        <c:if test="${that_user == null}">
                            ${user.email}
                        </c:if>
                    </label>
                </div>
                <div class="content-unit-container">
                    <label class="details-label">
                        <fmt:message bundle="${bundle}" key="th.email"/>: <c:if test="${that_user != null}">
                        ${that_user.role}
                    </c:if>
                        <c:if test="${that_user == null}">
                            ${user.role}
                        </c:if>
                    </label>
                </div>
                <div class="content-unit-container">
                    <label class="details-label">
                        <fmt:message bundle="${bundle}" key="th.status"/>: <c:if test="${that_user != null}">
                        ${that_user.status}
                    </c:if>
                        <c:if test="${that_user == null}">
                            ${user.status}
                        </c:if>
                    </label>
                </div>
            </c:if>
            <c:if test="${requests != null}">
                <c:if test="${empty requests}">
                    <div class="content-unit-container">
                        <label class="details-label">
                            <fmt:message bundle="${bundle}" key="user_details.no_requests"/>
                        </label>
                    </div>
                </c:if>
                <c:if test="${not empty requests}">
                    <div class="content-unit-container pre-scrollable black-border full-height">
                        <table class="table">
                            <thead>
                            <tr>
                                <th><fmt:message bundle="${bundle}" key="th.request_id"/></th>
                                <th><fmt:message bundle="${bundle}" key="th.departure_location"/></th>
                                <th><fmt:message bundle="${bundle}" key="th.arrival_location"/></th>
                                <th><fmt:message bundle="${bundle}" key="th.passenger_quantity"/></th>
                                <th><fmt:message bundle="${bundle}" key="th.load_volume"/></th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="request" items="${requests}">
                                <tr>
                                    <td>${request.id}</td>
                                    <td>${request.departureLocation}</td>
                                    <td>${request.arrivalLocation}</td>
                                    <td>${request.passengersQuantity}</td>
                                    <td>${request.load}</td>
                                    <td class="p-b-5 p-t-5 p-r-5 p-l-5">
                                        <form action="${pageContext.request.contextPath}/controller"
                                              method="post">
                                            <input type="hidden" name="request_id" value="${request.id}"/>
                                            <button class="table-btn-green" name="command" value="verify_request">
                                                <fmt:message bundle="${bundle}" key="button.create_order"/>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </c:if>
            <c:if test="${orders != null}">
                <c:if test="${empty orders}">
                    <div class="content-unit-container">
                        <label class="details-label">
                            <fmt:message bundle="${bundle}" key="user_details.no_orders"/>
                        </label>
                    </div>
                </c:if>
                <c:if test="${not empty orders}">
                    <div class="content-unit-container pre-scrollable black-border  full-height">
                        <table class="table">
                            <thead>
                            <tr>
                                <th><fmt:message bundle="${bundle}" key="th.order_id"/></th>
                                <th><fmt:message bundle="${bundle}" key="th.departure_location"/></th>
                                <th><fmt:message bundle="${bundle}" key="th.arrival_location"/></th>
                                <th><fmt:message bundle="${bundle}" key="th.driver"/></th>
                                <th><fmt:message bundle="${bundle}" key="th.car"/></th>
                                <th><fmt:message bundle="${bundle}" key="th.is_complete"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="order" items="${orders}">
                                <tr>
                                    <td>${order.id}</td>
                                    <td>${order.departureLocation}</td>
                                    <td>${order.arrivalLocation}</td>
                                    <td>${order.car.driver.username}</td>
                                    <td>${order.car.carName}</td>
                                    <td>${order.complete}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </c:if>
        </div>
    </div>
    <div class="copyright">
        <ctg:copyright-tag/>
    </div>
</div>
</body>
</html>
