package org.sulaiman;

import java.sql.Connection;
import io.github.cdimascio.dotenv.Dotenv;

public class DB {
    private Dotenv dotenv = Dotenv.load();
    private Connection connection;
    private String username,password, host, database;
    private int port;

    public DB(){
        this.username = dotenv.get("DB_USER");
        this.password = dotenv.get("DB_PASS");
        this.host = dotenv.get("DB_HOST");
        this.database = dotenv.get("DB_NAME");
        this.port = Integer.parseInt(dotenv.get("DB_PORT"));
    }

    public void connect(){
        System.out.println("Connecting to database...");
        System.out.println("Username: " + this.username);
        System.out.println("Password: " + this.password);
        System.out.println("Host: " + this.host);
        System.out.println("Database: " + this.database);
        System.out.println("Port: " + this.port);
    }
}
