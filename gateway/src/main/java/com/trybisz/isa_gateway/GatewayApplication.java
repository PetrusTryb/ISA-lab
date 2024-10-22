package com.trybisz.isa_gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(
            RouteLocatorBuilder builder,
            @Value("${routing.isa_gateway.host}") String host,
            @Value("${routing.isa_offer.url}") String offerUrl,
            @Value("${routing.isa_partner.url}") String partnerUrl
    ){
        return builder.routes()
                .route("offer", r -> r
                        .host(host)
                        .and()
                        .path(
                                "/offers",
                                "/offers/{uuid}",
                                "/partners/{uuid}/offers"
                        ).uri(offerUrl)
                )
                .route("partner", r -> r
                        .host(host)
                        .and()
                        .path(
                                "/partners",
                                "/partners/{uuid}"
                        ).uri(partnerUrl)
                )
                .build();
    }
}
