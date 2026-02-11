package nl.ad.mostwantedlijst;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) {
        Label label = new Label("Testing");
        Scene scene = new Scene(label, 400, 400);

        stage.setTitle("Test");
        stage.setScene(scene);
        stage.show();
    }

}
