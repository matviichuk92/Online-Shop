package online.shop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't find MySQL Driver", e);
        }
    }

    public static Connection getConnection() {
        Properties connectionProperties = new Properties();
        connectionProperties.put("user", "root");
        connectionProperties.put("password", "Romaha12345");
        String url = "jdbc:mysql://localhost:3306/shop?serverTimezone=UTC";
        try {
            Connection connection = DriverManager.getConnection(url, connectionProperties);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Can't establish connect to DB!");
        }
    }
}
