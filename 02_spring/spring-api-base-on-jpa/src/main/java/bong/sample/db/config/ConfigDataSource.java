package bong.sample.db.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class ConfigDataSource {

    private final YamlDBPropertiesForPersistence yamlDBPropertiesForPersistence;
    private final YamlDBPropertiesForHibernate yamlDBPropertiesForHibernate;
    private final YamlDBPropertiesForDBConfigs yamlDBPropertiesForDBConfigs;

        @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(yamlDBPropertiesForPersistence.getPackagetoscan());

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        dataSource.setDriverClassName(yamlDBPropertiesForDBConfigs.getHotel().get(0).getJdbc_driverClassName());
        dataSource.setUrl(yamlDBPropertiesForDBConfigs.getHotel().get(0).getJdbc_url());
        dataSource.setUsername(yamlDBPropertiesForDBConfigs.getHotel().get(0).getJdbc_user());
        dataSource.setPassword(yamlDBPropertiesForDBConfigs.getHotel().get(0).getJdbc_password());

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", yamlDBPropertiesForHibernate.getHbm2ddl().get("auto"));
        hibernateProperties.setProperty("hibernate.dialect", yamlDBPropertiesForHibernate.getDialect());

        return hibernateProperties;
    }
}
