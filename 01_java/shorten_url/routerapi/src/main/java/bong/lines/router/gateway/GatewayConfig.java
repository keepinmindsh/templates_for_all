package bong.lines.router.gateway;

import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.SetPathGatewayFilterFactory;
import org.springframework.cloud.gateway.handler.AsyncPredicate;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;


public class GatewayConfig {

    RouteLocator gateway(SetPathGatewayFilterFactory setPathGatewayFilterFactory){
        Route singleRoute = Route.async()
                .id("text")
                .filter(new OrderedGatewayFilter(setPathGatewayFilterFactory.apply(config -> {
                    config.setTemplate("/hello");
                }), 0))
                .uri("http://localhost:2001/")
                .asyncPredicate(new AsyncPredicate<ServerWebExchange>() {
                    @Override
                    public Publisher<Boolean> apply(ServerWebExchange serverWebExchange) {

                        URI uri = serverWebExchange.getRequest().getURI();
                        String path = uri.getPath();
                        boolean match = path.contains("/hello");

                        return Mono.just(match);
                    }
                }).build();

        return () -> Flux.just(singleRoute);
    }

    private Buildable<Route> getUri(PredicateSpec routeSpec) {
        return routeSpec.path("/real/**").uri("http://localhost:2001");
    }
}
