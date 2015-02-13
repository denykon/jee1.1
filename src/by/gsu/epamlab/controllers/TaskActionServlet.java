package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.constants.ConstantsServlet;
import by.gsu.epamlab.model.impl.TaskImplDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TaskActionServlet extends AbstractServlet {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] paramValues = request.getParameterValues("items");
        if (paramValues == null) {
            jump(ConstantsServlet.JUMP_MAIN, request, response);
            return;
        }

        String action = request.getParameter("act");
        new TaskImplDB().actTask(paramValues, action);

        jumpRedirect(ConstantsServlet.JUMP_TASK_SERVLET, response);
    }
}