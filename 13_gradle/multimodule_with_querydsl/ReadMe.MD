- https://zetcode.com/springboot/environment/#:~:text=Spring%20is%20a%20popular%20Java,the%20current%20application%20is%20running.
- https://www.netsurfingzone.com/jpa/how-to-get-jpa-entitymanager-in-spring-boot/
- https://donghyeon.dev/spring/2020/08/18/Spring-Annotation%EC%9D%98-%EC%9B%90%EB%A6%AC%EC%99%80-Custom-Annotation-%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0/

# JPA Entity Package Scan 

```java

package bong.lines.jpa;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@ComponentScan(basePackageClasses = JPAConfiguration.class)
@Configuration
public class JPAConfiguration {

    private final Environment env;

    public JPAConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {

        String username = env.getProperty("spring.datasource.username");
        String password = env.getProperty("spring.datasource.password");
        String driverClass = env.getProperty("spring.datasource.driver-class-name");
        String url = env.getProperty("spring.datasource.url");

        return DataSourceBuilder.create().username(username).password(password).url(url).driverClassName(driverClass)
                .build();
    }

    @Bean
    public JpaTransactionManager jpaTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    private HibernateJpaVendorAdapter vendorAdaptor() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        return vendorAdapter;
    }

    private Properties addProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
        properties.setProperty("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
        // we can add
        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(env.getProperty("spring.entity.packagescan"));
        entityManagerFactoryBean.setJpaProperties(addProperties());

        return entityManagerFactoryBean;
    }
}

```
