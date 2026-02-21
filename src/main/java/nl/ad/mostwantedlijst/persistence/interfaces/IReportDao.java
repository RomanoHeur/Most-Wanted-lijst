package nl.ad.mostwantedlijst.persistence.interfaces;

import nl.ad.mostwantedlijst.model.report.Report;


public interface IReportDao {

    void save(Report report, int criminalId);

}
