package nl.ad.mostwantedlijst.persistence.interfaces;

import nl.ad.mostwantedlijst.model.report.Report;

import java.util.List;


public interface IReportDao {

    void save(Report report, int criminalId);

    List<Report> findAll();

}
