package bong.lines.router.gateway;

import bong.lines.router.configuration.URIConfiguration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GatewayConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder, URIConfiguration uriConfiguration) {

        String httpURI = uriConfiguration.getHttpUrl();

        return builder.routes()
                .route(predicateSpec ->
                        predicateSpec
                                .path("/call")
                                .uri(httpURI))
                .build();
    }
}
