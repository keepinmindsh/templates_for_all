package bong.db.config;

import bong.comm.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "persistence")
@PropertySource(value = "classpath:config/persistence/persistence.yml", factory = YamlPropertySourceFactory.class)
@Getter
@Setter
public class YamlDBPropertiesForPersistence {
    private String packagetoscan;
}
