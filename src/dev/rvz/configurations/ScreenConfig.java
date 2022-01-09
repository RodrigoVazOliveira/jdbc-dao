package dev.rvz.configurations;

import dev.rvz.core.screens.system.ScreenRecordProduct;
import dev.rvz.core.screens.system.ScreenRemoveProduct;
import dev.rvz.core.screens.system.ScreenShowAllProduct;
import dev.rvz.core.screens.system.ScreenUpdateProduct;
import dev.rvz.core.strategy.OptionsStrategy;
import dev.rvz.core.strategy.ScreenSystemOption;
import dev.rvz.services.CategoryService;
import dev.rvz.services.ProductService;

import java.sql.Connection;
import java.util.HashMap;

public class ScreenConfig {

    private final Connection connection;

    public ScreenConfig(Connection connection) {
        this.connection = connection;
    }

    public OptionsStrategy build()  {
        CategoryService categoryService = new CategoryService(connection);
        ProductService productService = new ProductService(connection, categoryService);

        HashMap<Integer, ScreenSystemOption> screens = new HashMap<>();
        screens.put(1, new ScreenRecordProduct(productService));
        screens.put(2, new ScreenShowAllProduct(productService));
        screens.put(3, new ScreenUpdateProduct(productService));
        screens.put(4, new ScreenRemoveProduct(productService));

        return new OptionsStrategy(screens);
    }

    public Connection getConnection() {
        return connection;
    }
}
