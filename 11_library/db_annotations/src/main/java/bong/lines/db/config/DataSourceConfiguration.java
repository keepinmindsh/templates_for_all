package bong.lines.db.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {

    @Bean
    public DataSource routingDataSource() {
        MyRoutingDataSource replicationRoutingDataSource = new MyRoutingDataSource();

        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setUrl("*");
        dataSource.setDriverClass(org.mariadb.jdbc.Driver.class);
        dataSource.setUsername("*");
        dataSource.setPassword("*");

        Map<Object, Object> dataSourceMap = new LinkedHashMap<>();
        dataSourceMap.put("LINES", dataSource);
        replicationRoutingDataSource.setTargetDataSources(dataSourceMap);
        replicationRoutingDataSource.setDefaultTargetDataSource(dataSource);

        return replicationRoutingDataSource;
    }
}
