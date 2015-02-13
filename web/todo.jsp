<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/jstl/core" prefix="c" %>
<div class="todo">
    <form method="post" name="todoForm" id="todoForm"></form>
    <input type="hidden" name="act" form="todoForm">
    <table>
        <tr>
            <td>#</td>
            <td>#</td>
            <td>Task</td>
            <td>Status</td>
            <td>Date</td>
            <td>File</td>
        </tr>
        <c:forEach var="task" items="${sessionScope.taskList}" varStatus="count">
            <tr>
                <td>${count.index + 1}</td>
                <td><input type="checkbox" name="items" value="${task.id}" form="todoForm"></td>
                <td>${task.tittle}</td>
                <td>${task.status}</td>
                <td>${task.extDate}</td>
                <td>
                    <form method="post" name="uploadFileForm + ${task.id}" enctype="multipart/form-data"
                          action="/fileupload">
                        <input type="file" name="file">
                        <input type="text" name="fileTaskId" value="${task.id}">
                        <input type="submit" value="Upload">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
