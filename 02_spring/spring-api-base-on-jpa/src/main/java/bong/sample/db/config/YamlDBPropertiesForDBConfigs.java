package bong.sample.db.config;


import bong.sample.comm.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "hotels")
@PropertySource(value = "classpath:config/persistence/dbconfigs.yml", factory = YamlPropertySourceFactory.class)
@RequiredArgsConstructor
@Getter
public class YamlDBPropertiesForDBConfigs {
    private final List<Map<String, String>> dbConfigs;
}
