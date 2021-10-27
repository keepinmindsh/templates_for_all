package bong.db.config;

import bong.db.config.properties.YamlDBPropertiesForDBConfigs;
import bong.db.config.properties.YamlDBPropertiesForHibernate;
import bong.db.config.properties.YamlDBPropertiesForPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ConfigDataSource {

    private final YamlDBPropertiesForPersistence yamlDBPropertiesForPersistence;
    private final YamlDBPropertiesForHibernate yamlDBPropertiesForHibernate;
    private final YamlDBPropertiesForDBConfigs yamlDBPropertiesForDBConfigs;

    @Bean
    @Lazy
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        localContainerEntityManagerFactoryBean.setPackagesToScan(yamlDBPropertiesForPersistence.getPackagetoscan());

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        localContainerEntityManagerFactoryBean.setJpaProperties(additionalProperties());

        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    @Lazy
    public DataSource dataSource() {

        final AbstractRoutingDataSource dataSource = new MultiDBChooserThreadLocal();

        try {
            Class.forName(yamlDBPropertiesForPersistence.getDriverClassName());
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }

        dataSource.setTargetDataSources(getDataSourceConnector());

        dataSource.afterPropertiesSet();

        return dataSource;
    }

    private Map<Object, Object> getDataSourceConnector() {
        Map<Object,Object> targetDataSources = new HashMap<>();

        try{
            yamlDBPropertiesForDBConfigs.getDblist()
                    .forEach(item -> {
                        for (String key: item.keySet()) {
                            BasicDataSource dataSource = new BasicDataSource();

                            dataSource.setDriverClassName(yamlDBPropertiesForPersistence.getDriverClassName());
                            dataSource.setUrl(item.get(key).getJdbc_url());
                            dataSource.setUsername(item.get(key).getJdbc_user());
                            dataSource.setPassword(item.get(key).getJdbc_password());

                            targetDataSources.put(key, dataSource);
                        }
                    });
        }catch (Exception exception){
            log.error("getDataSourceConnector throw exception!");
            log.error(exception.getMessage());

        }

        return targetDataSources;
    }

    @Bean
    @Lazy
    public PlatformTransactionManager transactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    @Lazy
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
