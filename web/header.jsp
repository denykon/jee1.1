<%@ page import="by.gsu.epamlab.model.constants.ConstantsJSP" %>
<%@ taglib uri="/jstl/core" prefix="c" %>
<div id="header">
    <h2 id="welcome_text">Welcome, <c:out value="${user.login}" default="<%=ConstantsJSP.KEY_GUEST%>"/></h2>
    <c:choose>
        <c:when test="${empty user}">
            <jsp:include page="login.jsp"/>
            <a href="registration.jsp">Registration</a>
        </c:when>
        <c:otherwise>
            <a href="/logout">Logout</a>
        </c:otherwise>
    </c:choose>
</div>