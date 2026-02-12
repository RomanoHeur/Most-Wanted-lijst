package nl.ad.mostwantedlijst.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Verantwoordelijk voor het maken van een database connectie.
 */
public class DatabaseProviderService {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/most_wanted_lijst";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "Admin";

    // Private constructor zodat er geen instanties buiten deze klasse aangemaakt kan worden.
    private DatabaseProviderService() { }

    private static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    DATABASE_URL,
                    DATABASE_USERNAME,
                    DATABASE_PASSWORD
            );
        } catch (SQLException exception) {
            throw new RuntimeException("Kon geen verbinding maken met de database. ", exception);
        }
    }

}
