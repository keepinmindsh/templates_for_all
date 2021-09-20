package bong.lines.router.customfilter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.factory.AbstractChangeRequestUriGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.web.server.ServerWebExchange;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;

@Slf4j
public class ChanginURLFilterFactory extends AbstractChangeRequestUriGatewayFilterFactory<AbstractGatewayFilterFactory.NameConfig> {

    public ChanginURLFilterFactory() {
        super(NameConfig.class);
    }

    @Override
    protected Optional<URI> determineRequestUri(ServerWebExchange exchange, NameConfig config) {
        String cookieValue = exchange.getRequest().getCookies().getFirst(config.getName());
        String requestUrl = determineUrlFromCookie(cookieValue);
        return Optional.ofNullable(requestUrl).map(url -> {
            try {
                return new URL(url).toURI();
            }
            catch (MalformedURLException | URISyntaxException e) {
                log.info("Request url is invalid : url={}, error={}", requestUrl,
                        e.getMessage());
                return null;
            }
        });
    }
    }

}
