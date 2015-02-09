package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.constants.ConstantsJSP;
import by.gsu.epamlab.model.constants.ConstantsServlet;
import by.gsu.epamlab.model.exceptions.DAOFactoryException;
import by.gsu.epamlab.model.exceptions.UserNotFoundException;
import by.gsu.epamlab.model.factories.UserDAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *  LoginServlet
 */
public class LoginServlet extends AbstractServlet {

    /**
     *
     * @param request the request
     * @param response the response
     *
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = (String) request.getAttribute(ConstantsJSP.LOGIN_NAME);
        String password = (String) request.getAttribute(ConstantsJSP.PASSWORD_NAME);

        if (login == null || password == null || login.length() == 0 || password.length() == 0) {
            jump(ConstantsServlet.JUMP_MAIN, request, response);
            return;
        }

        HttpSession session = request.getSession(false);
        if (session.getAttribute(ConstantsJSP.SESSION_USER) == null) {
            String impl = session.getServletContext().getInitParameter(ConstantsServlet.IMPL);
            try {
                session.setAttribute(ConstantsJSP.SESSION_USER, UserDAOFactory.
                        createDAOUser(impl).getUser(login, password));
            } catch (UserNotFoundException | DAOFactoryException e) {
                session.setAttribute(ConstantsJSP.SESSION_USER, null);
                request.setAttribute(ConstantsJSP.KEY_ERROR_MESSAGE, e.getMessage());
            }
        }
        request.removeAttribute(ConstantsJSP.PASSWORD_NAME);
        jump(ConstantsServlet.JUMP_SERVLET, request, response);
    }
}
