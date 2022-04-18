package com.example.gw;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@EnableDiscoveryClient
@SpringBootApplication
@RequiredArgsConstructor
public class GwApplication {

    public static void main(String[] args) {
        SpringApplication.run(GwApplication.class, args);
    }

    private final AuthFilter authFilter;

    @Bean
    public RouteLocator MyRoute(RouteLocatorBuilder builder) {
        return builder//
                .routes()//
                .route("svc1", r -> r.path("/svc1/**")
                        //
                        .filters(f -> f.stripPrefix(1))
                        //
                        .uri("lb://svc1"))//
                .route("svc2", r -> r.path("/svc2/**")
                        //
                        .filters(f -> f.filter(authFilter).stripPrefix(1))
                        //
                        .uri("lb://svc2"))//
                .build();
    }

}
