package dev.rvz.connections;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class CreateConnectionFactory {

    private final DataSource dataSource;

    public CreateConnectionFactory(ComboPooledDataSource comboPooledDataSource) {
        this.dataSource = comboPooledDataSource;
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}