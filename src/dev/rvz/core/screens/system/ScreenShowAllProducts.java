package dev.rvz.core.screens.system;

import dev.rvz.core.strategy.ScreenSystemOption;
import dev.rvz.models.Product;
import dev.rvz.services.ProductService;

import java.util.List;

public class ScreenShowAllProducts implements ScreenSystemOption {

    private final ProductService productService;

    public ScreenShowAllProducts(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void running() {
        System.out.println("Listando os produtos.......");
        List<Product> products = productService.getAllProducts();
        isListEmpty(products);
    }

    private void showAllProducts(List<Product> products) {
        System.out.printf("|    ID    |    NAME     |    DESCRIPTION               |\n");
        for (Product product : products) {
            System.out.printf("|%4s | %8s      | %16s    \n", product.getId(), product.getName(), product.getDescription());
        }
    }

    private void isListEmpty(List<Product> products) {
        if (products.isEmpty()) {
            System.out.printf("Não existem produtos a serem listados!");
        }

        showAllProducts(products);
     }
}
