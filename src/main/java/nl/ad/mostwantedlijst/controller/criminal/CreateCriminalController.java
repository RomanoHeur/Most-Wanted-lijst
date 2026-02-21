package nl.ad.mostwantedlijst.controller.criminal;

import nl.ad.mostwantedlijst.model.management.Criminal;
import nl.ad.mostwantedlijst.model.management.CriminalStatus;
import nl.ad.mostwantedlijst.persistence.dao.CriminalDao;
import nl.ad.mostwantedlijst.persistence.interfaces.ICriminalDao;

import java.time.LocalDate;

/**
 * Controller voor het aanmaken van een crimineel.
 */
public class CreateCriminalController {

    private final ICriminalDao criminalDao;

    public CreateCriminalController() {
        this.criminalDao = new CriminalDao();
    }

    public void createCriminal(String firstname, String surname, String lastname, LocalDate dateOfBirth, String gender, String nationality, String notes, CriminalStatus criminalStatus, String crimes, String imagePath) {

        // Maakt crimineel object aan.
        Criminal criminal = new Criminal(firstname, surname, lastname, dateOfBirth, gender, nationality, criminalStatus, notes, crimes, imagePath);

        // Slaat de crimineel op via de Dao.
        criminalDao.save(criminal);
    }

}