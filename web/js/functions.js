function moveTask() {
    document.todoForm.act.value = "move";
    document.todoForm.action = "/taskact";
    document.todoForm.submit();
}

function fixTask() {
    document.todoForm.act.value = "fix";
    document.todoForm.action = "/taskact";
    document.todoForm.submit();
}

function deleteTask() {
    document.todoForm.act.value = "delete";
    document.todoForm.action = "/taskact";
    document.todoForm.submit();
}

function deleteAll() {
    document.todoForm.action = "/emptybin";
    document.todoForm.submit();
}