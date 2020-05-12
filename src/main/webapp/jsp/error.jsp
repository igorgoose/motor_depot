<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale" var="bundle"/>
<html>
<head>
    <title><fmt:message bundle="${bundle}" key="error.title"/></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/icons/car-icon.png"/>
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
    <div class="container-login100">
        <div class="wrap-login100 p-t-50 p-b-90" style="width: 500px;">
            <span class="login100-form-title p-b-51">
                <fmt:message bundle="${bundle}" key="oops.title"/>
            </span>
            <c:if test="${pageContext.errorData.statusCode != null}">
                <span class="login100-form-title" style="font-size: 18px">
                        <fmt:message bundle="${bundle}" key="error_code.label"/> ${pageContext.errorData.statusCode}
                </span>
            </c:if>
            <c:if test="${message != null}">
                <span class="login100-form-title" style="font-size: 18px">
                       <fmt:message bundle="${bundle}" key="error_description.label"/> ${message}
                </span>
            </c:if>
            <form class="login100-form validate-form flex-sb flex-w" action="${pageContext.request.contextPath}/controller" method="post">
                <div class="container-login100-form-btn m-t-17">
                    <input type="hidden" name="address" value="HOME"/>
                    <button class="login100-form-btn" name="command" value="redirect">
                        <fmt:message bundle="${bundle}" key="button.home"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>


</body>
</html>