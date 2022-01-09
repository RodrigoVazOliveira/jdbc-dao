package dev.rvz.core.screens.system.products;

import dev.rvz.core.proxys.IO;
import dev.rvz.core.strategy.ScreenSystemOption;
import dev.rvz.models.Category;
import dev.rvz.models.Product;
import dev.rvz.services.ProductService;

public class ScreenUpdateProduct implements ScreenSystemOption {
    private final ProductService productService;

    public ScreenUpdateProduct(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void running() {
        System.out.println("Por favor, digite o id do produto:");
        Integer id = Integer.parseInt(IO.getScanner().nextLine());
        System.out.println("Por favor, digite o nome do produto:");
        String name = IO.getScanner().nextLine();
        System.out.println("Por favor, digite a descricao do produto:");
        String description = IO.getScanner().nextLine();

        Category category = new Category.builder().build();
        Product product = new Product(id, name, description, category);
        productService.updateProduct(product);
        System.out.println("Produto atualizado com sucesso! \u2713");
    }
}
