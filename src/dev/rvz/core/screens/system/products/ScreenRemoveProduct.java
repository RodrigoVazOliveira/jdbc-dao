package dev.rvz.core.screens.system.products;

import dev.rvz.core.proxys.IO;
import dev.rvz.core.strategy.ScreenSystemOption;
import dev.rvz.services.ProductService;

public class ScreenRemoveProduct implements ScreenSystemOption {
    private final ProductService productService;

    public ScreenRemoveProduct(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void running() {
        System.out.println("Por favor, digite o id do produto a ser removido: ");
        Integer id = Integer.parseInt(IO.getScanner().nextLine());
        productService.remove(id);
        System.out.println("Produto removido! \u2713");
    }
}
