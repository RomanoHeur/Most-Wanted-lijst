package nl.ad.mostwantedlijst.model.report;

import nl.ad.mostwantedlijst.model.management.Criminal;

import java.time.LocalDate;

public class Report {

    private int id;
    private String description;
    private String location;
    private LocalDate date;
    private String reportName;
    private int criminalId;
    private String criminalName;

    public Report() { }

    public Report(int id, String description, String location, LocalDate date, String reportName, int criminalId, String criminalName) {
        this.id = id;
        this.description = description;
        this.location = location;
        this.date = date;
        this.reportName = reportName;
        this.criminalId = criminalId;
        this.criminalName = criminalName;
    }

    public Report(String description, String location, LocalDate date, String reportName) {
        this.description = description;
        this.location = location;
        this.date = date;
        this.reportName = reportName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public int getCriminalId() {
        return criminalId;
    }

    public void setCriminalId(int criminalId) {
        this.criminalId = criminalId;
    }

    public String getCriminalName() {
        return criminalName;
    }

    public void setCriminalName(String criminalName) {
        this.criminalName = criminalName;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", reportName='" + reportName + '\'' +
                '}';
    }

}
