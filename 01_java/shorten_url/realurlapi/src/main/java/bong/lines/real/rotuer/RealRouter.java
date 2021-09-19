package bong.lines.real.rotuer;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class RealRouter {

    @Bean
    public RouterFunction<ServerResponse> route(RealHandler realHandler){
        return RouterFunctions
                .route(GET("/real/hello1").and(accept(MediaType.APPLICATION_JSON)), realHandler::getRealData)
                .andRoute(GET("/real/hello2").and(accept(MediaType.APPLICATION_JSON)), realHandler::getRealData)
                .andRoute(GET("/test").and(accept(MediaType.APPLICATION_JSON)), realHandler::getRealData);
    }
}
