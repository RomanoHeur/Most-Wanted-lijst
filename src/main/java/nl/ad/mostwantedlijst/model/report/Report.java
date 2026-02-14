package nl.ad.mostwantedlijst.model.report;

import nl.ad.mostwantedlijst.model.management.Criminal;

import java.time.LocalDate;

public class Report {

    private int id;
    private String description;
    private String location;
    private LocalDate date;
    private String reportName;
    private Criminal criminal;

    public Report() { }

    public Report(int id, String description, String location, LocalDate date, String reportName, Criminal criminal) {
        this.id = id;
        this.description = description;
        this.location = location;
        this.date = date;
        this.reportName = reportName;
        this.criminal = criminal;
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

    public Criminal getCriminal() {
        return criminal;
    }

    public void setCriminal(Criminal criminal) {
        this.criminal = criminal;
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
