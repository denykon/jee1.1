<%@ page import="by.gsu.epamlab.model.constants.ConstantsJSP" %>
<%@ taglib uri="/jstl/core" prefix="c" %>
<%@ page session="true" %>
<form method="post" name="loginForm" action="/login">
    <div class="form">
        <ul class="list">
            <li class="field">
                <label for=<%= ConstantsJSP.LOGIN_NAME %>>Login</label>
                <input type="text" name=<%= ConstantsJSP.LOGIN_NAME %> id="login" placeholder="Enter your login here"
                       required/>
            </li>
            <li class="field">
                <label for=<%= ConstantsJSP.PASSWORD_NAME %>>Password</label>
                <input type="password" name=<%= ConstantsJSP.PASSWORD_NAME %> id="password"
                       placeholder="Enter your password here" required/>
            </li>
            <li>
                <input type="submit" name=<%= ConstantsJSP.LOGIN_SUBMIT_NAME %> value="Login"/>
            </li>
            <li>
                <c:out value="${errorMessage}"/>
            </li>
        </ul>
    </div> <%--.form--%>
</form>