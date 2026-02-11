module nl.ad.mostwantedlijst {
    requires javafx.controls;
    requires javafx.fxml;


    opens nl.ad.mostwantedlijst to javafx.fxml;
    exports nl.ad.mostwantedlijst;
}