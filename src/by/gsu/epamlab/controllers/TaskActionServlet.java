package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.constants.ConstantsJSP;
import by.gsu.epamlab.model.constants.ConstantsServlet;
import by.gsu.epamlab.model.entity.FileData;
import by.gsu.epamlab.model.factories.TaskDAOFactory;
import by.gsu.epamlab.model.helpers.Security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * actions for tasks
 */
public class TaskActionServlet extends AbstractServlet {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!Security.isUserValid(request)) {
            jumpRedirect(ConstantsServlet.JUMP_MAIN, response);
            return;
        }

        String[] paramValues = request.getParameterValues(ConstantsJSP.ITEMS);
        if (paramValues == null) {
            jump(ConstantsServlet.JUMP_MAIN, request, response);
            return;
        }

        String action = request.getParameter(ConstantsJSP.ACTION);

        if (ConstantsJSP.DELETE.equals(action)) {
            new FileData().delete(paramValues);
        }

        TaskDAOFactory.getTaskImpl().actTask(paramValues, action);

        jumpRedirect(ConstantsServlet.JUMP_TASK_SERVLET, response);
    }
}