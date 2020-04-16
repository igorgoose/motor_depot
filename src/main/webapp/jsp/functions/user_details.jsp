<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 07.04.2020
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale" var="bundle"/>
<html>
<head>
    <title><fmt:message bundle="${bundle}" key="home.title"/></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/icons/favicon.ico"/>
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
                        <c:if test="${role_id == 1}">
                            <button name="address" value="MANAGEMENT">
                                <fmt:message bundle="${bundle}" key="button.management"/>
                            </button>
                        </c:if>
                        <button name="command" value="view_profile">
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
    <div class="ultimate-container">
        <c:if test="${role_id == 1 && requestScope.user != null && sessionScope.user.id != requestScope.user.id}">
            <form class="toolbar-top-form flex-sb flex-w" action="${pageContext.request.contextPath}/controller"
                  method="post">
                <div class="menu-bar">
                    <div class="btn-wrapper">
                        <button class="menu-bar-button-dangerous" name="command" value="block">
                            <fmt:message bundle="${bundle}" key="button.block"/>
                        </button>
                    </div>
                    <div class="btn-wrapper">
                        <button class="menu-bar-button-dangerous" name="command" value="delete">
                            <fmt:message bundle="${bundle}" key="button.delete"/>
                        </button>
                    </div>
                </div>
            </form>
        </c:if>
        <div class="content-container">
            <div class="content-unit-container">
                <label class="details-label">
                    ID: ${user.id}
                </label>
            </div>
            <div class="content-unit-container">
                <label class="details-label">
                    Username: ${user.username}
                </label>
            </div>
            <div class="content-unit-container">
                <label class="details-label">
                    Email: ${user.email}
                </label>
            </div>
            <div class="content-unit-container">
                <label class="details-label">
                    Role: ${user.role}
                </label>
            </div>
            <div class="content-unit-container">
                <label class="details-label">
                    Blocked: ${user.blocked}
                </label>
            </div>
            <c:if test="${not empty requests}">
                <div class="content-unit-container pre-scrollable black-border ">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Request ID</th>
                            <th>Departure location</th>
                            <th>Arrival location</th>
                            <th>Passengers quantity</th>
                            <th>Load Volume</th>
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
                                        <input type="hidden" name="user_id" value="${user.id}"/>
                                        <button class="table-btn-blue" name="command" value="users_more">
                                            <fmt:message bundle="${bundle}" key="button.details"/>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <c:if test="${not empty orders}">
                <div class="content-unit-container pre-scrollable black-border">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Departure location</th>
                            <th>Arrival location</th>
                            <th>Driver</th>
                            <th>Car</th>
                            <th>Is Complete</th>
                            <c:if test="${role_id == 1}">
                                <th></th>
                            </c:if>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="order" items="${orders}">
                            <tr>
                                <td>${order.id}</td>
                                <td>${order.departureLocation}</td>
                                <td>${order.arrivalLocation}</td>
                                <td>${order.driver.username}</td>
                                <td>${order.car.carName}</td>
                                <td>${order.complete}</td>
                                <c:if test="${role_id == 1}">
                                    <td class="p-b-5 p-t-5 p-r-5 p-l-5">
                                        <form action="${pageContext.request.contextPath}/controller"
                                              method="post">
                                            <input type="hidden" name="user_id" value="${user.id}"/>
                                            <button class="table-btn-blue" name="command" value="users_more">
                                                <fmt:message bundle="${bundle}" key="button.details"/>
                                            </button>
                                        </form>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
