package com.tutorials.flightyapigateway.config;

import static com.tutorials.flightyapigateway.util.Constant.AUTH_MS_ID;
import static com.tutorials.flightyapigateway.util.Constant.AUTH_MS_PATH;
import static com.tutorials.flightyapigateway.util.Constant.AUTH_MS_URI;
import static com.tutorials.flightyapigateway.util.Constant.MS_BOOKING_ID;
import static com.tutorials.flightyapigateway.util.Constant.MS_BOOKING_PATH;
import static com.tutorials.flightyapigateway.util.Constant.MS_BOOKING_URI;
import static com.tutorials.flightyapigateway.util.Constant.MS_EXCEL_ADAPTER_ID;
import static com.tutorials.flightyapigateway.util.Constant.MS_EXCEL_ADAPTER_PATH;
import static com.tutorials.flightyapigateway.util.Constant.MS_EXCEL_ADAPTER_URI;
import static com.tutorials.flightyapigateway.util.Constant.MS_FLIGHT_ID;
import static com.tutorials.flightyapigateway.util.Constant.MS_FLIGHT_PATH;
import static com.tutorials.flightyapigateway.util.Constant.MS_FLIGHT_URI;
import static com.tutorials.flightyapigateway.util.Constant.MS_USER_ID;
import static com.tutorials.flightyapigateway.util.Constant.MS_USER_PATH;
import static com.tutorials.flightyapigateway.util.Constant.MS_USER_URI;

import com.tutorials.flightyapigateway.filter.RouteGatewayFilterFactory;
import com.tutorials.flightyapigateway.model.ResponseModel;
import java.time.LocalDateTime;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder, RouteGatewayFilterFactory routeFactory) {
        return builder.routes()
                .route(AUTH_MS_ID, spec -> buildRoute(routeFactory, spec, AUTH_MS_PATH, AUTH_MS_URI))
                .route(MS_USER_ID, spec -> buildRoute(routeFactory, spec, MS_USER_PATH, MS_USER_URI))
                .route(MS_FLIGHT_ID, spec -> buildRoute(routeFactory, spec, MS_FLIGHT_PATH, MS_FLIGHT_URI))
                .route(MS_BOOKING_ID, spec -> buildRoute(routeFactory, spec, MS_BOOKING_PATH, MS_BOOKING_URI))
                .route(MS_EXCEL_ADAPTER_ID, spec -> buildRoute(routeFactory, spec, MS_EXCEL_ADAPTER_PATH, MS_EXCEL_ADAPTER_URI))
                .build();
    }

    private Buildable<Route> buildRoute(RouteGatewayFilterFactory routeFactory, PredicateSpec predicateSpec,
                                        String path, String uri) {
        return predicateSpec.path(path)
                .filters(f -> addGatewayFilters(routeFactory, f))
                .uri(uri);
    }

    private GatewayFilterSpec addGatewayFilters(RouteGatewayFilterFactory routeFactory, GatewayFilterSpec filter) {
        return filter
                .rewritePath("/(?<path>.*)", "/$\\{path}")
                .filter(routeFactory.apply(new RouteGatewayFilterFactory.Config()))
                .modifyResponseBody(
                        Object.class,
                        ResponseModel.class,
                        MediaType.APPLICATION_JSON_VALUE,
                        this::generateResponseBody);
    }

    private Mono<ResponseModel> generateResponseBody(ServerWebExchange exchange, Object responseBody) {
        return Mono.just(
                ResponseModel.of(
                        exchange.getResponse().getStatusCode(),
                        LocalDateTime.now(),
                        responseBody));
    }
}
