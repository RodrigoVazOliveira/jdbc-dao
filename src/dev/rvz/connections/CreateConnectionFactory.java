package dev.rvz.connections;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class CreateConnectionFactory {

    private final ComboPooledDataSource comboPooledDataSource;
    private Connection connection;
    private DataSource dataSource;

    public CreateConnectionFactory(ComboPooledDataSource comboPooledDataSource) {
        this.comboPooledDataSource = comboPooledDataSource;
        this.dataSource = this.comboPooledDataSource;
    }

    public Connection getConnection() throws SQLException {
        this.connection = this.dataSource.getConnection();
        return connection;
    }
}