<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale" var="bundle" />
<html>
<head>
    <title><fmt:message bundle="${bundle}" key="signup.title"/></title>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/layout.css">
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
                    <c:if test="${role_id == 4}">
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
                        </div>
                    </c:if>
                    <c:if test="${role_id < 4}">
                        <c:if test="${role_id == 3}">
                            <button class="dropbtn" disabled>
                                    ${username}[
                                <fmt:message bundle="${bundle}" key="button.role.user"/>
                                ]
                            </button>
                            <div class="dropdown-content">
                                <form action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="address" value="USER_DETAILS">
                                    <button name="command" value="redirect">
                                        <fmt:message bundle="${bundle}" key="button.profile"/>
                                    </button>
                                </form>
                                <button name="command" value="log_out">
                                    <fmt:message bundle="${bundle}" key="button.logout"/>
                                </button>
                            </div>
                        </c:if>
                        <c:if test="${role_id == 2}">
                            <button class="dropbtn" disabled>
                                    ${username}[
                                <fmt:message bundle="${bundle}" key="button.role.driver"/>
                                ]
                            </button>
                            <div class="dropdown-content">
                                <form action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="address" value="USER_DETAILS">
                                    <button name="command" value="redirect">
                                        <fmt:message bundle="${bundle}" key="button.profile"/>
                                    </button>
                                </form>
                                <form action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="address" value="MANAGEMENT">
                                    <button name="command" value="redirect">
                                        <fmt:message bundle="${bundle}" key="button.management"/>
                                    </button>
                                </form>
                                <button name="command" value="log_out">
                                    <fmt:message bundle="${bundle}" key="button.logout"/>
                                </button>
                            </div>
                        </c:if>
                        <c:if test="${role_id == 1}">
                            <button class="dropbtn" disabled>
                                    ${username}[
                                <fmt:message bundle="${bundle}" key="button.role.admin"/>
                                ]
                            </button>
                            <div class="dropdown-content">
                                <form action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="address" value="USER_DETAILS">
                                    <button name="command" value="redirect">
                                        <fmt:message bundle="${bundle}" key="button.profile"/>
                                    </button>
                                </form>
                                <form action="${pageContext.request.contextPath}/controller">
                                    <input type="hidden" name="address" value="MANAGEMENT">
                                    <button name="command" value="redirect">
                                        <fmt:message bundle="${bundle}" key="button.management"/>
                                    </button>
                                </form>
                                <button name="command" value="log_out">
                                    <fmt:message bundle="${bundle}" key="button.logout"/>
                                </button>
                            </div>
                        </c:if>
                    </c:if>
                </div>
            </form>
        </div>
    </div>
    <div class="container-login100">
        <div class="wrap-login100 p-t-50 p-b-90">
            <div class="content-unit-container">
                <div class="content-unit-container">
                    <label class="details-label">
                        Customer's username: ${request.user.username}
                    </label>
                </div>
                <div class="content-unit-container">
                    <label class="details-label">
                        Car: ${car.carName}
                    </label>
                </div>
                <div class="content-unit-container">
                    <label class="details-label">
                        Driver: ${car.driver.username}
                    </label>
                </div>
                <div class="content-unit-container">
                    <label class="details-label">
                        From: ${request.departureLocation}
                    </label>
                </div>
                <div class="content-unit-container">
                    <label class="details-label">
                        To: ${request.arrivalLocation}
                    </label>
                </div>
            </div>
            <form class="login100-form validate-form flex-sb flex-w" action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="request_id" value="${request.id}"/>
                <input type="hidden" name="car_id" value="${car.id}"/>
                <div class="container-login100-form-btn m-t-17">
                    <button class="table-btn-green" name="command" value="submit_order">
                        <fmt:message bundle="${bundle}" key="button.submit_order"/>
                    </button>
                </div>
                <div class="container-login100-form-btn m-t-17">
                    <button class="table-btn-blue" name="command" value="verify_request">
                        <fmt:message bundle="${bundle}" key="button.back"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>


</body>
</html>