package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.constants.ConstantsServlet;
import by.gsu.epamlab.model.entity.Bin;
import by.gsu.epamlab.model.helpers.Security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Empty Recycle Bin
 */

public class BinEmptyServlet extends AbstractServlet {
    private static final long serialVersionUID = 8891834488944681304L;

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!Security.isUserValid(request)) {
            jumpRedirect(ConstantsServlet.JUMP_MAIN, response);
            return;
        }

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(ConstantsServlet.USER);

        new Bin().empty(user.getId());

        jumpRedirect(ConstantsServlet.JUMP_TASK_SERVLET, response);
    }
}