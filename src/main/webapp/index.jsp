<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale" var="bundle"/>
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <!--===============================================================================================-->
</head>
<body>

<div class="limiter">
    <div class="toolbar-top-container">
        <div class="toolbar-top">
            <form class="toolbar-top-form flex-sb flex-w" action="controller" method="post">
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
                            <button name="command" value="redirect">
                                <fmt:message bundle="${bundle}" key="button.authorize"/>
                            </button>
                            <input class="invisible" name="address" value="AUTHORIZE"/>
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
    </div>
    <div class="container-login100">
        <div class="wrap-login100 p-t-50 p-b-90">
            <form class="login100-form validate-form flex-sb flex-w" action="controller" method="post">
                <span class="login100-form-title p-b-51">
                    <fmt:message bundle="${bundle}" key="welcome"/>
                </span>
            </form>
        </div>
    </div>
</div>


</body>
</html>
