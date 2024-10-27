package org.sulaiman;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private Connection connection;
    private final String username;
    private final String password;
    private final String host;
    private final String database;
    private final int port;

    public DB() {
        Dotenv dotenv = Dotenv.load();
        this.username = dotenv.get("DB_USER");
        this.password = dotenv.get("DB_PASS");
        this.host = dotenv.get("DB_HOST");
        this.database = dotenv.get("DB_NAME");
        this.port = Integer.parseInt(dotenv.get("DB_PORT"));
    }

    private Connection getConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database;
            this.connection = DriverManager.getConnection(url, this.username, this.password);
        }
        return this.connection;
    }

    public void connect() {
        try {
            this.connection = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
