package dev.rvz.core.screens.system.category;

import dev.rvz.core.strategy.ScreenSystemOption;
import dev.rvz.models.Category;
import dev.rvz.services.CategoryService;

import java.util.List;

public class ScreenShowAllCategory implements ScreenSystemOption {
    private final CategoryService categoryService;

    public ScreenShowAllCategory(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void running() {
        System.out.println("Listando os produtos.......");
        List<Category> categories = this.categoryService.getAll();
        System.out.println(categories);
    }
}
