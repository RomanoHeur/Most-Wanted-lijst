module nl.ad.mostwantedlijst {
    requires javafx.controls;
    requires java.sql;


    opens nl.ad.mostwantedlijst to javafx.fxml;
    exports nl.ad.mostwantedlijst;
}