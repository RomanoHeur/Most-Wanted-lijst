package nl.ad.mostwantedlijst.model;

import java.time.LocalDate;

public class Crime {

    private int id;
    private String crimeType;
    private String description;
    private LocalDate date;

    public Crime() { }

    public Crime(int id, String crimeType, String description, LocalDate date) {
        this.id = id;
        this.crimeType = crimeType;
        this.description = description;
        this.date = date;
    }

    public Crime(String crimeType, String description, LocalDate date) {
        this.crimeType = crimeType;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCrimeType() {
        return crimeType;
    }

    public void setCrimeType(String crimeType) {
        this.crimeType = crimeType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Crime{" +
                "id=" + id +
                ", crimeType='" + crimeType + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }

}
