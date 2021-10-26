package bong.sample.db.config;


import bong.sample.comm.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "hotels")
@PropertySource(value = "classpath:config/persistence/dbconfigs.yml", factory = YamlPropertySourceFactory.class)
//@PropertySource(value = "classpath:config/persistence/dbconfigs.yml")

@Getter
@Setter
public class YamlDBPropertiesForDBConfigs {
    private List<DataSource> hotel;
}
