package bong.sample.db.config;

import bong.sample.comm.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "persistence")
@PropertySource(value = "classpath:config/persistence/persistence.yml", factory = YamlPropertySourceFactory.class)
@RequiredArgsConstructor
@Getter
public class YamlDBPropertiesForPersistence {
    private final String packagetoscan;
}
