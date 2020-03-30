<%--
  Created by IntelliJ IDEA.
  User: igorg
  Date: 30.03.2020
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale" var="bundle" />
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/util.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <!--===============================================================================================-->
</head>
<body>

<div class="limiter">
    <div class="toolbar-top-container">
        <div class="toolbar-top">
            <form class="toolbar-top-form flex-sb flex-w" action="controller" method="post">
                <div class="dropdown toolbar-top-btn">
                    <button class="dropbtn" disabled><fmt:message bundle="${bundle}" key="button.language"/></button>
                    <div class="dropdown-content">
                        <button name="language" value="ru">Русский</button>
                        <button name="language" value="en">English</button>
                    </div>
                </div>
                <div class="dropdown toolbar-top-btn">
                    <button class="dropbtn" disabled><fmt:message bundle="${bundle}" key="button.role.guest"/></button>
                </div>
            </form>
        </div>
    </div>
    <div class="container-login100">


    </div>
</div>

</body>
</html>
