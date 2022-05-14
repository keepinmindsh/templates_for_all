package bong.lines.register;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackageClasses = RegisterConfiguration.class)
@EnableJpaRepositories(basePackageClasses = RegisterConfiguration.class)
public class RegisterConfiguration {
}
