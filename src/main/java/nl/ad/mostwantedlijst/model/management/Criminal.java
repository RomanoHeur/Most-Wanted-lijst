package nl.ad.mostwantedlijst.model.management;

import nl.ad.mostwantedlijst.model.report.Report;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Criminal extends Person {

    private CriminalStatus criminalStatus;
    private String notes;
    private String imageLink;
    private List<Crime> crimes = new ArrayList<>();
    private List<Report> reports = new ArrayList<>();

    public Criminal() { }

    public Criminal(String firstname, String surname, String lastname, LocalDate dateOfBirth, String gender, String nationality, CriminalStatus criminalStatus, String notes, String imageLink) {
        super(firstname, surname, lastname, dateOfBirth, gender, nationality);
        this.criminalStatus = criminalStatus;
        this.notes = notes;
        this.imageLink = imageLink;
    }

    public Criminal(int id, String firstname, String surname, String lastname, LocalDate dateOfBirth, String gender, String nationality, CriminalStatus criminalStatus, String notes, String imageLink) {
        super(id, firstname, surname, lastname, dateOfBirth, gender, nationality);
        this.criminalStatus = criminalStatus;
        this.notes = notes;
        this.imageLink = imageLink;
    }

    public CriminalStatus getCriminalStatus() {
        return criminalStatus;
    }

    public void setCriminalStatus(CriminalStatus criminalStatus) {
        this.criminalStatus = criminalStatus;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public List<Crime> getCrimes() {
        return crimes;
    }

    public void setCrimes(List<Crime> crimes) {
        this.crimes = crimes;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public void addCrime(Crime crime) {
        if (crimes == null) {
            crimes = new ArrayList<>();
        }

        crimes.add(crime);
    }

    public void addReport(Report report) {
        if (reports == null) {
            reports = new ArrayList<>();
        }

        reports.add(report);
    }

    @Override
    public String toString() {
        return "Criminal{" +
                "criminalStatus=" + criminalStatus +
                ", notes='" + notes + '\'' +
                ", crimes=" + crimes +
                ", reports=" + reports +
                '}';
    }

}