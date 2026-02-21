DROP DATABASE IF EXISTS most_wanted_lijst;
CREATE DATABASE most_wanted_lijst;
USE most_wanted_lijst;

-- TABLES
CREATE TABLE account (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE person (
    id INT PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(255) NOT NULL,
    surname VARCHAR(100),
    lastname VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender VARCHAR(20),
    nationality VARCHAR(100),
    description TEXT
);

CREATE TABLE criminal (
    id INT PRIMARY KEY AUTO_INCREMENT,
    person_id INT NOT NULL,
    criminal_status ENUM('WANTED', 'CAPTURED', 'MISSING') NOT NULL,
    notes TEXT,
    crimes VARCHAR(255),
    image_link VARCHAR(255),
    FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE
);

CREATE TABLE report (
    id INT PRIMARY KEY AUTO_INCREMENT,
    criminal_id INT NOT NULL,
    description TEXT NOT NULL ,
    location VARCHAR(255),
    date DATE,
    report_name VARCHAR(100),
    FOREIGN KEY (criminal_id) REFERENCES criminal(id) ON DELETE CASCADE
);

-- INDEXES
CREATE INDEX idx_criminal_crimes
ON criminal (crimes(255));

CREATE INDEX idx_report_date
ON report (date);

INSERT INTO account (username, password)
VALUES ('admin', 'admin123');