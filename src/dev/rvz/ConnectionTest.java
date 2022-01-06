package dev.rvz;

import dev.rvz.connections.CreateConnectionFactory;
import dev.rvz.models.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectionTest {

    public static void main(String[] args) {
        CreateConnectionFactory connectionFactory = new CreateConnectionFactory();
        try {
            Connection connection = connectionFactory.getConnection();
            selectAllProducts(connection);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void selectAllProducts(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id, name, description FROM product;");
        ArrayList<Product> products = buildListProducts(resultSet);
        System.out.println(products);
    }

    private static ArrayList<Product> buildListProducts(ResultSet resultSet) throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        while(resultSet.next()) {
            Product product = new Product(resultSet.getInt("id"), resultSet.getString("name"),
                    resultSet.getString("description"));
            products.add(product);
        }

        return products;
    }
}