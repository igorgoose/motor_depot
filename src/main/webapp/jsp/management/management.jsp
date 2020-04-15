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
        <form class="toolbar-top-form flex-sb flex-w" action="${pageContext.request.contextPath}/controller" method="post">
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
        <form class="toolbar-top-form flex-sb flex-w" action="${pageContext.request.contextPath}/controller" method="post">
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
        </div>
    </div>
</div>

</body>
</html>
