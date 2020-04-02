package com.example.config.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
    public static final String ALLOW_ORIGIN_HEADER = "Access-Control-Allow-Origin";
    public static final String ALLOW_METHODS_HEADER = "Access-Control-Allow-Methods";
    public static final String ALLOW_HEADERS = "Access-Control-Allow-Headers";
    public static final String MAX_AGE_HEADER = "Access-Control-Max-Age";
    public static final String ALLOWED_ORIGINS = "*";
    public static final String ALLOWED_METHODS = "POST, GET, PUT, OPTIONS, DELETE";
    public static final String MAX_AGE = "3600";
    public static final String ALLOWED_HEADERS = "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse resp,
                         FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;

        response.setHeader(ALLOW_ORIGIN_HEADER, ALLOWED_ORIGINS);
        response.setHeader(ALLOW_METHODS_HEADER, ALLOWED_METHODS);
        response.setHeader(MAX_AGE_HEADER, MAX_AGE);
        response.setHeader(ALLOW_HEADERS, ALLOWED_HEADERS);

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(req, resp);
        }

    }

    @Override
    public void destroy() {

    }
}
