<%@ taglib uri="/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF8">
    <title>Best ToDo list</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<hr>
<div class="center-block">
    <c:choose>
        <c:when test="${not empty user}">
            <c:import url="navigation.jsp"/>
            <%--<jsp:include page="navigation.jsp"/>--%>
            <jsp:include page="todo.jsp"/>
            <jsp:include page="actionmenu.jsp"/>
        </c:when>
        <c:otherwise>
            <jsp:include page="hello.jsp"/>
        </c:otherwise>
    </c:choose>
</div>
<hr>
<jsp:include page="footer.jsp"/>
<script src="js/functions.js"></script>
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/jqfunctions.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
