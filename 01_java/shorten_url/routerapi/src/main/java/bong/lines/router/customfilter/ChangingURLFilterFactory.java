package bong.lines.router.customfilter;

import bong.lines.router.manger.QueryManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.factory.AbstractChangeRequestUriGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;

@Slf4j
@Component
public class ChangingURLFilterFactory extends AbstractChangeRequestUriGatewayFilterFactory<AbstractGatewayFilterFactory.NameConfig> {

    public ChangingURLFilterFactory() {
        super(NameConfig.class);
    }

    @Override
    protected Optional<URI> determineRequestUri(ServerWebExchange exchange, NameConfig config) {
        String contextPath = exchange.getRequest().getPath().toString();

        String key = contextPath.split("/")[2];

        return Optional.ofNullable(key).map(url -> {
            try {
                log.info("path : {}", url);
                //return new URL("http://localhost:2001/real_test?value=123").toURI();

                // 아래의 코드에서 URL을 정의할 때 전달 받을 Real API URL의 Query String 파라미터가 존재할 경우 반드시 매핑 시켜 전달해야한다.
                return  new URL(QueryManager.getQuery(url)).toURI();
            } catch (MalformedURLException | URISyntaxException e) {
                log.info("Request url is invalid : url={}, error={}", contextPath, e.getMessage());
                return null;
            }
        });
    }
}


