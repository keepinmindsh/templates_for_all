package bong.sample.db.config;

import bong.sample.comm.YamlPropertySourceFactory;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "persistence")
@PropertySource(value = "classpath:config/persistence/persistence.yml", factory = YamlPropertySourceFactory.class)
@Getter
public class YamlDBPropertiesForPersistence {
    @Value("${persistence.packagetoscan}")
    private String packagetoscan;
}
