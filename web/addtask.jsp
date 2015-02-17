<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/jstl/core" prefix="c" %>

<form method="post" name="addTaskForm" action="/addtask">
    <div class="add-task">
        <ul>
            <li><input type="text" name="title-text" required></li>
            <li><input type="hidden" name="date" value="${sessionScope.taskPage}"></li>
            <c:if test="${sessionScope.taskPage == 'someday'}">
            <li><input type="date" name="exp-date" required></li>
            </c:if>
            <li><input type="submit" name="addTaskSubmit" value="Add task"></li>
        </ul>
    </div>
</form>