package dev.rvz.core.screens.system;

import dev.rvz.core.proxys.IO;
import dev.rvz.core.strategy.ScreenSystemOption;
import dev.rvz.models.Product;
import dev.rvz.services.ProductService;

public class ScreenRecordProduct implements ScreenSystemOption {

    private final ProductService productService;

    public ScreenRecordProduct(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void running() throws RuntimeException {
        System.out.println("Por favor, digite o nome e a descrição do produto:");
        String name = IO.getScanner().nextLine();
        String description = IO.getScanner().nextLine();

        Product product = new Product(null, name, description);
        productService.recordNewProduct(product);
        System.out.println("Produto gravado com sucesso! \u2713");
    }
}
