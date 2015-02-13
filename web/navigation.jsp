<%@ page import="by.gsu.epamlab.model.constants.ConstantsJSP" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="navigation">
    <form method="post" name="naviForm" action="/tasks">
        <input type="hidden" name="<%=ConstantsJSP.REFERENCE%>"><BR>
        <ul>
            <li>
                <a href="JavaScript:sendForm('today')">Today</a>
            </li>
            <li>
                <a href="JavaScript:sendForm('tomorrow')">Tomorrow</a>
            </li>
            <li>
                <a href="JavaScript:sendForm('someday')">Someday</a>
            </li>
            <li>
                <a href="JavaScript:sendForm('fixed')">Fixed</a>
            </li>
            <li>
                <a href="JavaScript:sendForm('bin')">Recycle Bin</a>
            </li>
        </ul>
    </form>
</div>
