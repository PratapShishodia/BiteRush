package com.biterush.auth_service.config;

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

    private static final String REQUEST_ID = "requestId";
    private static final String TRACE_ID = "traceId";
    private static final String SPAN_ID = "spanId";
    private static final String USER_ID = "userId";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        long startedAt = System.nanoTime();
        String requestId = headerOrGenerated(request, "X-Request-Id");
        String traceId = headerOrGenerated(request, "X-Trace-Id");
        String spanId = headerOrGenerated(request, "X-Span-Id");

        put(REQUEST_ID, requestId);
        put(TRACE_ID, traceId);
        put(SPAN_ID, spanId);
        putIfPresent(USER_ID, request.getHeader("X-User-Id"));
        response.setHeader("X-Request-Id", requestId);
        response.setHeader("X-Trace-Id", traceId);

        try {
            filterChain.doFilter(request, response);
        } finally {
            long durationMs = (System.nanoTime() - startedAt) / 1_000_000;
            put("method", request.getMethod());
            put("path", request.getRequestURI());
            put("status", Integer.toString(response.getStatus()));
            put("durationMs", Long.toString(durationMs));
            try {
                org.slf4j.LoggerFactory.getLogger(RequestLoggingFilter.class)
                        .info("HTTP request completed");
            } finally {
                MDC.remove(REQUEST_ID);
                MDC.remove(TRACE_ID);
                MDC.remove(SPAN_ID);
                MDC.remove(USER_ID);
                MDC.remove("method");
                MDC.remove("path");
                MDC.remove("status");
                MDC.remove("durationMs");
            }
        }
    }

    private static String headerOrGenerated(HttpServletRequest request, String header) {
        String value = request.getHeader(header);
        return value == null || value.isBlank() ? UUID.randomUUID().toString() : value;
    }

    private static void put(String key, String value) {
        MDC.put(key, value);
    }

    private static void putIfPresent(String key, String value) {
        if (value != null && !value.isBlank()) {
            MDC.put(key, value);
        }
    }
}
