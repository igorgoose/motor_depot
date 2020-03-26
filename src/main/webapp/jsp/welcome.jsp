<%--
  Created by IntelliJ IDEA.
  User: igorg
  Date: 22.03.2020
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locale" var="bundle" />
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <h1>Welcome, ${username}</h1>
</body>
</html>
