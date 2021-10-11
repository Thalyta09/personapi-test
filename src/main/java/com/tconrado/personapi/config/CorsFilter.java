package com.tconrado.personapi.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsFilter implements Filter {

    public void doFilter (ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Acess-Control-Allow-Origin", "*");
        response.setHeader("Acess-Control-Allow-Methods", "POST, PUT, GET, DELETE");
        response.setHeader("Acess-Control-Max-Age", "3600");
        response.setHeader("Acess-Control-Allow-Headers",
                "Content-Type, Authorization, Content-Length, X-Requested-With");
        chain.doFilter(req, res);
    }
}
