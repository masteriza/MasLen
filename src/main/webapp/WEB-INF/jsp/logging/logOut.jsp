<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>

<div align="center">
    <h2><s:message code="logout.query"/></h2>
    <table>
        <tr>
            <td>
                <sf:form action="/logOut" method="post" cssClass="black">
                    <input type="submit" class="button150" value="<s:message code="confirm"/>"/>
                </sf:form>
            </td>
            <td>
                <sf:form action="/logOutRefuse" method="get" cssClass="black">
                    <input type="submit" class="button150" value="<s:message code="refuse"/>"/>
                </sf:form>
            </td>
        </tr>
    </table>

    <br>
    <a href="javascript:history.back();"><s:message code="index.back"/></a> |
    <a href="<c:url value="/" />"><s:message code="index.home"/></a>
</div>

</body>
</html>
