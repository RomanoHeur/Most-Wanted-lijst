package nl.ad.mostwantedlijst;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.ad.mostwantedlijst.view.CriminalOverview;

public class Application extends javafx.application.Application {

    private static final int[] APPLICATION_SIZE = { 1440, 820 };
    private static Stage stage;

    @Override
    public void start(Stage stage) {
        Application.stage = stage;

        Scene scene = new Scene(
                new CriminalOverview(),
                APPLICATION_SIZE[0],
                APPLICATION_SIZE[1]
        );

        stage.setTitle("Most-Wanted");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(Application.class, args);
    }

    public static void setStage(Parent parent) {
        stage.setScene(new Scene(
                parent,
                Application.APPLICATION_SIZE[0],
                Application.APPLICATION_SIZE[1]
        ));
    }
}
