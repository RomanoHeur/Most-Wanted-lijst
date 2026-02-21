package nl.ad.mostwantedlijst.persistence.dao;

import nl.ad.mostwantedlijst.model.report.Report;
import nl.ad.mostwantedlijst.persistence.interfaces.IReportDao;
import nl.ad.mostwantedlijst.service.DatabaseProviderService;

import java.sql.*;

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

}
