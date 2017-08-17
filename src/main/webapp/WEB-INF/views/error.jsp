<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>

<div align="center">

    <c:if test="${empty errorMessage}">
        <h2><span class="yellow"><s:message code="error.error"/></span></h2>
    </c:if>


    <c:if test="${!empty errorMessage}">
        <h2><span class="yellow">Error: <c:out value="${errorMessage}"/></span></h2>
    </c:if>

    <a href="<c:url value="/" />"><s:message code="index.home"/></a>
</div>

</body>
</html>
