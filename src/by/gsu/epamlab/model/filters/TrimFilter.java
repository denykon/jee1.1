package by.gsu.epamlab.model.filters;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Trim filter
 */
public class TrimFilter implements Filter {

    private FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String parameterName = (String) enumeration.nextElement();
            String parameterValue = request.getParameter(parameterName);
            request.setAttribute(parameterName, parameterValue.trim());
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
        this.filterConfig = null;
    }
}
