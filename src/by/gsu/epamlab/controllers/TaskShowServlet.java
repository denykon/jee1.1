package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.constants.ConstantsJSP;
import by.gsu.epamlab.model.constants.ConstantsServlet;
import by.gsu.epamlab.model.impl.TaskImplDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class TaskShowServlet extends AbstractServlet {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        //getting the page reference
        String[] paramValues = request.getParameterValues(ConstantsJSP.REFERENCE);
        if (paramValues == null && session.getAttribute(ConstantsServlet.CURRENT_PAGE) == null) {
            session.setAttribute(ConstantsServlet.CURRENT_PAGE, ConstantsJSP.TODAY_PAGE);
        } else if (paramValues != null) {
            session.setAttribute(ConstantsServlet.CURRENT_PAGE, paramValues[0]);
        }

        //checking the user and session need to add
        User user = (User) session.getAttribute(ConstantsServlet.USER);
        List<Task> list = new TaskImplDB().getTasks(user.getId(), (String) session.getAttribute(ConstantsServlet.CURRENT_PAGE));
        session.setAttribute(ConstantsJSP.TASK_LIST, list);
        jump(ConstantsServlet.JUMP_MAIN, request, response);
    }
}
