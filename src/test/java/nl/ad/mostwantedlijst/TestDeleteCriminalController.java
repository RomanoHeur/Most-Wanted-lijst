package nl.ad.mostwantedlijst;

import nl.ad.mostwantedlijst.controller.criminal.DeleteCriminalController;
import nl.ad.mostwantedlijst.exception.ValidateException;
import nl.ad.mostwantedlijst.model.management.Criminal;
import nl.ad.mostwantedlijst.persistence.dao.CriminalDao;
import nl.ad.mostwantedlijst.persistence.dummydao.DummyCriminalDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestDeleteCriminalController {

    @BeforeEach
    public void setUp() {
        DummyCriminalDao.clear();
    }


    /**
     * Wanneer er geen crimineel is gevonden geeft die een exception.
     */
    @Test
    void Given_CriminalDoesNotExist_When_Delete_Then_ThrowException() {
        DeleteCriminalController deleteCriminalController = new DeleteCriminalController(new DummyCriminalDao());

        assertThrows(ValidateException.class, () -> deleteCriminalController.deleteCriminal(1));
    }

    /**
     * Wanneer er een crimineel is gevonden wordt die verwijderd.
     */
    @Test
    void Given_CriminalExists_When_Delete_Then_RemoveFromList() {
        Criminal criminal = new Criminal();
        criminal.setId(1);

        DummyCriminalDao.addEntities(criminal);

        DeleteCriminalController deleteCriminalController = new DeleteCriminalController(new DummyCriminalDao());
        deleteCriminalController.deleteCriminal(1);

    }

}
