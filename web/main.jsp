<%@ taglib uri="/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF8">
    <title>Best ToDo list</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<hr>
<div class="center-block">
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
<%--modal vindow--%>
<!-- Button HTML (to Trigger Modal) -->
<a href="#myModal" class="btn btn-lg btn-primary" data-toggle="modal">Launch Demo Modal</a>

<!-- Modal HTML -->
<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Confirmation</h4>
            </div>
            <div class="modal-body">
                <p>Do you want to save changes you made to document before closing?</p>

                <p class="text-warning">
                    <small>If you don't save, your changes will be lost.</small>
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
<%--modal--%>
<%--modal vindow--%>

<hr>
<jsp:include page="footer.jsp"/>
<script src="js/functions.js"></script>
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/jqfunctions.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
