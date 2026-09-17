package com.bitberush.order_service.config;

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
    protected void doFilterInternal(HttpServletRequest r, HttpServletResponse s, FilterChain c) throws ServletException, IOException {
        long t = System.nanoTime(); String q = id(r, "X-Request-Id"), tr = id(r, "X-Trace-Id"), sp = id(r, "X-Span-Id"); MDC.put("requestId", q); MDC.put("traceId", tr); MDC.put("spanId", sp); put("userId", r.getHeader("X-User-Id")); s.setHeader("X-Request-Id", q); s.setHeader("X-Trace-Id", tr);
        try { c.doFilter(r, s); } finally { MDC.put("method", r.getMethod()); MDC.put("path", r.getRequestURI()); MDC.put("status", Integer.toString(s.getStatus())); MDC.put("durationMs", Long.toString((System.nanoTime() - t) / 1_000_000)); org.slf4j.LoggerFactory.getLogger(RequestLoggingFilter.class).info("HTTP request completed"); MDC.clear(); }
    }
    private static String id(HttpServletRequest r, String h) { String v = r.getHeader(h); return v == null || v.isBlank() ? UUID.randomUUID().toString() : v; }
    private static void put(String k, String v) { if (v != null && !v.isBlank()) MDC.put(k, v); }
}
