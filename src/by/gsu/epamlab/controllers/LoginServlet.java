package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.constants.ConstantsJSP;
import by.gsu.epamlab.model.constants.ConstantsServlet;
import by.gsu.epamlab.model.exceptions.UserNotFoundException;
import by.gsu.epamlab.model.factories.UserDAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *  Login Servlet
 */
public class LoginServlet extends AbstractServlet {

    /**
     *
     * @param request the HttpServletRequest
     * @param response the HttpServletResponse
     *
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String login = (String) request.getAttribute(ConstantsJSP.LOGIN_NAME);
        String password = (String) request.getAttribute(ConstantsJSP.PASSWORD_NAME);

        if (login == null || password == null || login.length() == 0 || password.length() == 0) {
            request.setAttribute(ConstantsJSP.KEY_ERROR_MESSAGE, ConstantsServlet.EMPTY_LOGIN_PASSWORD_ERROR);
            jump(ConstantsServlet.JUMP_MAIN, request, response);
            return;
        }

        HttpSession session = request.getSession(true);

        if (session.getAttribute(ConstantsServlet.USER) == null) {
            try {
                session.setAttribute(ConstantsServlet.USER, UserDAOFactory.getUserImpl().getUser(login, password));
            } catch (UserNotFoundException e) {
                session.setAttribute(ConstantsServlet.USER, null);
                request.setAttribute(ConstantsJSP.KEY_ERROR_MESSAGE, e.getMessage());
                jump(ConstantsServlet.JUMP_MAIN, request, response);
                return;
            }
        }

        jumpRedirect(ConstantsServlet.JUMP_TASK_SERVLET, response);
    }
}
