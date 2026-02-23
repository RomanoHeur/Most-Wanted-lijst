package nl.ad.mostwantedlijst;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import nl.ad.mostwantedlijst.view.CriminalOverview;
import nl.ad.mostwantedlijst.view.admin.LoginView;

public class Application extends javafx.application.Application {

    private static final int[] APPLICATION_SIZE = { 1440, 820 };
    private static Stage stage;

    @Override
    public void start(Stage stage) {
        Application.stage = stage;

        setStage(new CriminalOverview());

        stage.setTitle("Most-Wanted");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(Application.class, args);
    }

    public static void setStage(Parent parent) {

        Scene scene = new Scene(
                parent,
                APPLICATION_SIZE[0],
                APPLICATION_SIZE[1]
        );

        addShortcuts(scene); // Shortcut wordt opnieuw toegevoegd na elke nieuwe scene.

        stage.setScene(scene);
    }

    private static void addShortcuts(Scene scene) {

        // Voegt een key combinatie toe (CTRL + SHIFT + P).
        KeyCodeCombination secretLoginShortcut = new KeyCodeCombination(
                KeyCode.P,
                KeyCombination.CONTROL_DOWN,
                KeyCombination.SHIFT_DOWN
        );

        // Registreert de shortcut in de scene.
        scene.getAccelerators().put(secretLoginShortcut, () ->  {

            // Checkt of de huidige view een CriminalOverview is.
            if (scene.getRoot() instanceof CriminalOverview criminalOverview) {
                criminalOverview.toggleLoginButton(); // Toggle van de login knop.
            }
        });
    }

}
