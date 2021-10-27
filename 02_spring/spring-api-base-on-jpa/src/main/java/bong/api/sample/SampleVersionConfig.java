package bong.api.sample;

import bong.comm.factory.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "version")
@PropertySource(value = "classpath:config/version/version.yml", factory = YamlPropertySourceFactory.class)
@Getter
@Setter
public class SampleVersionConfig {
    private String info;
}
