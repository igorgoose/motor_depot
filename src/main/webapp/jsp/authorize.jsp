<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale" var="bundle"/>
<html>
<head>
    <title><fmt:message bundle="${bundle}" key="authorize.label"/></title>
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
    <div class="toolbar-top-container">
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
                    <c:if test="${role.id == 4}">
                        <button class="dropbtn" disabled>
                            <fmt:message bundle="${bundle}" key="button.role.guest"/>
                        </button>
                        <div class="dropdown-content">
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="address" value="HOME">
                                <button name="command" value="redirect">
                                    <fmt:message bundle="${bundle}" key="button.home"/>
                                </button>
                            </form>
                        </div>
                    </c:if>
                    <c:if test="${role.id < 4}">
                        <c:if test="${role.id == 3}">
                            <button class="dropbtn" disabled>
                                    ${username}[
                                <fmt:message bundle="${bundle}" key="button.role.user"/>
                                ]
                            </button>
                            <div class="dropdown-content">
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <button name="command" value="view_profile">
                                        <fmt:message bundle="${bundle}" key="button.profile"/>
                                    </button>
                                </form>
                                <button name="command" value="log_out">
                                    <fmt:message bundle="${bundle}" key="button.logout"/>
                                </button>
                            </div>
                        </c:if>
                        <c:if test="${role.id == 2}">
                            <button class="dropbtn" disabled>
                                    ${username}[
                                <fmt:message bundle="${bundle}" key="button.role.driver"/>
                                ]
                            </button>
                            <div class="dropdown-content">
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <button name="command" value="view_profile">
                                        <fmt:message bundle="${bundle}" key="button.profile"/>
                                    </button>
                                </form>
                                <form action="${pageContext.request.contextPath}/controller" method="post">
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
                        <c:if test="${role.id == 1}">
                            <button class="dropbtn" disabled>
                                    ${username}[
                                <fmt:message bundle="${bundle}" key="button.role.admin"/>
                                ]
                            </button>
                            <div class="dropdown-content">
                                <form action="${pageContext.request.contextPath}/controller" method="post">
                                    <button name="command" value="view_profile">
                                        <fmt:message bundle="${bundle}" key="button.profile"/>
                                    </button>
                                </form>
                                <form action="${pageContext.request.contextPath}/controller" method="post">
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
        <c:if test="${message != null}">
                    <span class="login100-form-title" style="font-size: 14px; margin-top: 5px">
                            ${message}
                    </span>
        </c:if>
    </div>
    <div class="container-login100">
        <div class="wrap-login100 p-t-50 p-b-90">
            <form class="login100-form validate-form flex-sb flex-w" action="${pageContext.request.contextPath}/controller" method="post">
					<span class="login100-form-title p-b-51">
						<fmt:message bundle="${bundle}" key="authorize.label"/>
					</span>

                <div class="wrap-input100 validate-input m-b-16" data-validate="Username is required">
                    <label>
                        <input class="input100" type="text" name="username" placeholder="Username"
                               pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$"
                               required
                        >
                        <span class="form__error"> <fmt:message bundle="${bundle}" key="form.error.username"/></span>
                    </label>
                    <span class="focus-input100"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-16" data-validate="Password is required">
                    <label>
                        <input class="input100" type="password" name="password" placeholder="Password"
                               pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
                               required
                        >
                        <span class="form__error"> <fmt:message bundle="${bundle}" key="form.error.password"/></span>
                    </label>
                    <span class="focus-input100"></span>
                </div>

                <div class="container-login100-form-btn m-t-17">
                    <button class="login100-form-btn" name="command" value="log_in">
                        <fmt:message bundle="${bundle}" key="button.login"/>
                    </button>
                </div>

            </form>
            <form class="login100-form validate-form flex-sb flex-w" action="${pageContext.request.contextPath}/controller" method="post">
                <div class="container-login100-form-btn m-t-17">
                    <input type="hidden" name="address" value="SIGN_UP"/>
                    <button class="login100-form-btn" name="command" value="redirect">
                        <fmt:message bundle="${bundle}" key="button.signup"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>


<div id="dropDownSelect1"></div>

</body>
</html>