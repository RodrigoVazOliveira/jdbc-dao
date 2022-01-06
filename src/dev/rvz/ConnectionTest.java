package dev.rvz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/loja_virtual",
                    "loja_virtual",
                    "loja_virtual");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}