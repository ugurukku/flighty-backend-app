package com.tutorials.flightyapigateway.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GlobalSecurityFilter implements GlobalFilter {

    @Value("${security.internal.header.name}")
    private String headerName;

    @Value("${security.internal.header.value}")
    private String headerValue;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest modifiedRequest = exchange.getRequest()
                .mutate()
                .header(headerName, headerValue)
                .build();

        ServerWebExchange mutatedExchange = exchange.mutate()
                .request(modifiedRequest)
                .build();

        return chain.filter(mutatedExchange);
    }
}
