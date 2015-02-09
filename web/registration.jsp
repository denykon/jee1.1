<%@ page import="by.gsu.epamlab.model.constants.ConstantsJSP" %>
<%@ taglib uri="/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF8">
    <title>Registration</title>
    <style><jsp:include page="css/style.css"/></style>
</head>
<body>
<form method="post" name="regForm" action="/signup">
    <div class="form">
    <ul>
        <li>
            <label for="<%= ConstantsJSP.REG_LOGIN_NAME%>">Login</label>
            <input type="text" name="<%= ConstantsJSP.REG_LOGIN_NAME%>" title="Please, use only a-z, A-Z, 0-9"
                   pattern="[a-z0-9A-Z]{3,}" maxlength="10" placeholder="Enter your login here" required>
        </li>
        <li>
            <label for="<%= ConstantsJSP.REG_PASSWORD_NAME%>">Password</label>
            <input type="password" name="<%= ConstantsJSP.REG_PASSWORD_NAME%>" placeholder="Enter your password here"
                   required>
        </li>
        <li>
            <label for="<%= ConstantsJSP.REG_CONFIRM_PASSWORD_NAME%>">Confirm password</label>
            <input type="password" name="<%= ConstantsJSP.REG_CONFIRM_PASSWORD_NAME%>"
                   placeholder="Enter your password here" required>
        </li>
        <li>
            <label for="<%= ConstantsJSP.REG_FIRST_NAME%>">First name</label>
            <input type="text" name="<%= ConstantsJSP.REG_FIRST_NAME%>" value="${requestScope.regFirstName}"
                   placeholder="Enter your first name here" required>
        </li>
        <li>
            <label for="<%=ConstantsJSP.REG_LAST_NAME%>">Last name</label>
            <input type="text" name="<%=ConstantsJSP.REG_LAST_NAME%>" id="regLastName"
                   value="${requestScope.regLastName}" placeholder="Enter your last name here" required>
        </li>
        <li>
            <input type="submit" name="<%=ConstantsJSP.REG_SUBMIT_NAME%>" value="Register">
        </li>
        <li>
            <a href="main.jsp">Back to main page</a>
        </li>
        <li>
            <c:out value="${errorMessage}"/>
        </li>
    </ul>
    </div>
</form>
</body>