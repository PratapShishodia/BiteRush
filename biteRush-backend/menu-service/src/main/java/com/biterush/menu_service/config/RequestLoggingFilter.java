package com.biterush.menu_service.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class RequestLoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long startedAt = System.nanoTime(); String requestId = id(request, "X-Request-Id"); String traceId = id(request, "X-Trace-Id"); String spanId = id(request, "X-Span-Id");
        MDC.put("requestId", requestId); MDC.put("traceId", traceId); MDC.put("spanId", spanId); put("userId", request.getHeader("X-User-Id")); response.setHeader("X-Request-Id", requestId); response.setHeader("X-Trace-Id", traceId);
        try { filterChain.doFilter(request, response); } finally { MDC.put("method", request.getMethod()); MDC.put("path", request.getRequestURI()); MDC.put("status", Integer.toString(response.getStatus())); MDC.put("durationMs", Long.toString((System.nanoTime() - startedAt) / 1_000_000)); org.slf4j.LoggerFactory.getLogger(RequestLoggingFilter.class).info("HTTP request completed"); MDC.clear(); }
    }
    private static String id(HttpServletRequest r, String h) { String v = r.getHeader(h); return v == null || v.isBlank() ? UUID.randomUUID().toString() : v; }
    private static void put(String k, String v) { if (v != null && !v.isBlank()) MDC.put(k, v); }
}
