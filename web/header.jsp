<%@ page import="by.gsu.epamlab.model.constants.ConstantsJSP" %>
<%@ taglib uri="/jstl/core" prefix="c" %>
<div id="header">
    <div class="container">
        <h3>Welcome <c:out value="${user.firstName}" default="<%=ConstantsJSP.KEY_GUEST%>"/></h3>
    <c:choose>
        <c:when test="${empty user}">
            <jsp:include page="login.jsp"/>
            <h5><a href="registration.jsp">or You can <kbd>REGISTER</kbd></a></h5>
        </c:when>
        <c:otherwise>
            <h5><a href="/logout">Logout</a></h5>
        </c:otherwise>
    </c:choose>
    </div>
</div>