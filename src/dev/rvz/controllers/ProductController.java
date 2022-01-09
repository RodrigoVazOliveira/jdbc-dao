package dev.rvz.controllers;

import dev.rvz.models.Product;
import dev.rvz.services.ProductService;

import java.util.List;

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public List<Product> getAll() {
        return this.productService.getAll();
    }

    public void save(Product product) {
        this.productService.recordNewProduct(product);
    }

    public void remove(Integer id) {
        this.productService.remove(id);
    }

    public void update(Product product) {
        this.productService.updateProduct(product);
    }
}
