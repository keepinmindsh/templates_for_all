package bong.db.config.properties;

import bong.comm.factory.YamlPropertySourceFactory;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:config/persistence/persistence.yml", factory = YamlPropertySourceFactory.class)
@Getter
public class YamlDBPropertiesForPersistence {
    @Value("${persistence.packagetoscan}")
    private String packagetoscan;

    @Value("${driver.className}")
    private String driverClassName;
}
