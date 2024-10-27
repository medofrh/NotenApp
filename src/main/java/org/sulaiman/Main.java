package org.sulaiman;

public class Main {
    public static void main(String[] args) {
        DB database = new DB();

        database.connect();
        database.close();
    }
}