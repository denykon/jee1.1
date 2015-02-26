<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/jstl/core" prefix="c" %>
<div class="todo container">
    <form method="post" name="todoForm" id="todoForm"></form>
    <input type="hidden" name="act" form="todoForm">
    <table class="table table-striped">
        <c:forEach var="task" items="${sessionScope.taskList}" varStatus="count">
            <tr>
                <td class="col-sm-1">${count.index + 1}</td>
                <td class="col-sm-1"><input type="checkbox" name="items" value="${task.id}" form="todoForm"></td>
                <td class="col-md-4">${task.tittle}</td>
                <td class="col-md-1">${task.status}</td>
                <td class="col-md-2">${task.extDate}</td>
                <c:if test="${task.haveFile == false && sessionScope.taskPage != 'bin'}">
                    <td class="col-md-6">
                        <form method="post" name="uploadFileForm${task.id}" enctype="multipart/form-data"
                              action="/fileupload">
                            <input type="file" name="file" required>
                            <input type="hidden" name="fileTaskId" value="${task.id}">
                            <input type="submit" value="Upload">
                        </form>
                    </td>
                </c:if>
                <c:if test="${task.haveFile == true && sessionScope.taskPage != 'bin'}">
                    <td class="col-md-2">
                        <form method="post" name="downloadFileForm${task.id}" action="/filedownload">
                            <input type="hidden" name="fileTaskId" value="${task.id}">
                            <input type="submit" class="btn btn-success" value="Download">
                        </form>
                    </td>
                    <td class="col-md-1">
                        <form method="post" name="deleteFile" action="/filedelete">
                            <input type="hidden" name="fileTaskId" value="${task.id}">
                            <input type="submit" class="btn btn-danger" value="Delete File">
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>