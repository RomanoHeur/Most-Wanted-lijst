package nl.ad.mostwantedlijst.persistence.interfaces;

import nl.ad.mostwantedlijst.model.management.Criminal;

import java.util.List;

public interface ICriminalDao {

    void save(Criminal criminal);

    List<Criminal> findAll();

    Criminal findById(int criminalId);

    void update(Criminal newCriminal);

    void delete(int criminalId);

}
