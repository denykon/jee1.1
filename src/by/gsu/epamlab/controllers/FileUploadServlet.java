package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.beans.File;
import by.gsu.epamlab.model.constants.ConstantsServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FileUploadServlet extends AbstractServlet {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletInputStream in = request.getInputStream();
        new File().upload(in);
        jumpRedirect(ConstantsServlet.JUMP_TASK_SERVLET, response);
    }
}