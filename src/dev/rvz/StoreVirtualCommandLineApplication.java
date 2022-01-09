package dev.rvz;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import dev.rvz.configurations.ScreenConfig;
import dev.rvz.connections.CreateConnectionFactory;
import dev.rvz.core.MainMenu;

import java.sql.SQLException;

public class StoreVirtualCommandLineApplication {

    public static void main(String[] args) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mariadb://localhost:3306/loja_virtual");
        comboPooledDataSource.setUser("loja_virtual");
        comboPooledDataSource.setPassword("loja_virtual");
        comboPooledDataSource.setMaxPoolSize(15);

        CreateConnectionFactory connectionFactory = new CreateConnectionFactory(comboPooledDataSource);
        ScreenConfig screenConfig = new ScreenConfig(connectionFactory.getConnection());
        MainMenu mainMenu = new MainMenu(screenConfig.build());
        mainMenu.runningSystemMainMenu();
        screenConfig.getConnection().close();
    }
}