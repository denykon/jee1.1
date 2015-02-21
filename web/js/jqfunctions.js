$(document).ready(function () {
    $("#navi").find("li a").click(function (event) {
        event.preventDefault();
        $("#ref").val(event.target.name);
        $("#naviForms").submit();

    });
});