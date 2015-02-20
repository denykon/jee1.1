package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.constants.ConstantsServlet;
import by.gsu.epamlab.model.logic.FileData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FileDeleteServlet extends AbstractServlet {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String taskId = request.getParameter("fileTaskId");
        if (taskId != null && !"".equals(taskId)) {
            new FileData().delete(taskId);
        }
        jumpRedirect(ConstantsServlet.JUMP_TASK_SERVLET, response);
    }
}
