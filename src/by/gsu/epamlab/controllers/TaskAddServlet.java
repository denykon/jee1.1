package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.constants.ConstantsJSP;
import by.gsu.epamlab.model.constants.ConstantsServlet;
import by.gsu.epamlab.model.factories.TaskDAOFactory;
import by.gsu.epamlab.model.helpers.DateCreator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TaskAddServlet extends AbstractServlet {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(ConstantsServlet.USER) == null) {
            jumpRedirect(ConstantsServlet.JUMP_TASK_SERVLET, response);//todo try change on main
            return;
        }

        User user = (User) session.getAttribute(ConstantsServlet.USER);

        String tittle = request.getParameter(ConstantsJSP.TITLE_TEXT);
        String expDate = request.getParameter(ConstantsJSP.EXP_DATE);
        String day = request.getParameter(ConstantsJSP.DATE);

        if (expDate == null) {
            String taskDate = DateCreator.create(day);
            TaskDAOFactory.getTaskImpl().addTask(user.getId(), tittle, taskDate);
        } else {
            TaskDAOFactory.getTaskImpl().addTask(user.getId(), tittle, expDate);
        }

        jumpRedirect(ConstantsServlet.JUMP_TASK_SERVLET, response);
    }
}
