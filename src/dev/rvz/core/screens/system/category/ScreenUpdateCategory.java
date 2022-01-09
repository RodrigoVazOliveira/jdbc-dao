package dev.rvz.core.screens.system.category;

import dev.rvz.core.proxys.IO;
import dev.rvz.core.strategy.ScreenSystemOption;
import dev.rvz.models.Category;
import dev.rvz.services.CategoryService;

public class ScreenUpdateCategory implements ScreenSystemOption {
    private final CategoryService categoryService;

    public ScreenUpdateCategory(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void running() {
        System.out.println("Por favor, digite o id da categoria:");
        Integer id = Integer.parseInt(IO.getScanner().nextLine());
        System.out.println("Por favor, digite o nome do categoria:");
        String name = IO.getScanner().nextLine();

        Category category = new Category.builder()
                .setId(id)
                .setName(name).build();
        this.categoryService.update(category);
        System.out.println("Produto atualizado com sucesso! \u2713");
    }
}
