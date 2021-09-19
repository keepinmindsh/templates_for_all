package bong.lines.router.gateway;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    RouteLocator gateway (RouteLocatorBuilder rlb) {
        return rlb
                .routes()
                .route( routeSpec ->
                        getUri(routeSpec)
                        )
                .route( routeSpec ->
                        routeSpec.path("/hello")
                                .uri("http://localhost:2001/")
                        )
                .build();
    }

    private Buildable<Route> getUri(PredicateSpec routeSpec) {
        return routeSpec.path("/real/**")
//                .filters(
//                        gatewayFilterSpec -> {
//
//                            gatewayFilterSpec.setPath("/real");
//
//                            return gatewayFilterSpec;
//                        }
//                )
                .uri("http://localhost:2001");
    }

}
