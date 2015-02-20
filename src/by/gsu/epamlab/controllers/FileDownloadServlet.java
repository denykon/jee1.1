package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.constants.ConstantsServlet;
import by.gsu.epamlab.model.factories.TaskDAOFactory;
import by.gsu.epamlab.model.logic.FileData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FileDownloadServlet extends AbstractServlet {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String taskId = request.getParameter("fileTaskId");
        String fileName = TaskDAOFactory.getTaskImpl().getFileName(taskId);
        if (!"".equals(fileName)) {
            new FileData().download(fileName, response);
        }

        jumpRedirect(ConstantsServlet.JUMP_TASK_SERVLET, response);

    }
}
