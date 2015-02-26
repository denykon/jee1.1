package by.gsu.epamlab.model.helpers;

import by.gsu.epamlab.model.constants.ConstantsServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * User validation
 */
public class Security {
    public static boolean isUserValid(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session.getAttribute(ConstantsServlet.USER) != null);
    }
}
