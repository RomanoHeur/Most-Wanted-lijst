package nl.ad.mostwantedlijst.controller.report;

import nl.ad.mostwantedlijst.model.report.Report;
import nl.ad.mostwantedlijst.persistence.dao.ReportDao;
import nl.ad.mostwantedlijst.persistence.interfaces.IReportDao;

import java.time.LocalDate;

/**
 * Controller voor het aanmaken van een melding.
 */
public class CreateReportController {

    private final IReportDao reportDao;

    public CreateReportController() {
        this.reportDao = new ReportDao();
    }

    public void createReport(String description, String location, LocalDate date, String reportName, int criminalId) {

        // Maakt melding object aan.
        Report report = new Report(description, location, date, reportName);

        // Melding wordt opgeslagen via de Dao.
        reportDao.save(report, criminalId);
    }

}
