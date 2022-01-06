package dev.rvz;

import dev.rvz.configurations.ScreenConfig;
import dev.rvz.connections.CreateConnectionFactory;
import dev.rvz.core.MainMenu;

import java.sql.SQLException;

public class StoreVirtualApplication {
    public static void main(String[] args) throws SQLException {
        ScreenConfig screenConfig = new ScreenConfig(new CreateConnectionFactory().getConnection());
        MainMenu mainMenu = new MainMenu(screenConfig.build());
        mainMenu.runningSystemMainMenu();
        screenConfig.getConnection().close();
    }
}
