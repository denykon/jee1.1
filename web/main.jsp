<%@ taglib uri="/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF8">
    <script src="js/functions.js"></script>
    <title>Best ToDo list</title>
    <style><jsp:include page="css/style.css"/></style>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr>
<div class="main">
    <c:choose>
        <c:when test="${not empty user}">
            <jsp:include page="navigation.jsp"/>
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
</body>
</html>
