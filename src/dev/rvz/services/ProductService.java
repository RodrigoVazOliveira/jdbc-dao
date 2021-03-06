package dev.rvz.services;

import dev.rvz.models.Category;
import dev.rvz.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductService {

    private final Connection connection;
    private final CategoryService categoryService;

    public ProductService(Connection connection, CategoryService categoryService) {
        this.connection = connection;
        this.categoryService = categoryService;
    }

    public void recordNewProduct(Product product) {
        String sql = "INSERT INTO product VALUES (null, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getCategory().getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Ocorreu um erro ao inserir o produto - erro: " + e.getMessage());
        }
    }

    public List<Product> getAll() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, description, id_category FROM product;");
            return getProducts(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Ocorreu um erro ao buscar os produto - erro: " + e.getMessage());
        }
    }

    public void updateProduct(Product product) {
        Optional<Product> optionalProduct = findById(product.getId());

        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Produto com id " + product.getId() + " não existe no sistema!");
        }

        String sql = "UPDATE product SET name = ?, description=? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
                throw new RuntimeException("Ocorreu um erro ao tentar atualizar o produto - erro: " + e.getMessage());
        }
    }

    public void remove(Integer id) {
        Optional<Product> optionalProduct = findById(id);

        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Produto com id " + id + " não existe no sistema!");
        }

        String sql = "DELETE FROM product WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Ocorreu um erro ao tentar remover o produto - erro: " + e.getMessage());
        }
    }

    private List<Product> getProducts(ResultSet resultSet) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            products.add(createProduct(resultSet));
        }

        return products;
    }

    private Product createProduct(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        Integer idCategory = resultSet.getInt("id_category");

        Optional<Category> optionalCategory = categoryService.findById(idCategory);

        if (optionalCategory.isEmpty()) {
            throw new RuntimeException("categoria do produto não localizado!");
        }

        return new Product(id, name, description, optionalCategory.get());
    }

    private Optional<Product> findById(Integer id) {
        String sql = "SELECT id, name, description, id_category FROM product WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getProduct(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Ocorreu um erro ao buscar o produto com id " + id + " - erro: " + e.getMessage());
        }
    }

    private Optional<Product> getProduct(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return Optional.of(createProduct(resultSet));
        } else {
            return Optional.empty();
        }
    }
}
