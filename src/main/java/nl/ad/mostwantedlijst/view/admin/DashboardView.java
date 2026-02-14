package nl.ad.mostwantedlijst.view.admin;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class DashboardView extends StackPane {

    public DashboardView() {
        initialize();
    }

    private void initialize() {
        this.createTestMessage();
    }

    private void createTestMessage() {
        Label label = new Label("Welkom");
        this.getChildren().add(label);
    }

}
