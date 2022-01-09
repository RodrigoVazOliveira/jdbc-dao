package dev.rvz.core.screens.system.category;

import dev.rvz.core.proxys.IO;
import dev.rvz.core.strategy.ScreenSystemOption;
import dev.rvz.models.Category;
import dev.rvz.services.CategoryService;

public class ScreenRemoveCategory implements ScreenSystemOption {
    private final CategoryService categoryService;

    public ScreenRemoveCategory(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void running() {
        System.out.println("Digite o id da categoria que deseja remover: ");
        Integer id = Integer.parseInt(IO.getScanner().nextLine());
        Category category = new Category.builder()
                .setId(id).build();
        this.categoryService.remove(id);
        System.out.println("Categoria removido! \u2713");
    }
}
