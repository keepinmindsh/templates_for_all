package bong.db;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = DBConfiguration.class)
@EnableConfigurationProperties
public class DBConfiguration {
}
