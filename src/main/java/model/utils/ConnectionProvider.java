package model.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class ConnectionProvider {
    private static BasicDataSource basicDataSource= new BasicDataSource();

    static{
        log.debug("ConnectionProvider.static");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/store");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("root123");
        basicDataSource.setMaxTotal(20);
        basicDataSource.setMinIdle(5);

    }
    public static Connection getConnection() throws SQLException {
        log.debug("Connection created");
        return basicDataSource.getConnection();
    }
}
