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
                            <button name="address" value="AUTHORIZE">
                                <fmt:message bundle="${bundle}" key="button.authorize"/>
                            </button>
                        </div>
                    </c:if>
                    <c:if test="${role < 4}">
                        <c:if test="${role == 3}">
                            <button class="dropbtn" disabled>
                                    ${username}(
                                <fmt:message bundle="${bundle}" key="button.role.user"/>
                                )
                            </button>
                            <div class="dropdown-content">
                                <button name="command" value="view_profile">
                                    <fmt:message bundle="${bundle}" key="button.profile"/>
                                </button>
                                <button name="command" value="log_out">
                                    <fmt:message bundle="${bundle}" key="button.logout"/>
                                </button>
                            </div>
                        </c:if>
                        <c:if test="${role == 2}">
                            <button class="dropbtn" disabled>
                                    ${username}(
                                <fmt:message bundle="${bundle}" key="button.role.driver"/>
                                )
                            </button>
                            <div class="dropdown-content">
                                <button name="command" value="view_profile">
                                    <fmt:message bundle="${bundle}" key="button.profile"/>
                                </button>
                                <button name="address" value="MANAGEMENT">
                                    <fmt:message bundle="${bundle}" key="button.management"/>
                                </button>
                                <button name="command" value="log_out">
                                    <fmt:message bundle="${bundle}" key="button.logout"/>
                                </button>
                            </div>
                        </c:if>
                        <c:if test="${role == 1}">
                            <button class="dropbtn" disabled>
                                    ${username}(
                                <fmt:message bundle="${bundle}" key="button.role.admin"/>
                                )
                            </button>
                            <div class="dropdown-content">
                                <button name="command" value="view_profile">
                                    <fmt:message bundle="${bundle}" key="button.profile"/>
                                </button>
                                <button name="address" value="MANAGEMENT">
                                    <fmt:message bundle="${bundle}" key="button.management"/>
                                </button>
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
        <c:if test="${role == 4}">
        <div class="wrap-login100 p-t-50 p-b-90">
            <span class="login100-form-title p-b-51">
                    <fmt:message bundle="${bundle}" key="welcome.welcome"/>
            </span>
            <span class="login100-form-title" style="font-size: 18px">
                    <fmt:message bundle="${bundle}" key="welcome.advice"/>
            </span>
        </div>
        </c:if>
        <c:if test="${role < 4}">
            <form class="login100-form validate-form flex-sb flex-w" action="${pageContext.request.contextPath}/controller" method="post">
					<span class="login100-form-title p-b-51">
						<fmt:message bundle="${bundle}" key="signup.title"/>
					</span>

                <div class="wrap-input100 validate-input m-b-16" data-validate = "Username is required">
                    <label>
                        <input class="input100" type="text" name="username" placeholder="Username"
                               pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$"
                               required
                        >
                        <span class="form__error"> <fmt:message bundle="${bundle}" key="form.error.username"/></span>
                    </label>
                    <span class="focus-input100"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-16" data-validate = "Username is required">
                    <label>
                        <input class="input100" type="email" name="email" placeholder="Email"
                               pattern="^[A-Za-z0-9+_.-]+@(.+)(\.(.+))+$"
                        >
                        <span class="form__error"> <fmt:message bundle="${bundle}" key="form.error.email"/></span>
                    </label>
                    <span class="focus-input100"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-16" data-validate = "Password is required">
                    <label>
                        <input class="input100" type="password" name="password" placeholder="Password"
                               pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
                               required
                        >
                        <span class="form__error"> <fmt:message bundle="${bundle}" key="form.error.password"/></span>
                    </label>
                    <span class="focus-input100"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-16" data-validate = "Password is required">
                    <label>
                        <input class="input100" type="password" name="repeat_password" placeholder="Repeat password"
                               pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
                               required
                        >
                        <span class="form__error"> <fmt:message bundle="${bundle}" key="form.error.password"/></span>
                    </label>
                    <span class="focus-input100"></span>
                </div>

                <div class="container-login100-form-btn m-t-17">
                    <button class="login100-form-btn" name="command" value="sign_up">
                        <fmt:message bundle="${bundle}" key="button.signup"/>
                    </button>
                </div>

            </form>
        </c:if>
    </div>
</div>


</body>
</html>
