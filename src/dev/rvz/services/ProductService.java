package dev.rvz.services;

import dev.rvz.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private final Connection connection;

    public ProductService(Connection connection) {
        this.connection = connection;
    }


    public void recordNewProduct(Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product VALUES (null, ?, ?)");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Ocorreu um erro ao inserir o produto - erro: " + e.getMessage());
        }
    }

    public List<Product> getAllProducts() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, name, description FROM product;");
            return buildListProducts(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Ocorreu um erro ao buscar os produto - erro: " + e.getMessage());
        }
    }

    private List<Product> buildListProducts(ResultSet resultSet) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            Product product = new Product(id, name, description);

            products.add(product);
        }

        return products;
    }
}
