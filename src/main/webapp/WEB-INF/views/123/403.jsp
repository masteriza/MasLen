<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>

<div align="center">
    <h2><span class="yellow">HTTP Status 403 - <s:message code="error.accessDenied"/></span></h2>

    <a href="javascript:history.back();"><s:message code="index.back"/></a> |
    <a href="<c:url value="/" />"><s:message code="index.home"/></a>
</div>

</body>
</html>
