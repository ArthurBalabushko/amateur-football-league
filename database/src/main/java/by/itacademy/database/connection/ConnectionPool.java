package by.itacademy.database.connection;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConnectionPool {

    private static DataSource dataSource;

    static {
        initConnectionPool();
    }

    private static void initConnectionPool() {
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setDriverClassName(PropertyManager.get("db.driver"));
        poolProperties.setUrl(PropertyManager.get("db.url"));
        poolProperties.setUsername(PropertyManager.get("db.username"));
        poolProperties.setPassword(PropertyManager.get("db.password"));
        poolProperties.setMaxActive(Integer.parseInt(PropertyManager.get("db.pool.size")));
        dataSource = new DataSource(poolProperties);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
