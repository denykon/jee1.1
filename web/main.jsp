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
<jsp:include page="include/header.jsp"/>
<hr>
<div class="center-block">
    <c:choose>
        <c:when test="${not empty user}">
            <jsp:include page="include/navigation.jsp"/>
            <jsp:include page="include/todo.jsp"/>
            <jsp:include page="include/actionmenu.jsp"/>
        </c:when>
        <c:otherwise>
            <jsp:include page="include/hello.jsp"/>
        </c:otherwise>
    </c:choose>
</div>
<hr>
<jsp:include page="include/footer.jsp"/>
<script src="js/functions.js"></script>
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/jqfunctions.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
