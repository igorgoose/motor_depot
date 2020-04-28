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
                        <form action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="address" value="HOME">
                            <button name="command" value="redirect">
                                <fmt:message bundle="${bundle}" key="button.create_request"/>
                            </button>
                        </form>
                        <c:if test="${role_id == 1}">
                            <form action="${pageContext.request.contextPath}/controller">
                                <input type="hidden" name="address" value="MANAGEMENT">
                                <button name="command" value="redirect">
                                    <fmt:message bundle="${bundle}" key="button.management"/>
                                </button>
                            </form>
                        </c:if>
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
            </div>
        </form>
    </div>
    <div class="ultimate-container">
        <c:if test="${role_id == 1}">
            <form class="toolbar-top-form flex-sb flex-w" action="${pageContext.request.contextPath}/controller"
                  method="post">
                <div class="menu-bar">
                    <div class="btn-wrapper">
                        <button class="menu-bar-button-dangerous" name="command" value="reject_request">
                            <fmt:message bundle="${bundle}" key="button.reject_request"/>
                        </button>
                    </div>
                </div>
            </form>
        </c:if>
        <div class="content-container">
            <div class="content-unit-container">
                <label class="details-label">
                    Request ID: ${request.id}
                </label>
            </div>
            <div class="content-unit-container">
                <label class="details-label">
                    Customer's username: ${request.user.username}
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
            <div class="content-unit-container">
                <label class="details-label">
                    Seats required: ${request.passengersQuantity}
                </label>
            </div>
            <div class="content-unit-container">
                <label class="details-label">
                    Room for baggage required: ${request.load}
                </label>
            </div>
            <c:if test="${not empty cars}">
                <div class="p-b-20 content-unit-container pre-scrollable black-border">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Car ID</th>
                            <th>Car Model</th>
                            <th>Reg. Number</th>
                            <th>Driver</th>
                            <th>Load Capacity</th>
                            <th>Passenger Capacity</th>
                            <th>State</th>
                            <th></th>
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
                                        <input type="hidden" name="request_id" value="${request.id}"/>
                                        <input type="hidden" name="car_id" value="${car.id}"/>
                                        <button class="table-btn-green" name="command" value="assign_car">
                                            <fmt:message bundle="${bundle}" key="button.assign_car"/>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <c:if test="${empty cars}">
            <div class="content-unit-container">
                <label class="details-label-warn">
                    There are no cars that match the request at the moment
                </label>
            </div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
