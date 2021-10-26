package bong.sample.db.config;


import bong.sample.comm.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "hotels")
@PropertySource(value = "classpath:config/persistence/dbconfigs.yml", factory = YamlPropertySourceFactory.class)
@Getter
@Setter
public class YamlDBPropertiesForDBConfigs {
    private List<DataSource> hotel;
}
