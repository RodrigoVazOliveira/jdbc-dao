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
}
