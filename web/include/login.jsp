<%@ page import="by.gsu.epamlab.model.constants.ConstantsJSP" %>
<%@ taglib uri="/jstl/core" prefix="c" %>
<form method="post" class="form-inline" name="loginForm" action="/login">
    <div class="form-group">
        <label class="control-label" for=<%= ConstantsJSP.LOGIN_NAME %>>Login</label>
        <input class="form-control" type="text" name=<%= ConstantsJSP.LOGIN_NAME %> id="login"
               placeholder="Enter your login here" required/>
    </div>
    <div class="form-group">
        <label class="control-label" for=<%= ConstantsJSP.PASSWORD_NAME %>>Password</label>
        <input class="form-control" type="password" name=<%= ConstantsJSP.PASSWORD_NAME %> id="password"
               placeholder="Enter your password here" required/>
    </div>
    <input type="submit" class="btn btn-sm btn-success" name=<%= ConstantsJSP.LOGIN_SUBMIT_NAME %> value="Login"/>
    <c:if test="${errorMessage != null}">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <c:out value="${errorMessage}"/>
        </div>
    </c:if>
</form>