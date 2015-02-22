package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.constants.ConstantsServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *  LogoutServlet
 */
public class LogoutServlet extends AbstractServlet {
    private static final long serialVersionUID = -3298384702630067457L;

    /**
     *
     * @param request the request
     * @param response the response
     *
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session != null){
            session.invalidate();
        }

        jumpRedirect(ConstantsServlet.JUMP_MAIN, response);
    }
}
