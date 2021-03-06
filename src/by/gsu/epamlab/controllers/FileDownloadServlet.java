package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.constants.ConstantsJSP;
import by.gsu.epamlab.model.constants.ConstantsServlet;
import by.gsu.epamlab.model.entity.FileData;
import by.gsu.epamlab.model.factories.TaskDAOFactory;
import by.gsu.epamlab.model.helpers.Security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * File download
 */
public class FileDownloadServlet extends AbstractServlet {
    private static final long serialVersionUID = 2208068223950740320L;

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!Security.isUserValid(request)) {
            jumpRedirect(ConstantsServlet.JUMP_MAIN, response);
            return;
        }

        String taskId = request.getParameter(ConstantsJSP.FILE_TASK_ID);

        if (taskId == null) {
            jumpRedirect(ConstantsServlet.JUMP_MAIN, response);
            return;
        }

        String fileName = TaskDAOFactory.getTaskImpl().getFileName(taskId);

        if (!"".equals(fileName)) {
            new FileData().download(fileName, response);
        }
    }
}
