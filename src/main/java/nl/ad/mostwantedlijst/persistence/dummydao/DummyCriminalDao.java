package nl.ad.mostwantedlijst.persistence.dummydao;

import nl.ad.mostwantedlijst.model.management.Criminal;
import nl.ad.mostwantedlijst.persistence.interfaces.ICriminalDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DummyCriminalDao implements ICriminalDao {

    private static final List<Criminal> ENTITIES = new ArrayList<>();

    @Override
    public void save(Criminal criminal) {
        ENTITIES.add(criminal);
    }

    @Override
    public List<Criminal> findAll() {
        return new ArrayList<>(ENTITIES);
    }

    @Override
    public Criminal findById(int criminalId) {
        return ENTITIES.stream()
                .filter(criminal -> criminal.getId() == criminalId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Criminal newCriminal) {
        delete(newCriminal.getId());
        ENTITIES.add(newCriminal);
    }

    @Override
    public void delete(int criminalId) {
        ENTITIES.removeIf(criminal -> criminal.getId() == criminalId);
    }

    public static void addEntities(Criminal... criminals) {
        Collections.addAll(ENTITIES, criminals);
    }

    public static void clear() {
        ENTITIES.clear();
    }

}
