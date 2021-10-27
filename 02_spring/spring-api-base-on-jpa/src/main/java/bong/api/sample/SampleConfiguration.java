package bong.api.sample;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = SampleConfiguration.class)
@EnableConfigurationProperties
public class SampleConfiguration {
}
