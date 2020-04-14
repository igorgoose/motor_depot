<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 07.04.2020
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <c:if test="${role == 4}">
                    <button class="dropbtn" disabled>
                        <fmt:message bundle="${bundle}" key="button.role.guest"/>
                    </button>
                    <div class="dropdown-content">
                        <button name="address" value="AUTHORIZE">
                            <fmt:message bundle="${bundle}" key="button.authorize"/>
                        </button>
                    </div>
                </c:if>
                <c:if test="${role < 4}">
                    <c:if test="${role == 3}">
                        <button class="dropbtn" disabled>
                            <fmt:message bundle="${bundle}" key="button.role.user"/>
                        </button>
                    </c:if>
                    <c:if test="${role == 2}">
                        <button class="dropbtn" disabled>
                            <fmt:message bundle="${bundle}" key="button.role.driver"/>
                        </button>
                    </c:if>
                    <c:if test="${role == 1}">
                        <button class="dropbtn" disabled>
                            <fmt:message bundle="${bundle}" key="button.role.admin"/>
                        </button>
                    </c:if>
                    <div class="dropdown-content">
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
                    <button class="menu-bar-button" name="command" value="view_cars">
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
        <div class="content-container">
            <div class="content-unit-container">
                <table class="profile-table">
                    <thead>
                    <tr>
                        <th>Car ID</th>
                        <th>Car Model</th>
                        <th>Reg. Number</th>
                        <th>Driver</th>
                        <th>Load Capacity</th>
                        <th>Passenger Capacity</th>
                        <th>State</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="car" items="${cars}">
                            <tr>
                                <td>${car.id}</td>
                                <td>${car.carName}</td>
                                <td>${car.registrationNumber}</td>
                                <td>${car.driver.username}</td>
                                <td>${car.loadCapacity}</td>
                                <td>${car.passengerCapacity}</td>
                                <td>${car.carStatus}</td>
                                <td class="p-b-5 p-t-5 p-r-5 p-l-5">
                                    <form action="${pageContext.request.contextPath}/controller"
                                          method="post">
                                    <input type="hidden" name="user_id" value="${user.id}"/>
                                    <button class="table-btn" name="command" value="users_more">
                                        <fmt:message bundle="${bundle}" key="button.details"/>
                                    </button>
                                    </form>
                                </td>
                            </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
