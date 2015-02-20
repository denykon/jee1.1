package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.constants.ConstantsServlet;
import by.gsu.epamlab.model.logic.Bin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BinEmptyServlet extends AbstractServlet {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ConstantsServlet.USER);

        Bin.empty(user.getId());

        jumpRedirect(ConstantsServlet.JUMP_TASK_SERVLET, response);

    }
}
