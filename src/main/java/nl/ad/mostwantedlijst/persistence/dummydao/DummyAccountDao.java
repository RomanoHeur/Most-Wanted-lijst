package nl.ad.mostwantedlijst.persistence.dummydao;

import nl.ad.mostwantedlijst.model.security.Account;
import nl.ad.mostwantedlijst.persistence.interfaces.IAccountDao;

import java.util.*;

public class DummyAccountDao implements IAccountDao {

    private static final List<Account> ENTITIES = new ArrayList<>();

    @Override
    public Account save(Account account) {
        ENTITIES.add(account);
        return account;
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        return ENTITIES.stream().filter(entity -> entity.getUsername().equals(username)).findFirst();
    }

    public static void addEntities(Account... accounts) {
        Collections.addAll(ENTITIES, accounts);
    }

    public static void clear() {
        ENTITIES.clear();
    }

}
