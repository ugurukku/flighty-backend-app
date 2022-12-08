package com.tutorials.flightyapigateway.filter;


import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class RouteGatewayFilterFactory extends
        AbstractGatewayFilterFactory<RouteGatewayFilterFactory.Config> {

    public RouteGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> chain.filter(exchange);
    }


    public static class Config {

    }
}
