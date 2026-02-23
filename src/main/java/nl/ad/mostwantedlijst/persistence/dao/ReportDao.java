package nl.ad.mostwantedlijst.persistence.dao;

import nl.ad.mostwantedlijst.model.report.Report;
import nl.ad.mostwantedlijst.persistence.interfaces.IReportDao;
import nl.ad.mostwantedlijst.service.DatabaseProviderService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDao implements IReportDao {

    private final Connection connection;

    public ReportDao() {
        this.connection = DatabaseProviderService.getConnection();
    }

    /**
     * Slaat een melding op in de database.
     * @param report Het object dat opgeslagen moet worden.
     * @param criminalId Het ID van de crimineel waaraan de report wordt gekoppeld.
     */
    @Override
    public void save(Report report, int criminalId) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO report (criminal_id, description, location, date, report_name) VALUES (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            // Stelt alle waarden op.
            statement.setInt(1, criminalId);
            statement.setString(2, report.getDescription());
            statement.setString(3, report.getLocation());
            statement.setDate(4, Date.valueOf(report.getDate()));
            statement.setString(5, report.getReportName());

            // Voert statement uit.
            statement.executeUpdate();

            // Ophalen van een gegenereerde id.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                report.setId(resultSet.getInt(1));
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Kon geen verbinding maken met de database.", exception);
        }
    }


    /**
     * Haalt alle meldingen op uit de database.
     * @return Een lijst met alle Report objecten.
     */
    @Override
    public List<Report> findAll() {
        List<Report> reports = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT r.id, r.description, r.location, r.date, r.report_name, c.id AS criminal_id, p.firstname, p.lastname FROM report r JOIN criminal c ON r.criminal_id = c.id JOIN person p ON c.person_id = p.id");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                // Samenstellen van de naam.
                String fullName = resultSet.getString("firstname") + " " + resultSet.getString("lastname");

                // Maakt nieuw Report object aan.
                Report report = new Report(
                        resultSet.getInt("id"),
                        resultSet.getString("description"),
                        resultSet.getString("location"),
                        resultSet.getDate("date").toLocalDate(),
                        resultSet.getString("report_name"),
                        resultSet.getInt("criminal_id"),
                        fullName
                );

                // Voegt de report toe aan de lijst.
                reports.add(report);
            }

            return reports;

        } catch (SQLException exception) {
            throw new IllegalStateException("Kon geen verbinding maken met de database", exception);
        }
    }

}
