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
    private String plz;
    private String ort;
    private String status;
    private boolean role = false;
    private DB db = new DB();
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

                this.updateUserData();
                return rs.getInt(1) > 0;
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

    private void updateUserData (){
        String sql;
        if(this.role){
            sql = "SELECT * FROM lehrer WHERE username = ? AND password = ?";
        }else{
            sql = "SELECT * FROM schueler WHERE username = ? AND password = ?";
        }
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, this.username);
            pstmt.setString(2, this.password); // Passwords should be hashed in a real application!

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.userId = rs.getInt("ID");
                this.vorname = rs.getString("vorname");
                this.nachname = rs.getString("nachname");
                this.email = rs.getString("email");
                this.geburtsdatum = rs.getString("geburtsdatum");
                this.telefonnummer = rs.getString("telefonnummer");
                this.strasse = rs.getString("anschrift");
                this.plz = rs.getString("plz");
                this.ort = rs.getString("stadt");
                this.status = rs.getString("status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getUserId() {
        return userId;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public String getStrasse() {
        return strasse;
    }

    public String getPlz() {
        return plz;
    }

    public String getOrt() {
        return ort;
    }

    public String getStatus() {
        return status;
    }

    public boolean getRole() {
        return role;
    }
}

