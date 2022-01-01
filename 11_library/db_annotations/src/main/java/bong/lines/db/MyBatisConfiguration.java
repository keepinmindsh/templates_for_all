package bong.lines.db;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = MyBatisConfiguration.class)
@EnableConfigurationProperties
public class MyBatisConfiguration {
}
