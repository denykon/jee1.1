<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/jstl/core" prefix="c" %>

<div class="action-menu col-sm-5">

    <c:if test="${sessionScope.taskPage == 'today' || sessionScope.taskPage == 'tomorrow' ||
                        sessionScope.taskPage == 'someday'}">
        <a href="#addTaskModal" class="btn btn-sm btn-primary" data-toggle="modal">
            <span class="glyphicon glyphicon-briefcase"></span> Add new task</a>
    </c:if>
    <c:if test="${sessionScope.taskPage == 'fixed'}">
        <a href="Javascript:fixTask()" class="btn btn-sm btn-success">
            <span class="glyphicon glyphicon-unchecked"></span> Unfix task</a>
    </c:if>
    <c:if test="${sessionScope.taskPage != 'fixed' && sessionScope.taskPage != 'bin'}">
        <a href="Javascript:fixTask()" class="btn btn-sm btn-success">
            <span class="glyphicon glyphicon-check"></span> Fix task</a>
    </c:if>
    <c:if test="${sessionScope.taskPage != 'bin'}">
        <a href="Javascript:moveTask()" class="btn btn-sm btn-warning">
            <span class="glyphicon glyphicon-trash"></span> Remove</a>
    </c:if>
    <c:if test="${sessionScope.taskPage == 'bin'}">
        <a href="Javascript:moveTask()" class="btn btn-sm btn-info">
            <span class="glyphicon glyphicon-wrench"></span> Restore</a>

        <a href="Javascript:deleteTask()" class="btn btn-sm btn-danger">
            <span class="glyphicon glyphicon-fire"></span> Delete</a>

        <a href="Javascript:deleteAll()" class="btn btn-sm btn-danger">
            <span class="glyphicon glyphicon-fire"></span> Empty Recycle Bin</a>
    </c:if>

</div>

<div id="addTaskModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">New Task</h4>
            </div>
            <div class="modal-body">
                <form method="post" name="addTaskForm" id="addTaskForm" action="/addtask">
                    <textarea class="form-control" rows="3" name="title-text" required></textarea>
                    <input type="hidden" name="date" value="${sessionScope.taskPage}">
                    <c:if test="${sessionScope.taskPage == 'someday'}">
                        <input type="date" name="exp-date" required>
                    </c:if>
                </form>
            </div>
            <div class="modal-footer">
                <input type="submit" class="btn btn-sm btn-success" form="addTaskForm" name="addTaskSubmit"
                       value="Add task">
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
