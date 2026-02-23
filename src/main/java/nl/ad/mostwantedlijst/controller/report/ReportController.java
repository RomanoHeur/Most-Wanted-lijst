package nl.ad.mostwantedlijst.controller.report;

import nl.ad.mostwantedlijst.model.report.Report;
import nl.ad.mostwantedlijst.persistence.dao.ReportDao;
import nl.ad.mostwantedlijst.persistence.interfaces.IReportDao;

import java.util.List;

/**
 * Controller voor het ophalen van alle Meldingen.
 */
public class ReportController {

    private IReportDao reportDao;

    public ReportController() {
        this.reportDao = new ReportDao();
    }

    public List<Report> getAllReports() {
        return reportDao.findAll(); // Haalt alle meldingen op via de Dao.
    }

}