package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.constants.ConstantsServlet;
import by.gsu.epamlab.model.factories.TaskDAOFactory;
import by.gsu.epamlab.model.factories.UserDAOFactory;
import by.gsu.epamlab.model.impl.TaskImplDB;
import by.gsu.epamlab.model.impl.UserImplDB;
import by.gsu.epamlab.model.impl.UserImplRAM;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  StartServlet
 */
public class StartServlet extends AbstractServlet {

    private static final long serialVersionUID = -6689210557262892208L;

    private static final String RAM_IMPL = "memory";
    private static final String DATABASE_IMPL = "db";


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

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String impl = config.getServletContext().getInitParameter(ConstantsServlet.IMPL);
        switch (impl) {
            case RAM_IMPL:
                UserDAOFactory.setUserImpl(new UserImplRAM());
                //TaskDAOFactory.setTaskImpl(new TaskImplRAM());
                break;
            case DATABASE_IMPL:
                UserDAOFactory.setUserImpl(new UserImplDB());
                TaskDAOFactory.setTaskImpl(new TaskImplDB());
                break;
            //for wrong parameters
            default:
                UserDAOFactory.setUserImpl(new UserImplDB());
                TaskDAOFactory.setTaskImpl(new TaskImplDB());
        }
    }
}
