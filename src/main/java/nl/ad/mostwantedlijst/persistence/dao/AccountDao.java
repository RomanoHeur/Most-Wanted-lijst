package nl.ad.mostwantedlijst.persistence.dao;

import nl.ad.mostwantedlijst.model.Account;
import nl.ad.mostwantedlijst.persistence.interfaces.IAccountDao;
import nl.ad.mostwantedlijst.service.DatabaseProviderService;

import java.sql.*;
import java.util.Optional;

public class AccountDao implements IAccountDao {

    private final Connection connection;

    public AccountDao() {
        this.connection = DatabaseProviderService.getConnection();
    }

    /**
     * Slaat een account op in de database.
     * @param account Het object dat opgeslagen moet worden.
     * @return Het opgeslagen account.
     */
    @Override
    public Account save(Account account) {
        try {
            // Query aanmaken
            PreparedStatement statement = connection.prepareStatement("INSERT INTO account (username, password) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

            // Stelt alle waarden op.
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());

            // Voert de statement uit.
            statement.execute();

            // Haalt de id op.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                account.setId(resultSet.getInt(1));
            }

            return account;

        } catch (SQLException exception) {
            throw new IllegalStateException("Kan geen verbinding maken met de database.", exception);
        }
    }

    /**
     * Zoekt een account op basis van de username.
     * @param username De gebruikersnaam zoeken.
     * @return Optional<Account>, leeg als er geen account is gevonden.
     */
    @Override
    public Optional<Account> findByUsername(String username) {
        try {
            // Query aanmaken.
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM account WHERE username = ?");

            // Stelt alle waarden op.
            statement.setString(1, username);

            // Maakt een account object aan als er resultaat is.
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Account account = new Account(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );

                return Optional.of(account);
            }

            return Optional.empty();

        } catch (SQLException exception) {
            throw new IllegalStateException("Kon geen verbinding maken met de database", exception);
        }
    }

}
