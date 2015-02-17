<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/jstl/core" prefix="c" %>

<div class="action-menu">
    <ul class="list-unstyled">

        <c:if test="${sessionScope.taskPage == 'today' || sessionScope.taskPage == 'tomorrow' ||
                        sessionScope.taskPage == 'someday'}">

            <li><a href="addtask.jsp">Add task</a></li>

        </c:if>
        <c:if test="${sessionScope.taskPage == 'fixed'}">
            <li><a href="Javascript:fixTask()">Unfix</a></li>
        </c:if>
        <c:if test="${sessionScope.taskPage != 'fixed' && sessionScope.taskPage != 'bin'}">
            <li><a href="Javascript:fixTask()">Fix</a></li>
        </c:if>
        <c:if test="${sessionScope.taskPage != 'bin'}">
            <li><a href="Javascript:moveTask()">Remove</a></li>
        </c:if>
        <c:if test="${sessionScope.taskPage == 'bin'}">
            <li><a href="Javascript:moveTask()">Restore</a></li>
            <li><a href="Javascript:deleteTask()">Delete</a></li>
        </c:if>
    </ul>
</div>
