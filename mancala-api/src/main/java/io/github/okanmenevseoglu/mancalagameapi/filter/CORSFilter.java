package io.github.okanmenevseoglu.mancalagameapi.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This is a class to implement a filter for enabling Cross-Origin resource sharing for RESTful clients.
 */
@Component
public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * This method is used to add necessary headers for the CORS filter.
     *
     * @param request  request
     * @param response response to add necessary headers
     * @param chain    for applying the filter
     * @throws IOException      for read write errors
     * @throws ServletException for servlet errors
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "POST, GET");
        res.setHeader("Access-Control-Allow-Headers", "Content-Type");

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }
}
