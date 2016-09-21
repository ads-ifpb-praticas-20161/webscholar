package jsfbeans;

import dac.webscholar.shared.entities.ScholarUser;
import dac.webscholar.shared.entities.UserType;
import dac.webscholar.shared.utils.RoleUriMap;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by marcusviniv on 20/09/2016.
 */
@WebFilter("/*")
public class WebScholarFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        ScholarUser user = (ScholarUser) req.getSession().getAttribute("user");
        UserType credential;

        if(user == null){
            credential = UserType.PUBLIC;
        }
        else{
            credential = user.getUserType();
        }

        RoleUriMap path = new RoleUriMap(credential.getUriMap(), req.getRequestURI());

        if( !path.findMatches().isAllowed() ){
            System.out.println("nao possui permissao");
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