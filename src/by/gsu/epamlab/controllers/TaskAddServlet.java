package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.constants.ConstantsServlet;
import by.gsu.epamlab.model.impl.TaskImplDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskAddServlet extends AbstractServlet {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            jumpRedirect(ConstantsServlet.JUMP_SERVLET, response);
            return;
        }
        User user = (User) session.getAttribute("user");
        String tittle = request.getParameter("title-text");
        Date expDate = dateFormat(request.getParameter("exp-date"));

        new TaskImplDB().addTask(user.getId(), tittle, expDate);
        jumpRedirect(ConstantsServlet.JUMP_SERVLET, response);
    }

    private Date dateFormat(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
