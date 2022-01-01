package bong.lines.db.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;
import java.util.Optional;

@Slf4j
public class MyRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
    }
    @Override
    protected Object determineCurrentLookupKey() {
        return Optional.ofNullable(DBThreadLocal.get()).orElse("LINES").toString();
    }
}
