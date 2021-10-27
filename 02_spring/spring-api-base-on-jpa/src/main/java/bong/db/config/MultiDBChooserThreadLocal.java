package bong.db.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Optional;

public class MultiDBChooserThreadLocal extends AbstractRoutingDataSource {	
	protected Object determineCurrentLookupKey() {
		Object resultObj = null;
		
		try {
			if(Optional.ofNullable(DBSelector.get()).isPresent()) {
			    resultObj = Optional.ofNullable(DBSelector.get()).orElse("") ;
			}else {
				resultObj = "wingsent5";
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		return resultObj;
	}
}
