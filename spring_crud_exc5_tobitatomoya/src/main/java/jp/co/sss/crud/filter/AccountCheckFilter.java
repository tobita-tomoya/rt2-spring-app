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
@Order(2) 
public class AccountCheckFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
       
        if (request.getServletPath().startsWith("/list")) {
            HttpSession session = request.getSession(false);
            boolean isAdmin = false;

            if (session != null) {
                Integer authority = (Integer) session.getAttribute("authority");
                if (authority != null && authority == 2) {
                    isAdmin = true;
                }
            }
            
            request.setAttribute("isAdmin", isAdmin);
        }

        chain.doFilter(request, response);
    }
}