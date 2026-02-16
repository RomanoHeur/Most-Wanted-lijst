package nl.ad.mostwantedlijst.controller;

import nl.ad.mostwantedlijst.model.security.Account;
import nl.ad.mostwantedlijst.model.type.LoginResult;
import nl.ad.mostwantedlijst.persistence.dao.AccountDao;
import nl.ad.mostwantedlijst.persistence.dummydao.DummyAccountDao;
import nl.ad.mostwantedlijst.persistence.interfaces.IAccountDao;

import java.util.Optional;

/**
 * Controller voor het inloggen van een account.
 */
public class LoginController {

    private final IAccountDao accountDao;

    // Hier wordt de DAO geinitialiseerd.
    public LoginController(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public LoginResult login(String username, String password) {

        // Checkt of er lege velden zijn.
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return LoginResult.EMPTY_FIELD;
        }

        // Haalt de username van de database.
        Optional<Account> accountOptional = accountDao.findByUsername(username);

        // Checkt of de gebruiker gevonden is.
        if (accountOptional.isEmpty()) {
            return LoginResult.USER_NOT_FOUND;
        }

        Account account = accountOptional.get();

        // Checkt of het wachtwoord klopt.
        if (!account.getPassword().equals(password)) {
            return LoginResult.INVALID_CREDENTIALS;
        }

        return LoginResult.SUCCESS;
    }

}
