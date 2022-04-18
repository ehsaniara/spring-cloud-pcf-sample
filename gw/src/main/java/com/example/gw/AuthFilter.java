package com.example.gw;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Component
public class AuthFilter implements GatewayFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // check Authorization exist
        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            var response = exchange.getResponse();
            //or 401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        //get AUTHORIZATION header...
        final String token = request.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION).stream().findFirst().orElseThrow();

        log.info("token: {}", token);

        exchange.getRequest().mutate().header("id", UUID.randomUUID().toString()).build();
        return chain.filter(exchange);
    }
}
