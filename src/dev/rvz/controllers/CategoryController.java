package dev.rvz.controllers;

import dev.rvz.models.Category;
import dev.rvz.services.CategoryService;

import java.util.List;

public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public List<Category> getAll() {
        return this.categoryService.getAll();
    }
}
