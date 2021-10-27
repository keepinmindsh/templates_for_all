package bong.db.config.properties;


import bong.comm.factory.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "hotels")
@PropertySource(value = "classpath:config/persistence/dbconfigs.yml", factory = YamlPropertySourceFactory.class)
@Getter
@Setter
public class YamlDBPropertiesForDBConfigs {
    private List<Map<String, DataSource>> dblist;
}
