package com.biterush.user_service.config;

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
        long startedAt = System.nanoTime();
        String requestId = headerOrGenerated(request, "X-Request-Id");
        String traceId = headerOrGenerated(request, "X-Trace-Id");
        String spanId = headerOrGenerated(request, "X-Span-Id");
        MDC.put("requestId", requestId);
        MDC.put("traceId", traceId);
        MDC.put("spanId", spanId);
        putIfPresent("userId", request.getHeader("X-User-Id"));
        response.setHeader("X-Request-Id", requestId);
        response.setHeader("X-Trace-Id", traceId);
        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.put("method", request.getMethod());
            MDC.put("path", request.getRequestURI());
            MDC.put("status", Integer.toString(response.getStatus()));
            MDC.put("durationMs", Long.toString((System.nanoTime() - startedAt) / 1_000_000));
            org.slf4j.LoggerFactory.getLogger(RequestLoggingFilter.class).info("HTTP request completed");
            MDC.clear();
        }
    }

    private static String headerOrGenerated(HttpServletRequest request, String header) {
        String value = request.getHeader(header);
        return value == null || value.isBlank() ? UUID.randomUUID().toString() : value;
    }

    private static void putIfPresent(String key, String value) {
        if (value != null && !value.isBlank()) MDC.put(key, value);
    }
}
