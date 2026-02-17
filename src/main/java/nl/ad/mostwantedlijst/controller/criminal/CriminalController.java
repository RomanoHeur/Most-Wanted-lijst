package nl.ad.mostwantedlijst.controller.criminal;

import nl.ad.mostwantedlijst.model.management.Criminal;
import nl.ad.mostwantedlijst.persistence.dao.CriminalDao;
import nl.ad.mostwantedlijst.persistence.interfaces.ICriminalDao;

import java.util.List;

/**
 * Controller voor het ophalen van criminelen.
 */
public class CriminalController {

    private ICriminalDao criminalDao;

    public CriminalController() {
        this.criminalDao = new CriminalDao();
    }

    public List<Criminal> getAllCriminals() {

        // Haalt alle criminelen op via de Dao.
        return criminalDao.findAll();
    }

}
