create database if not exists db_lib;

use db_lib;

create table schueler (
    schueler_id INT PRIMARY KEY AUTO_INCREMENT,
    vorname VARCHAR(100) NOT NULL,
    nachname VARCHAR(100) NOT NULL,
    geburtsdatum DATE NOT NULL,
    eintrittsdatum DATE,
    austrittsdatum DATE,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefonnummer int(11) NOT NULL,
    anschrift VARCHAR(255) NOT NULL,
    stadt VARCHAR(100) NOT NULL,
    plz VARCHAR(7) NOT NULL,
    status ENUM('aktiv', 'inaktiv') NOT NULL DEFAULT 'aktiv'
);

create table lehrer (
    lehrer_id INT PRIMARY KEY AUTO_INCREMENT,
    vorname VARCHAR(100) NOT NULL,
    nachname VARCHAR(100) NOT NULL,
    geburtsdatum DATE NOT NULL,
    fach VARCHAR(100),
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefonnummer int(11) NOT NULL,
    anschrift VARCHAR(255) NOT NULL,
    stadt VARCHAR(100) NOT NULL,
    plz VARCHAR(7) NOT NULL,
    status ENUM('aktiv', 'inaktiv') NOT NULL DEFAULT 'aktiv'
);

create table notenskala (
    notenskala_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    min_wert DECIMAL(5, 2) NOT NULL,
    max_wert DECIMAL(5, 2) NOT NULL,
    notentyp ENUM('Punkte', 'Prozent', 'Dezimal', 'Wortnote') NOT NULL
);

create table notenart (
    notenart_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

create table bildungsgang (
    bildungsgang_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    notenskala_id INT,
    FOREIGN KEY (notenskala_id) REFERENCES notenskala(notenskala_id)
);

create table fach (
    fach_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    bildungsgang_id INT,
    FOREIGN KEY (bildungsgang_id) REFERENCES bildungsgang(bildungsgang_id)
);

create table klasse (
    klasse_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    schuljahr VARCHAR(9) NOT NULL,
    bildungsgang_id INT,
    lehrer_id INT,
    FOREIGN KEY (bildungsgang_id) REFERENCES bildungsgang(bildungsgang_id),
    FOREIGN KEY (lehrer_id) REFERENCES lehrer(lehrer_id)
);

create table noten (
    noten_id INT PRIMARY KEY AUTO_INCREMENT,
    schueler_id INT,
    fach_id INT,
    klassen_id INT,
    notenart_id INT,
    wert DECIMAL(5, 2) NOT NULL,
    datum DATE NOT NULL,
    bildungsgang_id INT,
    halbjahr ENUM('1', '2') NOT NULL,
    lehrer_id INT,
    FOREIGN KEY (schueler_id) REFERENCES schueler(schueler_id),
    FOREIGN KEY (fach_id) REFERENCES fach(fach_id),
    FOREIGN KEY (klassen_id) REFERENCES klasse(klasse_id),
    FOREIGN KEY (notenart_id) REFERENCES notenart(notenart_id),
    FOREIGN KEY (bildungsgang_id) REFERENCES bildungsgang(bildungsgang_id),
    FOREIGN KEY (lehrer_id) REFERENCES lehrer(lehrer_id)
);

create table Zeugnisnote (
    zeugnisnote_id INT PRIMARY KEY AUTO_INCREMENT,
    schueler_id INT,
    fach_id INT,
    klasse_id INT,
    wert DECIMAL(5, 2) NOT NULL,
    lehrer_id INT,
    datum DATE NOT NULL,
    FOREIGN KEY (schueler_id) REFERENCES schueler(schueler_id),
    FOREIGN KEY (fach_id) REFERENCES fach(fach_id),
    FOREIGN KEY (klasse_id) REFERENCES klasse(klasse_id),
    FOREIGN KEY (lehrer_id) REFERENCES lehrer(lehrer_id)
);