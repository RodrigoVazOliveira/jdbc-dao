package dev.rvz;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import dev.rvz.connections.CreateConnectionFactory;
import dev.rvz.controllers.CategoryController;
import dev.rvz.controllers.ProductController;
import dev.rvz.services.CategoryService;
import dev.rvz.services.ProductService;
import dev.rvz.windows.ProductWindow;

import javax.swing.*;
import java.sql.SQLException;

public class StoreVirtualGUIApplication {
    public static void main(String[] args) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mariadb://localhost:3306/loja_virtual");
        comboPooledDataSource.setUser("loja_virtual");
        comboPooledDataSource.setPassword("loja_virtual");
        comboPooledDataSource.setMaxPoolSize(15);

        CreateConnectionFactory createConnectionFactory = new CreateConnectionFactory(comboPooledDataSource);
        CategoryService categoryService = new CategoryService(createConnectionFactory.getConnection());
        ProductService productService = new ProductService(createConnectionFactory.getConnection(), categoryService);

        ProductController productController = new ProductController(productService);
        CategoryController categoryController = new CategoryController(categoryService);

        ProductWindow productWindow = new ProductWindow("Produtos", productController, categoryController);
        productWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createConnectionFactory.getConnection().close();
    }
}
