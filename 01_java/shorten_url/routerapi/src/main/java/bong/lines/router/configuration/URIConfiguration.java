package bong.lines.router.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class URIConfiguration {

    private String httpUrl = "http://localhost:2001/real";

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }
}
