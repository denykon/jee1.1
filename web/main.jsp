<%@ taglib uri="/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF8">
    <title>Best ToDo list</title>
    <style><jsp:include page="css/style.css"/></style>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr>
<div class="main">
    <jsp:include page="navigation.jsp"/>
    <jsp:include page="todo.jsp"/>
    <jsp:include page="actionmenu.jsp"/>
</div>
<hr>
<jsp:include page="footer.jsp"/>
</body>
</html>
