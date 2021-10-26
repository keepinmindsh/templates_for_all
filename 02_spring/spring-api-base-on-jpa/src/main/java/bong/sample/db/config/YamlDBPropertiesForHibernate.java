package bong.sample.db.config;


import bong.sample.comm.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "hibernate")
@PropertySource(value = "classpath:config/persistence/persistence.yml", factory = YamlPropertySourceFactory.class)
//@PropertySource(value = "classpath:config/persistence/persistence.yml")
@Getter
@Setter
public class YamlDBPropertiesForHibernate {
    private Map<String, String> hbm2ddl;
    private String dialect;
}
