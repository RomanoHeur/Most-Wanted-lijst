package nl.ad.mostwantedlijst;

import nl.ad.mostwantedlijst.controller.LoginController;
import nl.ad.mostwantedlijst.model.security.Account;
import nl.ad.mostwantedlijst.model.type.LoginResult;
import nl.ad.mostwantedlijst.persistence.dummydao.DummyAccountDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test voor de LoginController
 *
 * Deze test controleert de of de juiste waarde worden teruggegeven van de LoginController.
 */
public class TestLoginController {

    /**
     * Zorgt ervoor dat de dummydatabase leeg is.
     */
    @BeforeEach
     void setUp() {
        DummyAccountDao.clear();
    }

    /**
     * Wanneer beide velden leeg zijn dan moet die een EMPTY_FIELD terug geven.
     */
    @Test
    void Given_EmptyFields_When_Login_Then_ReturnEmptyFields() {
        LoginController loginController = new LoginController(new DummyAccountDao());

        LoginResult loginResult = loginController.login("", ""); // Voert methode uit.

        assertEquals(LoginResult.EMPTY_FIELD, loginResult);
    }

    /**
     * Wanneer een gebruiker niet bestaat dan moet die een USER_NOT_FOUND terug geven.
     */
    @Test
    void Given_UserDoesNotExist_When_Login_Then_ReturnsUserNotFound() {
        LoginController loginController = new LoginController(new DummyAccountDao());

        LoginResult loginResult = loginController.login("test", "wachtwoord"); // Voert methode uit.

        assertEquals(LoginResult.USER_NOT_FOUND, loginResult);
    }

    /**
     * Wanneer er verkeerde inloggegevens wordt meegegeven moet die een INVALID_CREDENTIALS terug geven.
     */
    @Test
    void Given_InvalidPassword_When_Login_Then_ReturnsInvalidCredentials() {
        DummyAccountDao.addEntities(new Account("admin", "admin123")); // Voegt testaccount toe.

        LoginController loginController = new LoginController(new DummyAccountDao()); // Voert methode uit.

        LoginResult loginResult = loginController.login("admin", "test123"); // probeert in te loggen met een verkeerd wachtwoord.

        assertEquals(LoginResult.INVALID_CREDENTIALS, loginResult);
    }

    /**
     * Wanneer de inloggevens juist zijn dan moet die een SUCCESS terug geven.
     */
    @Test
    void Given_ValidCredentials_When_Login_Then_ReturnsSuccess() {
        DummyAccountDao.addEntities(new Account("admin", "admin123")); // Voegt een testaccount toe.

        LoginController loginController = new LoginController(new DummyAccountDao()); // Voert methode uit.

        LoginResult loginResult = loginController.login("admin", "admin123"); // Logt in met juiste gegevens.

        assertEquals(LoginResult.SUCCESS, loginResult);
    }


}
