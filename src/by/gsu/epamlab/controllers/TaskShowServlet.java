package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.constants.ConstantsJSP;
import by.gsu.epamlab.model.constants.ConstantsServlet;
import by.gsu.epamlab.model.factories.TaskDAOFactory;
import by.gsu.epamlab.model.helpers.Security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Show current tasks
 */
public class TaskShowServlet extends AbstractServlet {

    private static final long serialVersionUID = -6978765389547869786L;

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        //user validation
        if (!Security.isUserValid(request)) {
            jumpRedirect(ConstantsServlet.JUMP_MAIN, response);
            return;
        }

        HttpSession session = request.getSession(false);

        //getting the current page
        String[] paramValues = request.getParameterValues(ConstantsJSP.REFERENCE);
        if (paramValues == null && session.getAttribute(ConstantsServlet.CURRENT_PAGE) == null) {
            session.setAttribute(ConstantsServlet.CURRENT_PAGE, ConstantsJSP.TODAY_PAGE);
        } else if (paramValues != null) {
            session.setAttribute(ConstantsServlet.CURRENT_PAGE, paramValues[0]);
        }

        User user = (User) session.getAttribute(ConstantsServlet.USER);
        //task list
        List<Task> list = TaskDAOFactory.getTaskImpl().getTasks(user.getId(),
                (String) session.getAttribute(ConstantsServlet.CURRENT_PAGE));

        session.setAttribute(ConstantsJSP.TASK_LIST, list);
        jump(ConstantsServlet.JUMP_MAIN, request, response);
    }
}
