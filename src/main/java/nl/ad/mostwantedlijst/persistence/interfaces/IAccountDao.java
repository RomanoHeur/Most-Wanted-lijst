package nl.ad.mostwantedlijst.persistence.interfaces;

import nl.ad.mostwantedlijst.model.security.Account;

import java.util.Optional;

public interface IAccountDao {

    Account save(Account account);

    Optional<Account> findByUsername(String username);

}
