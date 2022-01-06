package dev.rvz.core;

import dev.rvz.core.proxys.IO;

public class MainMenu {

    private static Boolean RUNNING_SYSTEM = true;
    private Integer option;

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
        while(RUNNING_SYSTEM) {
            printMenu();
            String word = IO.getScanner().nextLine();
            option = Integer.parseInt(word);

        }
    }
}
