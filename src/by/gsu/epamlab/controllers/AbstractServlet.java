package by.gsu.epamlab.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractServlet extends HttpServlet {

    private static final long serialVersionUID = -1327172833125275182L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        performTask(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        performTask(request, response);
    }

    /**
     * performTask.
     *
     * @param request the request
     * @param response the response
     *
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    abstract protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    /**
     * jump.
     *
     * @param url the url
     * @param request the request
     * @param response the response
     *
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void jump(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    /**
     * jumpRedirect.
     *
     * @param url the url
     * @param response the response
     *
     * @throws IOException
     */
    protected void jumpRedirect(String url, HttpServletResponse response) throws IOException {
        response.sendRedirect(url);
    }
}
