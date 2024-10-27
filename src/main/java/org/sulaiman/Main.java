package org.sulaiman;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String username;
        String password;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wilkommen zur NotenApp!");
        System.out.println("Bitte wählen Sie eine Option:");
        System.out.println("1. Anmelden");
        System.out.println("2. Registrieren");
        System.out.println("3. Beenden");

        int option = scanner.nextInt();
        scanner.nextLine();

        if (option == 1) {
            System.out.println("Ist es Lehrer oder Schüler konto?");
            System.out.println("1. Lehrer");
            System.out.println("2. Schüler");
            int role = scanner.nextInt();
            if (role == 1) {
                scanner.nextLine();
                clearScreen();
                System.out.println("Anmelden");
                System.out.print("Benutzername: ");
                username = scanner.nextLine();
                System.out.print("Passwort: ");
                password = scanner.nextLine();

                User lehrer = new User(username,password,true);
                if(lehrer.login()){
                    System.out.println("Wilkommen Herr "+lehrer.getNachname());
                }else{
                    System.out.println("Falsche Anmeldedaten, bitte versuchen Sie es erneut.");
                }
            } else {
                scanner.nextLine();
                clearScreen();
                System.out.println("Anmelden");
                System.out.print("Benutzername: ");
                username = scanner.nextLine();
                System.out.print("Passwort: ");
                password = scanner.nextLine();

                User schueler = new User(username,password,false);
                System.out.println(schueler.login());
            }

        } else if (option == 2) {
            System.out.println("Registrieren");
        } else {
            System.exit(0);
        }
    }

    public static void clearScreen() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
}