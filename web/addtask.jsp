<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form method="post" name="addTaskForm" action="/addtask">
    <div class="add-task">
        <ul>
            <li><input type="text" name="title-text" required></li>
            <li><input type="date" name="exp-date" required></li>
            <li><input type="submit" name="addTaskSubmit" value="Add task"></li>
        </ul>
    </div>
</form>