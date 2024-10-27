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
            // Load MySQL JDBC driver explicitly if necessary
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("MySQL JDBC Driver not found");
                e.printStackTrace();
            }

            // Enhanced URL with additional properties
            String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database +
                    "?useSSL=false&serverTimezone=UTC";
            this.connection = DriverManager.getConnection(url, this.username, this.password);
            System.out.println("Connected to the database");
        }
        return this.connection;
    }

    public void connect() {
        try {
            this.connection = getConnection();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database");
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error closing the database connection");
            e.printStackTrace();
        }
    }
}
