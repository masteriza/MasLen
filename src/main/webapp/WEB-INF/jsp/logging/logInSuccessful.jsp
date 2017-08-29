<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>

<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal.username" var="username"/>
</sec:authorize>

<div align="center">
    <h2><p>
        <s:message code="login.success1"/>
        <c:out value="${username}"/>!
        <p><s:message code="login.success2"/></h2>

    <br>
    <a href="<c:url value="/" />"><s:message code="index.home"/></a>
</div>

</body>
</html>
