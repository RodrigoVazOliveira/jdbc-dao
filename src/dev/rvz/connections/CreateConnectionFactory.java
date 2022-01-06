package dev.rvz.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateConnectionFactory {

    private Connection connection;

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/loja_virtual",
                    "loja_virtual",
                    "loja_virtual");
            return connection;
        }

        return connection;
    }
}