package org.sulaiman;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private Connection connection;
    private int userId;
    private String vorname;
    private String nachname;
    private String username;
    private String password;
    private String email;
    private String geburtsdatum;
    private String telefonnummer;
    private String strasse;
    private String hausnummer;
    private String plz;
    private String ort;
    private boolean role = false;
    private DB db = new DB();

    /* public User(int userId, String vorname, String nachname, String username, String password, String email, String geburtsdatum, String telefonnummer, String strasse, String hausnummer, String plz, String ort, String role) {
        this.userId = userId;
        this.vorname = vorname;
        this.nachname = nachname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.geburtsdatum = geburtsdatum;
        this.telefonnummer = telefonnummer;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ort = ort;
        this.role = role;

        this.connection = connection;
        DB db = new DB();
    } */
    public User(String username, String password, boolean role) {
        this.username = username;
        this.password = password;
        this.role = role;

        try{
            this.connection = db.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean login () {
        String sql;
        if(this.role){
            sql = "SELECT COUNT(*) FROM lehrer WHERE username = ? AND password = ?";
        }else{
            sql = "SELECT COUNT(*) FROM schueler WHERE username = ? AND password = ?";
        }

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, this.username);
            pstmt.setString(2, this.password); // Passwords should be hashed;

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // If count > 0, credentials are correct
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // close connection
        db.close();
        return false; // Credentials are incorrect
    };

    public void logout (){

    };

    public void changePassword (String newPassword){

    };
}

