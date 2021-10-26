package bong.sample.db.config;


import bong.sample.comm.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "hibernate")
@PropertySource(value = "classpath:config/persistence/persistence.yml", factory = YamlPropertySourceFactory.class)
@RequiredArgsConstructor
@Getter
public class YamlDBPropertiesForHibernate {
    private final Map<String, String> hbm2ddl;
    private final String dialect;
}
