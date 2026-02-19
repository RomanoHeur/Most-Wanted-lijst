package nl.ad.mostwantedlijst.controller.criminal;

import nl.ad.mostwantedlijst.exception.ValidateException;
import nl.ad.mostwantedlijst.model.management.Criminal;
import nl.ad.mostwantedlijst.persistence.dao.CriminalDao;
import nl.ad.mostwantedlijst.persistence.interfaces.ICriminalDao;

/**
 * Controller voor het verwijderen van een crimineel.
 */
public class DeleteCriminalController {

    private ICriminalDao criminalDao;

    public DeleteCriminalController() {
        this.criminalDao = new CriminalDao();
    }

    public void deleteCriminal(int criminalId) {

        // Zoekt de crimineel op basis van de crimineel id
        Criminal criminal = criminalDao.findById(criminalId);

        // Checkt of de crimineel bestaat, anders geeft die een error.
        if (criminal == null) {
            throw new ValidateException("Crimineel niet gevonden in de database.");
        }

        // Verwijderd crimineel vanuit de dao klasse.
        criminalDao.delete(criminalId);
    }

}
