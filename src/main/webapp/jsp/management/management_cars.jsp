<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 07.04.2020
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale" var="bundle"/>
<html>
<head>
    <title><fmt:message bundle="${bundle}" key="management.title"/></title>
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
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="address" value="HOME"/>
                            <button name="command" value="redirect">
                                <fmt:message bundle="${bundle}" key="button.create_request"/>
                            </button>
                        </form>
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="address" value="AUTHORIZE"/>
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
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="address" value="HOME"/>
                            <button name="command" value="redirect">
                                <fmt:message bundle="${bundle}" key="button.create_request"/>
                            </button>
                        </form>
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <button name="command" value="view_profile">
                                <fmt:message bundle="${bundle}" key="button.profile"/>
                            </button>
                            <button name="command" value="log_out">
                                <fmt:message bundle="${bundle}" key="button.logout"/>
                            </button>
                        </form>
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
        <c:if test="${role.id == 1}">
            <form class="toolbar-top-form flex-sb flex-w" action="${pageContext.request.contextPath}/controller"
                  method="post">
                <div class="menu-bar">
                    <div class="btn-wrapper">
                        <button class="menu-bar-button" name="command" value="view_requests">
                            <fmt:message bundle="${bundle}" key="button.requests"/>
                        </button>
                    </div>
                    <div class="btn-wrapper">
                        <button class="menu-bar-button" name="command" value="view_users">
                            <fmt:message bundle="${bundle}" key="button.users"/>
                        </button>
                    </div>
                    <div class="btn-wrapper">
                        <button class="menu-bar-button-pushed" name="command" value="view_cars">
                            <fmt:message bundle="${bundle}" key="button.cars"/>
                        </button>
                    </div>
                    <div class="btn-wrapper">
                        <button class="menu-bar-button" name="command" value="view_orders">
                            <fmt:message bundle="${bundle}" key="button.orders"/>
                        </button>
                    </div>
                </div>
            </form>
        </c:if>
        <c:if test="${role.id == 2}">
            <form class="toolbar-top-form flex-sb flex-w" action="${pageContext.request.contextPath}/controller"
                  method="post">
                <div class="menu-bar">
                    <div class="btn-wrapper">
                        <button class="menu-bar-button" name="command" value="view_current_order">
                            <fmt:message bundle="${bundle}" key="button.current_order"/>
                        </button>
                    </div>
                    <div class="btn-wrapper">
                        <button class="menu-bar-button" name="command" value="view_completed_orders">
                            <fmt:message bundle="${bundle}" key="button.completed_orders"/>
                        </button>
                    </div>
                    <div class="btn-wrapper">
                        <button class="menu-bar-button" name="command" value="view_my_cars">
                            <fmt:message bundle="${bundle}" key="button.my_cars"/>
                        </button>
                    </div>
                </div>
            </form>
        </c:if>
        <div class="content-container">
            <c:if test="${not empty cars}">
                <div class="content-unit-container pre-scrollable full-height">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Car ID</th>
                            <th>Car Model</th>
                            <th>Reg. Number</th>
                            <c:if test="${role.id != 2}">
                                <th>Driver</th>
                            </c:if>
                            <th>Load Capacity</th>
                            <th>Passenger Capacity</th>
                            <th>State</th>
                            <c:if test="${role.id == 2}">
                                <th></th>
                            </c:if>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="car" items="${cars}">
                            <tr>
                                <td>${car.id}</td>
                                <td>${car.carName}</td>
                                <td>${car.registrationNumber}</td>
                                <c:if test="${role.id != 2}">
                                    <td>${car.driver.username}</td>
                                </c:if>
                                <td>${car.loadCapacity}</td>
                                <td>${car.passengerCapacity}</td>
                                <td>${car.carStatus}</td>
                                <c:if test="${role.id == 2 && car.carStatus.id == 1}">
                                    <td>
                                        <form action="${pageContext.request.contextPath}/controller"
                                              method="post">
                                            <input type="hidden" name="car_id" value="${car.id}"/>
                                            <button class="table-btn-blue" name="command" value="set_car_state_broken">
                                                <fmt:message bundle="${bundle}" key="button.car_is_broken"/>
                                            </button>
                                        </form>
                                    </td>
                                </c:if>
                                <c:if test="${role.id == 2 && car.carStatus.id == 3}">
                                    <td>
                                        <form action="${pageContext.request.contextPath}/controller"
                                              method="post">
                                            <input type="hidden" name="car_id" value="${car.id}"/>
                                            <button class="table-btn-blue" name="command" value="set_car_state_ready">
                                                <fmt:message bundle="${bundle}" key="button.car_is_ready"/>
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
            <c:if test="${empty cars}">
                <div class="content-unit-container">
                    <label class="details-label">
                        <fmt:message bundle="${bundle}" key="management.no_cars"/>
                    </label>
                </div>
            </c:if>
        </div>
    </div>
    <div class="copyright">
        <ctg:copyright-tag/>
    </div>
</div>

</body>
</html>
