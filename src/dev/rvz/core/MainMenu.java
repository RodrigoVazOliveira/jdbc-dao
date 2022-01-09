package dev.rvz.core;

import dev.rvz.core.proxys.IO;
import dev.rvz.core.strategy.OptionsStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainMenu {
    private final OptionsStrategy optionsStrategy;
    private Boolean runningSystem = true;
    private Integer option;
    private final List<Integer> OPTIONS_AVAILABLE = new ArrayList<>(Arrays.asList(1,2,3,4, 5, 6, 7, 8));

    public MainMenu(OptionsStrategy optionsStrategy) {
        this.optionsStrategy = optionsStrategy;
    }

    public void printMenu() {
        System.out.printf("\u1F609 SEJA BEM VINDO -- \n" +
                "1. Gravar novo produto.\n" +
                "2. Listar todos os produtos.\n" +
                "3. Alterar um produto.\n" +
                "4. Remover um produto.\n" +
                "5. Gravar novo Categoria.\n" +
                "6. Listar todos os categorias.\n" +
                "7. Alterar um categoria.\n" +
                "8. Remover um categoria.\n" +
                "0. Sair do sistema.\n" +
                "Selecione uma das opções: ");
    }

    public void runningSystemMainMenu() {
        while(runningSystem) {
            printMenu();
            String word = IO.getScanner().nextLine();
            option = Integer.parseInt(word);

            if (OPTIONS_AVAILABLE.contains(option)) {
                try {
                    optionsStrategy.selectScreen(option);
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (option == 0) {
                runningSystem = false;
            } else {
                System.out.println("Opção não existe no sistema. Favor selecinar uma das opçoes disponiveis");
            }
        }
    }
}
