package dev.rvz.core.screens.system.category;

import dev.rvz.core.proxys.IO;
import dev.rvz.core.strategy.ScreenSystemOption;
import dev.rvz.models.Category;
import dev.rvz.services.CategoryService;

public class ScreenRecordCategory implements ScreenSystemOption {
    private final CategoryService categoryService;

    public ScreenRecordCategory(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void running() {
        System.out.println("Digite o nome da categoria: ");
        String name = IO.getScanner().nextLine();
        Category category = new Category.builder()
                .setName(name)
                .build();

        this.categoryService.save(category);
        System.out.println("Produto gravado com sucesso! \u2713");
    }
}
