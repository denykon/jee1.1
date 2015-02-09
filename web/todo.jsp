<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/jstl/core" prefix="c" %>
<div class="todo">
    <table>
        <tr>
            <td>#</td>
            <td>#</td>
            <td>Task</td>
            <td>Status</td>
            <td>Date</td>
            <td>File</td>
        </tr>
        <c:forEach var="task" items="${requestScope.taskList}">
            <tr>
                <td>#</td>
                <td><input type="checkbox" name="${task.id}"></td>
                <td>${task.tittle}</td>
                <td>${task.status}</td>
                <td>${task.extDate}</td>
                <td>-</td>
            </tr>
        </c:forEach>
    </table>
</div>
