# Most-Wanted-lijst - Opsporingssysteem
Dit is  een JavaFX dekstopapplicatie. De applicatie is een soort Nederlandse "Most Wanted" lijst waarin criminelen worden geregistreerd en beheerd. Bezoekers kunnen daarnaast meldingen doen wanneer zij informatie hebben over een gezochte crimineel.

---

## 🚀 Aan de slag
Deze instructies helpen je om de applicatie lokaal te kunnen installeren en ook uit te voeren.

---

## 📋 Vereisten
Zorg dat je de volgende software hebt geïnstalleerd.

- Java Development Kit (JDK 23)
- Intellij IDEA of een andere IDE
- MySQL Server (Voor de database)
- Maven (Zodat het project gebouwd kan worden)
- XAMPP of een andere lokale database omgeving.

---

## ⚙️ Installatie
Clone de Repository:

```
git clone https://github.com/[jouw-gebruikersnaam]/most-wanted-lijst.git

Of download het project als ZIP-bestand.
```

## 2. Database opzetten
1. Start MySQL
2. Import het meegeleverde SQL-bestandje in MySQL Workbench.
3. Zorg dat de database de juiste naam heeft (bijvoorbeeld most_wanted_lijst).

## 3. Database Configuratie aanpassen
Open:
```
DatabaseProviderService.java
```
Pas indien nodig de volgende gegevens aan:
```
private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/most_wanted_lijst";
private static final String DATABASE_USERNAME = "root";
private static final String DATABASE_PASSWORD = "jouw_wachtwoord";
```

## 4. Applicatie bouwen
Via Maven:
```
mvn clean package
```
Dit genereert een .jar bestand in de target mapje.

## 5. Applicatie starten.
Via terminal:
```
java -jar target\Most-Wanted-Lijst-1.0-jar-with-dependencies.jar
```
Of via Maven:
```
mvn javafx:run
```

---

## 🌐 Productieomgeving & Uitrol
Productieomgeving
De applicatie is ontwikkeld als JavaFX dekstopapplicatie en kan worden uitgerold binnen een beveiligde omgeving, zoals een politie of gemeentelijke netwerk.

De productieomgeving bestaat uit:
- Een centrale MySQL database server.
- JDBC database verbindingen.

# Uitrolstrategie
De implementatie van het systeem verloopt in meerdere stappen:
1. Installeren van MySQL Server
2. Importeren van het SQL setup script.
3. Het configureren van de database gegevens.
4. Bouwen van het JAR-bestand
5. Testen van de databaseconnectie

---

## 🎯 Functionaliteiten
👥 Publieke overzicht
* Overzicht van alle gezochte personen
* Kaarten weergave met foto, naam, status en nationaliteit
* Detailpagina per persoon
* Dynamische weergave van resultaten

---

## 🔐 Admin Panel
Toegang via login met gebruikersnaam en wachtwoord:
```
Gebruikersnaam: admin
Wachtwoord: admin123
```

### Crimineel Beheer:
* Nieuw crimineel toevoegen
* Bestaande crimineel bewerken
* Crimineel Verwijderen
* Foto uploaden
* Status aanpassen
* Notities beheren

### Melding Beheer
* Overzicht van alle meldingen

---

## ⌨️ Shortcut Functionaliteit
De login knop kan worden getoond/verbergen met:
```
CTRL + SHIFT + P
```

Dit is geïmplementeerd met een `KeyCodeCombination` in JavaFX.

---

## 🛠 Technologieën
* Java 23
* JavaFX
* CSS
* MySQL
* JDBC
* Maven
* JUnit 5

--- 

## 🧪 Testing
De controllers zijn getest met:
* Dummy DAO implementaties
* Unit tests met JUnit 5
* Tests voor:
   * LoginController
   * DeleteCriminalController

--- 

## 📌 Acceptatiecriteria
Het systeem wordt succesvol beschouwd wanneer:
* Alle criminelen CRUD-functionaliteiten werkt
* Meldingen correct gekoppeld worden
* Login Systeem correct functioneert
* Shortcut werkt
* Database verbinding stabiel is

---

## 📅 Implementatieplanning
Week 1-3: Database ontwerp maken, dus de SQL script, connectie en al regelen. Ontwerp en ideeën opdoen hoe de applicatie er ongeveer uit moet komen te zien.
Week 4-6: Beginnen aan de applicatie, dus denk hierbij aan de functionaliteitein maken. CRUD functionaileiten maken, de UI implementeren.
week 6-8: Hierbij dan ook testen maken en uitvoeren, bugs oplossen als die er waren en de documentatie afronden.

---

## Evaluatie

Na implementatie wordt het systeem geëvalueerd op:

- Stabiliteit van de applicatie
- Gebruiksvriendelijkheid
- Databaseprestaties
- Testresultaten
- Eventuele bugs en verbeterpunten

Verbeterpunten worden meegenomen in toekomstige versies van het systeem.

---

## 🛣 Mogelijke Uitbreidingen
* Zoekfuncties
* Filter op status
* Statistieken dashboard
* PDF/Excel export
* Rolgebaseerde Toegang
* Meer mogelijke functies voor het beheren van meldingen

--- 

## 👨‍💻 Gebruik
1. Start de applicatie
2. Bekijke het publieke overzicht
3. Log in via de login knop met shortcut
4. Gebruik het admin panel voor beheer

--- 

## 📞 Contact
Naam: Romano Heur
Eigen Mail: romanoheur1@gmail.com
School Mail: ra.heur@ad-academie.nl
Schoolproject - AD Academie

# 📄 Licentie
Dit project is ontwikkeld als onderdeel van een schoolopdracht voor de AD Academie zie `License`.
