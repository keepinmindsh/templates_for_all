package sample;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {SampleConfiguration.class})
public class SampleConfiguration {
}
