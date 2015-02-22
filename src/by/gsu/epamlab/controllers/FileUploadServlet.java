package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.constants.ConstantsServlet;
import by.gsu.epamlab.model.entity.FileData;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FileUploadServlet extends AbstractServlet {
    private static final long serialVersionUID = -4132861398697668159L;

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletInputStream in = request.getInputStream();
        new FileData().upload(in);
        jumpRedirect(ConstantsServlet.JUMP_TASK_SERVLET, response);
    }
}