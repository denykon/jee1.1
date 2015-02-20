<%@ page import="by.gsu.epamlab.model.constants.ConstantsJSP" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/jstl/core" prefix="c" %>
<div class="navigation col-sm-2"></div>
<div class="navigation col-sm-5">
    <form id="naviForms" method="post" name="naviForm" action="/tasks">
        <input id="ref" type="hidden" name="<%=ConstantsJSP.REFERENCE%>">
        <div class="container-fluid">
            <nav class="navbar navbar-inverse">
                <ul id="navi" class="nav navbar-nav">
                    <li>
                        <a href="" name="today">Today</a>
                    </li>

                    <li>
                        <a href="" name="tomorrow">Tomorrow</a>
                    </li>

                    <li>
                        <a href="" name="someday">Someday</a>
                    </li>

                    <li>
                        <a href="" name="fixed">Fixed</a>
                    </li>

                    <li>
                        <a href="" name="bin">Recycle Bin</a>
                    </li>
                </ul>
            </nav>
        </div>
    </form>
</div>
<div>
    <h2 class="text-uppercase"><c:out value="${sessionScope.taskPage}"/></h2>
</div>
