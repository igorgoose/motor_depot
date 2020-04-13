<%--
  Created by IntelliJ IDEA.
  User: igorg
  Date: 30.03.2020
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale" var="bundle"/>
<html>
<head>
    <title><fmt:message bundle="${bundle}" key="profile.label"/></title>
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
    <!--===============================================================================================-->
</head>
<body>

<div class="limiter">
    <div class="toolbar-top-container">
        <div class="toolbar-top">
            <form class="toolbar-top-form flex-sb flex-w" action="${pageContext.request.contextPath}/controller" method="post">
                <div class="dropdown toolbar-top-btn">
                    <button class="dropbtn" disabled><fmt:message bundle="${bundle}" key="button.language"/></button>
                    <div class="dropdown-content">
                        <button name="language" value="ru">Русский</button>
                        <button name="language" value="en">English</button>
                    </div>
                </div>
                <div class="dropdown toolbar-top-btn">
                    <button class="dropbtn" disabled>
                        ${username}(
                        <c:if test="${role == 3}">
                            <fmt:message bundle="${bundle}" key="button.role.user"/>
                        </c:if>
                        <c:if test="${role == 2}">
                            <fmt:message bundle="${bundle}" key="button.role.driver"/>
                        </c:if>
                        <c:if test="${role == 1}">
                            <fmt:message bundle="${bundle}" key="button.role.admin"/>
                        </c:if>
                        )
                    </button>
                    <div class="dropdown-content">
                        <button name="command" value="log_out">log out</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="container-login100">
        <div class="wrap-content-unit p-t-10 p-b-10">
            <table class="profile-table">
                <tr>
                    <th>Request ID</th>
                    <th>Departure location</th>
                    <th>Arrival location</th>
                    <th>Departure time</th>
                    <th>Arrival time</th>
                    <th>Passengers quantity</th>
                    <th>Load(kg)</th>
                </tr>
                <c:forEach var="request" items="${requests}">
                    <tr>
                        <td>${request.id}</td>
                        <td>${request.route.departureLocation}</td>
                        <td>${request.route.arrivalLocation}</td>
                        <td>${request.route.departureTime}</td>
                        <td>${request.route.arrivalTime}</td>
                        <td>${request.passengersQuantity}</td>
                        <td>${request.load}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="wrap-content-unit p-t-10 p-b-10">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <button class="login100-form-btn" name="address" value="HOME">
                    <fmt:message bundle="${bundle}" key="button.home"/>
                </button>
<%--                <input class="invisible" name="address" value="HOME"/>--%>
            </form>
        </div>
    </div>
</div>

</body>
</html>
