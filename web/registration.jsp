<%@ page import="by.gsu.epamlab.model.constants.ConstantsJSP" %>
<%@ taglib uri="/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF8">
    <title>Registration</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<form class="form-horizontal" method="post" name="regForm" action="/signup">
    <fieldset>
        <legend>Registration</legend>

        <div class="form-group">
            <label class="col-md-4 control-label" for="<%= ConstantsJSP.REG_LOGIN_NAME%>">Login</label>

            <div class="col-md-4">
                <input class="form-control input-md" type="text" name="<%= ConstantsJSP.REG_LOGIN_NAME%>"
                       value="${requestScope.regLogin}"
                       title="Please, use only a-z, A-Z, 0-9"
                       pattern="[a-z0-9A-Z]{3,}" maxlength="10" placeholder="Enter your login here" required>
                <span class="help-block">You can use a-z, 0-9, length 3 - 10 symbols</span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="<%= ConstantsJSP.REG_PASSWORD_NAME%>">Password</label>

            <div class="col-md-4">
                <input class="form-control input-md" type="password" name="<%= ConstantsJSP.REG_PASSWORD_NAME%>"
                       placeholder="Enter your password here"
                       required>
                <span class="help-block">Does not recommend the use of passwords from other sites or words
                    that attackers can easily pick up</span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label"
                   for="<%= ConstantsJSP.REG_CONFIRM_PASSWORD_NAME%>">Confirm password</label>

            <div class="col-md-4">
                <input class="form-control input-md" type="password" name="<%= ConstantsJSP.REG_CONFIRM_PASSWORD_NAME%>"
                       placeholder="Enter your password here again" required>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="<%= ConstantsJSP.REG_FIRST_NAME%>">First name</label>

            <div class="col-md-4">
                <input class="form-control input-md" type="text" name="<%= ConstantsJSP.REG_FIRST_NAME%>"
                       value="${requestScope.regFirstName}"
                       placeholder="Enter your first name here" required>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="<%=ConstantsJSP.REG_LAST_NAME%>">Last name</label>

            <div class="col-md-4">
                <input class="form-control input-md" type="text" name="<%=ConstantsJSP.REG_LAST_NAME%>" id="regLastName"
                       value="${requestScope.regLastName}" placeholder="Enter your last name here" required>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="<%=ConstantsJSP.REG_SUBMIT_NAME%>"></label>

            <div class="col-md-2">
                <input class="btn btn-success" type="submit" name="<%=ConstantsJSP.REG_SUBMIT_NAME%>" value="Register">
            </div>
            <div class="col-md-2">
                <a href="main.jsp">Back to main page</a>
            </div>
        </div>
        <c:if test="${errorMessage != null}">
            <div class="alert alert-danger" role="alert">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                <c:out value="${errorMessage}"/>
            </div>
        </c:if>
    </fieldset>
</form>
</body>