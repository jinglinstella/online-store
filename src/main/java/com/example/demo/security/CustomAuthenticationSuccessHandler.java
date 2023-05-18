package com.example.demo.security;

import com.example.demo.domain.Owner;
import com.example.demo.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private OwnerService ownerService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
    	System.out.println("start authentication.......");
        String email = authentication.getName();
        Owner owner = ownerService.findOwnerByEmail(email);

        if (owner != null) {
            HttpSession session = request.getSession();
            System.out.println("userId: " + owner.getOwnerId());
            session.setAttribute("userId", owner.getOwnerId());
            session.setAttribute("user", owner);
        }

        response.sendRedirect("/welcome");
    }
}
