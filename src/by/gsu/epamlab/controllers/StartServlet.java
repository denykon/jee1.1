package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.constants.ConstantsServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  StartServlet
 */
public class StartServlet extends AbstractServlet {

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
        jump(ConstantsServlet.JUMP_MAIN, request, response);
    }
}
