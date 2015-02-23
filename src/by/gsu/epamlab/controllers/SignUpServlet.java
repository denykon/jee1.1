package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.constants.ConstantsJSP;
import by.gsu.epamlab.model.constants.ConstantsServlet;
import by.gsu.epamlab.model.exceptions.DAOException;
import by.gsu.epamlab.model.exceptions.UserAddingException;
import by.gsu.epamlab.model.factories.UserDAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * SignUpServlet
 */
public class SignUpServlet extends AbstractServlet {

    private static final long serialVersionUID = 8965098908092711843L;

    /**
     * @param request  the request
     * @param response the response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session.getAttribute(ConstantsServlet.USER) != null) {
            jumpRedirect(ConstantsServlet.JUMP_MAIN, response);
            return;
        }

        String login = (String) request.getAttribute(ConstantsJSP.REG_LOGIN_NAME);
        String password = (String) request.getAttribute(ConstantsJSP.REG_PASSWORD_NAME);
        String confPassword = (String) request.getAttribute(ConstantsJSP.REG_CONFIRM_PASSWORD_NAME);
        String firstName = (String) request.getAttribute(ConstantsJSP.REG_FIRST_NAME);
        String lastName = (String) request.getAttribute(ConstantsJSP.REG_LAST_NAME);

        if (!password.equals(confPassword)) {
            request.setAttribute(ConstantsJSP.REG_LOGIN_NAME, login);
            request.setAttribute(ConstantsJSP.REG_FIRST_NAME, firstName);
            request.setAttribute(ConstantsJSP.REG_LAST_NAME, lastName);
            request.setAttribute(ConstantsJSP.ERROR_MESSAGE, ConstantsJSP.PASSWORDS_MATCH_ERROR);
            jump(ConstantsServlet.JUMP_SIGNUP, request, response);
            return;
        }

        User user;

        try {
            user = UserDAOFactory.getUserImpl().addUser(login, password, firstName, lastName);
        } catch (UserAddingException e) {
            request.setAttribute(ConstantsJSP.REG_FIRST_NAME, firstName);
            request.setAttribute(ConstantsJSP.REG_LAST_NAME, lastName);
            request.setAttribute(ConstantsJSP.ERROR_MESSAGE, e.getMessage());
            jump(ConstantsServlet.JUMP_SIGNUP, request, response);
            return;
        } catch (DAOException e) {
            jump(ConstantsServlet.JUMP_MAIN, request, response);
            return;
        }

        session.setAttribute(ConstantsServlet.USER, user);
        jumpRedirect(ConstantsServlet.JUMP_TASK_SERVLET, response);
    }
}