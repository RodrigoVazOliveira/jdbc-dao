package dev.rvz.core;

import dev.rvz.core.proxys.IO;
import dev.rvz.core.strategy.OptionsStrategy;

public class MainMenu {
    private final OptionsStrategy optionsStrategy;
    private Boolean runningSystem = true;
    private Integer option;

    public MainMenu(OptionsStrategy optionsStrategy) {
        this.optionsStrategy = optionsStrategy;
    }

    public void printMenu() {
        System.out.printf("\u1F609 SEJA BEM VINDO -- \n" +
                "1. Gravar novo produto.\n" +
                "2. Listar todos os produtos.\n" +
                "3. Alterar um produto.\n" +
                "4. Remover um produto.\n" +
                "5. Sair do sistema.\n" +
                "Selecione uma das opções: ");
    }

    public void runningSystemMainMenu() {
        while(runningSystem) {
            printMenu();
            String word = IO.getScanner().nextLine();
            option = Integer.parseInt(word);

            if (option != 5) {
                try {
                    optionsStrategy.selectScreen(option);
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                runningSystem = false;
            }
        }
    }
}
