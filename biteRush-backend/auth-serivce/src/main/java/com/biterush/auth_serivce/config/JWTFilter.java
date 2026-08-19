package com.biterush.auth_serivce.config;

import com.biterush.auth_serivce.utils.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        System.out.println("Incoming request: " + request.getServletPath());
        log.debug("Incoming request: {}", request.getServletPath());
        return path.equals("/api/v1/auth/register/**") ||
                path.equals("/api/v1/auth/login/**") ||
                path.equals("/api/v1/auth/refresh/**") ||
                path.equals("/api/v1/auth/sendOTP/**") ||
                path.equals("/api/v1/auth/forget-password/**") ||
                path.equals("/api/v1/auth/activate-profile/**");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        String userName = null;
        String header = request.getHeader("Authorization");

        if(header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
            try{
                userName = jwtUtil.extractUsername(token);
            }catch (Exception e){
                log.debug("Invalid token: {}",e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"message\":\"Invalid or expired token\"}");
                return;
            }
        }

        filterChain.doFilter(request,response);
    }
}
