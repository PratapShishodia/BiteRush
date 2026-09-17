package com.biterush.api_gateway.config;

import com.biterush.api_gateway.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

@Component
@RequiredArgsConstructor
@Slf4j
public class JWTFilter implements GlobalFilter{

    private final JWTUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        String method = exchange.getRequest().getMethod() != null
                ? exchange.getRequest().getMethod().name()
                : "UNKNOWN";
        Instant startedAt = Instant.now();

        log.debug("Incoming gateway request: method={}, path={}", method, path);
        if (isPublicEndpoint(path)) {
            log.debug("Allowing public gateway request: method={}, path={}", method, path);
            String requestId = headerOrGenerated(exchange, "X-Request-Id");
            String traceId = headerOrGenerated(exchange, "X-Trace-Id");
            String spanId = headerOrGenerated(exchange, "X-Span-Id");
            ServerWebExchange enrichedExchange = exchange.mutate()
                    .request(exchange.getRequest().mutate()
                            .header("X-Request-Id", requestId)
                            .header("X-Trace-Id", traceId)
                            .header("X-Span-Id", spanId)
                            .build())
                    .build();
            enrichedExchange.getResponse().getHeaders().add("X-Request-Id", requestId);
            enrichedExchange.getResponse().getHeaders().add("X-Trace-Id", traceId);
            return logCompletion(enrichedExchange, chain.filter(enrichedExchange), method, path, startedAt,
                    requestId, traceId, spanId, null);
        }

        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.warn("Rejecting unauthorized gateway request: method={}, path={}, reason=missing_bearer_token",
                    method, path);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);
        boolean validToken = jwtUtil.isValid(token);
        if (!validToken) {
            log.warn("Rejecting unauthorized gateway request: method={}, path={}, reason=invalid_token",
                    method, path);

            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

            return exchange.getResponse().setComplete();
        }

        String requestId = headerOrGenerated(exchange, "X-Request-Id");
        String traceId = headerOrGenerated(exchange, "X-Trace-Id");
        String spanId = headerOrGenerated(exchange, "X-Span-Id");
        String userId = jwtUtil.extractUserId(token);
        ServerWebExchange enrichedExchange = exchange.mutate()
                .request(exchange.getRequest().mutate()
                        .header("X-Request-Id", requestId)
                        .header("X-Trace-Id", traceId)
                        .header("X-Span-Id", spanId)
                        .header("X-User-Id", userId == null ? "" : userId)
                        .build())
                .build();
        enrichedExchange.getResponse().getHeaders().add("X-Request-Id", requestId);
        enrichedExchange.getResponse().getHeaders().add("X-Trace-Id", traceId);
        log.debug("Authenticated gateway request: method={}, path={}", method, path);
        return logCompletion(enrichedExchange, chain.filter(enrichedExchange), method, path, startedAt,
                requestId, traceId, spanId, userId);
    }

    private Mono<Void> logCompletion(ServerWebExchange exchange, Mono<Void> request, String method,
                                     String path, Instant startedAt, String requestId, String traceId,
                                     String spanId, String userId) {
        return request
                .doOnSuccess(ignored -> log.atInfo()
                        .addKeyValue("timestamp", Instant.now().toString())
                        .addKeyValue("level", "INFO")
                        .addKeyValue("service", "api-gateway")
                        .addKeyValue("logger", JWTFilter.class.getName())
                        .addKeyValue("traceId", traceId)
                        .addKeyValue("spanId", spanId)
                        .addKeyValue("requestId", requestId)
                        .addKeyValue("userId", userId)
                        .addKeyValue("method", method)
                        .addKeyValue("path", path)
                        .addKeyValue("status", exchange.getResponse().getStatusCode() == null ? 500 : exchange.getResponse().getStatusCode().value())
                        .addKeyValue("durationMs", Duration.between(startedAt, Instant.now()).toMillis())
                        .log("HTTP request completed"))
                .doOnError(error -> log.error(
                        "Gateway request failed: method={}, path={}, durationMs={}",
                        method, path, Duration.between(startedAt, Instant.now()).toMillis(), error));
    }

        private String headerOrGenerated(ServerWebExchange exchange, String header) {
                String value = exchange.getRequest().getHeaders().getFirst(header);
                return value == null || value.isBlank() ? java.util.UUID.randomUUID().toString() : value;
        }


    private boolean isPublicEndpoint(String path) {
        return path.startsWith("/api/auth/signup")
                || path.startsWith("/api/auth/login")
                || path.startsWith("/api/auth/refresh")
                || path.startsWith("/api/auth/logout")
                || path.startsWith("/api/auth/activate")
                || path.startsWith("/api/auth/send-otp")
                || path.startsWith("/api/auth/verify-otp")
                || path.startsWith("/api/auth/forget-password")
                || path.startsWith("/api/graphql")
                || path.startsWith("/api/rating/")
                || path.startsWith("/actuator");
    }
}
