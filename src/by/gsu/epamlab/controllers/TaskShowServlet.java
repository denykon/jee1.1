package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.constants.ConstantsServlet;
import by.gsu.epamlab.model.impl.TaskImplDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class TaskShowServlet extends AbstractServlet {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        //check the user and session need to add
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        List<Task> list = new TaskImplDB().getTasks(user.getId());
        request.setAttribute("taskList", list);
        jump(ConstantsServlet.JUMP_MAIN, request, response);
    }
}
