package jsfbeans;

import dac.webscholar.shared.entities.ScholarUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by marcusviniv on 20/09/2016.
 */
@WebFilter(urlPatterns = "/salas/cadastro_sala.xhtml")
public class WebScholarFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        ScholarUser user = (ScholarUser) req.getSession().getAttribute("user");
        if(user == null){
            req.getRequestDispatcher("/login.xhtml").forward(servletRequest, servletResponse);
        }
        else if(user.getUserType().isBlocked("cadastro_sala.xhtml")){
            req.getRequestDispatcher("/login.xhtml").forward(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

/*
@WebFilter(urlPatterns = "/faces/hello.xhtml")
public class Filtro implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse rep = (HttpServletResponse) response;

        Pessoa pessoa = (Pessoa) req.getSession().getAttribute("pessoa");

        if (pessoa == null) {
            req.getRequestDispatcher("/faces/index.xhtml").forward(request, response);
//            rep.sendRedirect("http://localhost:8088/filtro-jsf/faces/index.xhtml");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}*/