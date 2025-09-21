package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreConnection implements JdbcConnection{

    public Connection getConnection(){
        try {
            String url = "jdbc:postgresql://localhost:5432/hospital";
            String userName = "postgres";
            String password = "123";
            Connection connection = DriverManager.getConnection(url, userName, password);
            return connection;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
