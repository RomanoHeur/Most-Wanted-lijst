package nl.ad.mostwantedlijst.controller.criminal;

import nl.ad.mostwantedlijst.exception.ValidateException;
import nl.ad.mostwantedlijst.model.management.Criminal;
import nl.ad.mostwantedlijst.persistence.dao.CriminalDao;
import nl.ad.mostwantedlijst.persistence.interfaces.ICriminalDao;

/**
 * Controller voor het bewerken van een crimineel.
 */
public class EditCriminalController {

    private final ICriminalDao criminalDao;

    public EditCriminalController() {
        this.criminalDao = new CriminalDao();
    }

    public void updateCriminal(Criminal criminal) {

        // Haalt de bestaande crimineel op via de Dao.
        Criminal existingCriminal = criminalDao.findById(criminal.getId());

        // Checkt of de bestaande crimineel bestaat.
        if (existingCriminal == null) {
            throw new ValidateException("Crimineel niet gevonden in de database.");
        }

        // Crimineel wordt geupdate via de Dao.
        criminalDao.update(criminal);
    }

}
