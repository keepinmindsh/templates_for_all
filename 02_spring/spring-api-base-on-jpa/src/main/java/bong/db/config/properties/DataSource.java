package bong.db.config.properties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataSource {
    private String jdbc_driverClassName;
    private String jdbc_url;
    private String jdbc_user;
    private String jdbc_password;
}
