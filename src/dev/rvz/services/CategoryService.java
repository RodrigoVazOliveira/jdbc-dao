package dev.rvz.services;

import dev.rvz.models.Category;
import dev.rvz.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryService {
    private final Connection connection;

    public CategoryService(Connection connection) {
        this.connection = connection;
    }

    public void save(Category category) {
        String sql = "INSERT INTO category VALUES (NULL, ?);";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Ocorreu um erro ao tentar inserir uma nova categoria - erro: " + e.getMessage());
        }
    }

    public List<Category> getAll() {
        String sql = "SELECT " +
                "c.id, c.name, p.id, p.name, p.description " +
                "FROM category c, product p " +
                "WHERE c.id = p.id_category;";
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            return getCategories(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("ocorreu um erro ao consultar o banco de dados - erro: " + e.getMessage());
        }
    }

    public void update(Category category) {
        Optional<Category> optionalCategory = findById(category.getId());

        if (optionalCategory.isEmpty()) {
            throw new RuntimeException("Categoria com id " + category.getId() + " não existe no sistema!");
        }

        String sql = "UPDATE category SET name=? WHERE id = ?;";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Ocorreu um erro ao tentar atualizar a categoria - erro: " + e.getMessage());
        }
    }

    public void remove(Integer id) {
        Optional<Category> optionalCategory = findById(id);

        if (optionalCategory.isEmpty()) {
            throw new RuntimeException("Produto com id " + id + " não existe no sistema!");
        }

        String sql = "DELETE FROM category WHERE id = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Ocorreu um erro ao tentar remover a categoria - erro: " + e.getMessage());
        }
    }

    public Optional<Category> findById(Integer id) {
        String sql = "SELECT id, name FROM category WHERE id = ?;";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getCategory(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Ocorreu um erro ao buscar a categoria com id " + id + " - erro: " + e.getMessage());
        }
    }

    private Optional<Category> getCategory(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Category category = new Category(id, name, new ArrayList<>());
            return Optional.of(category);
        }

        return Optional.empty();
    }

    private List<Category> getCategories(ResultSet resultSet) throws SQLException {
        List<Category> categories = new ArrayList<>();
        while (resultSet.next()) {
            Integer idCategory = resultSet.getInt(1);
            String nameCategory = resultSet.getString(2);
            Integer idProduct = resultSet.getInt(3);
            String nameProduct = resultSet.getString(4);
            String descriptionProduct = resultSet.getString(5);

            createCategoryAndProduct(categories, idCategory, nameCategory, idProduct, nameProduct, descriptionProduct);
        }

        return categories;
    }

    private void createCategoryAndProduct(List<Category> categories, Integer idCategory, String nameCategory, Integer idProduct, String nameProduct, String descriptionProduct) {
        Category category;
        Category categoryContains = new Category.builder().setId(idCategory).build();
        if (!categories.contains(categoryContains)) {
            category = new Category(idCategory, nameCategory, new ArrayList<>());
            category.getProducts().add(new Product(idProduct, nameProduct, descriptionProduct, category));
            categories.add(category);
        } else {
            category = getCategoryByProduct(categories, idCategory);
            category.getProducts().add(new Product(idProduct, nameProduct, descriptionProduct, category));
        }
    }

    private Category getCategoryByProduct(List<Category> categories, Integer idCategory) {
        for (Category category : categories) {
            if (category.getId().equals(idCategory)) {
                return category;
            }
        }

        throw new RuntimeException("Ocorreu um erro ao listar todas as categorias");
    }
}
