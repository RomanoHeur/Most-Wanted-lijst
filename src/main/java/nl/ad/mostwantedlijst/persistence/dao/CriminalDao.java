package nl.ad.mostwantedlijst.persistence.dao;

import javafx.scene.layout.Region;
import nl.ad.mostwantedlijst.model.management.Criminal;
import nl.ad.mostwantedlijst.model.management.CriminalStatus;
import nl.ad.mostwantedlijst.persistence.interfaces.ICriminalDao;
import nl.ad.mostwantedlijst.service.DatabaseProviderService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CriminalDao implements ICriminalDao {

    private final Connection connection;

    public CriminalDao() {
        this.connection = DatabaseProviderService.getConnection();
    }

    /**
     * Slaat een crimineel op in de database.
     * @param criminal Crimineel object dat opgeslagen moet worden.
     */
    @Override
    public void save(Criminal criminal) {
        try {
            // Eerst een persoon toevoegen.
            PreparedStatement personStatement = connection.prepareStatement("INSERT INTO person (firstname, surname, lastname, date_of_birth, gender, nationality, description) VALUES (?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            // Stelt alle waarden op voor de persoon.
            personStatement.setString(1, criminal.getFirstname());
            personStatement.setString(2, criminal.getSurname());
            personStatement.setString(3, criminal.getLastname());
            personStatement.setDate(4, Date.valueOf(criminal.getDateOfBirth()));
            personStatement.setString(5, criminal.getGender());
            personStatement.setString(6, criminal.getNationality());
            personStatement.setString(7, criminal.getNotes());

            // Voert methode uit.
            personStatement.executeUpdate();

            // Ophalen van een gegenereerde id.
            ResultSet resultSet = personStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int personId = resultSet.getInt(1);

                // Een crimineel toevoegen.
                PreparedStatement criminalStatement = connection.prepareStatement("INSERT INTO criminal (person_id, criminal_status, notes, image_link) VALUES (?, ?, ?, ?)");

                // Stelt alle waarden op voor de criminieel.
                criminalStatement.setInt(1, personId);
                criminalStatement.setString(2, criminal.getCriminalStatus().name());
                criminalStatement.setString(3, criminal.getNotes());
                criminalStatement.setString(4, criminal.getImageLink());

                // Voert de methode uit.
                criminalStatement.executeUpdate();
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Kon geen verbinding maken met de database", exception);
        }
    }

    /**
     * Haalt alle criminelen op uit de database.
     * @return een lijst met criminelen.
     */
    @Override
    public List<Criminal> findAll() {
        List<Criminal> criminals = new ArrayList<>();

        try {
            // Crimineel info joinen met die van de persoon info.
            PreparedStatement statement = connection.prepareStatement("SELECT p.firstname, p.surname, p.lastname, p.date_of_birth, p.gender, p.nationality, c.criminal_status, c.notes, c.image_link FROM criminal c JOIN person p ON c.person_id = p.id");

            // Voert methode uit.
            ResultSet resultSet = statement.executeQuery();

            // Maakt crimineel objecten aan.
            while (resultSet.next()) {
                Criminal criminal = new Criminal(
                        resultSet.getString("firstname"),
                        resultSet.getString("surname"),
                        resultSet.getString("lastname"),
                        resultSet.getDate("date_of_birth").toLocalDate(),
                        resultSet.getString("gender"),
                        resultSet.getString("nationality"),
                        CriminalStatus.valueOf(resultSet.getString("criminal_status")),
                        resultSet.getString("notes"),
                        resultSet.getString("image_link")
                );

                criminals.add(criminal);

            }

            return criminals;

        } catch (SQLException exception) {
            throw new IllegalStateException("Kon geen verbinding maken met de database", exception);
        }
    }

}