package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.beans.FileData;
import by.gsu.epamlab.model.constants.ConstantsServlet;
import by.gsu.epamlab.model.impl.TaskImplDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FileDownloadServlet extends AbstractServlet {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String taskId = request.getParameter("fileTaskId");
        String fileName = new TaskImplDB().getFileName(taskId);
        if (!"".equals(fileName)) {
            new FileData().download(fileName, response);
        }

        jumpRedirect(ConstantsServlet.JUMP_TASK_SERVLET, response);

    }
}
