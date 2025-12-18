package jp.co.sss.crud.filter;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component 
@Order(1) 
public class LoginCheckFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        String path = request.getServletPath();

       
        if (path.startsWith("/list") || path.startsWith("/regist") || 
            path.startsWith("/update") || path.startsWith("/delete")) {
            
            HttpSession session = request.getSession(false);
            if (session == null ) {
                response.sendRedirect(request.getContextPath() + "/");
                return;
            }
           
        }
        chain.doFilter(request, response);
    }
}