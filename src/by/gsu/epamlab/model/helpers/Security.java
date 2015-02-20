package by.gsu.epamlab.model.helpers;

import by.gsu.epamlab.model.constants.ConstantsServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Security {
    public static boolean isUserValid(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        return (session.getAttribute(ConstantsServlet.USER) != null);
    }
}
