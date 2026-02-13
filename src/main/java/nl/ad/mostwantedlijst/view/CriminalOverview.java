package nl.ad.mostwantedlijst.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import nl.ad.mostwantedlijst.Application;
import nl.ad.mostwantedlijst.view.admin.LoginView;

/**
 * View voor het overzicht van alle criminelen.
 */
public class CriminalOverview extends BorderPane {

    public CriminalOverview() {
        initialize();

        this.getStylesheets().add(Application.class.getResource("css/criminal-overview.css").toString());
    }

    private void initialize() {
        this.setTop(createHeader());

    }

    private HBox createHeader() {
        HBox header = new HBox();
        header.setSpacing(20);
        header.setAlignment(Pos.TOP_LEFT);
        header.setPadding(new Insets(10, 10, 10, 40));
        header.getStyleClass().add("header");

        // Logo icoontje
        ImageView logo = new ImageView(
          Application.class.getResource("images/logo-icon.png").toString()
        );
        logo.setFitHeight(40);
        logo.setPreserveRatio(true);
        HBox.setMargin(logo, new Insets(10, 0, 0, 0)); // Afstand van de header vandaan.

        // Titel naam van applicatie.
        Label title = new Label("Most-Wanted Lijst");
        title.getStyleClass().add("application-title");

        // Subtitel van de applicatie.
        Label subTitle = new Label("Opsporing Nederland");
        subTitle.getStyleClass().add("application-subTitle");

        // Titels toevoegen aan box.
        VBox titleBox = new VBox(title, subTitle);
        titleBox.setSpacing(3);
        titleBox.setPadding(new Insets(0, 0, 0, 5));

        // Login button maken.
        Button loginButton = new Button("Login");
        loginButton.getStyleClass().add("login-button");
        loginButton.setPadding(new Insets(6, 20, 6, 20));
        HBox.setMargin(loginButton, new Insets(10, 40, 0, 0)); // Zorgt ervoor dat er afstand zit tussen de header.

        loginButton.setOnMouseClicked(_ -> {
            LoginView loginView = new LoginView();
            Application.setStage(loginView);
        });


        // Afstand tussen button en titelbox.
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        header.getChildren().addAll(logo, titleBox, spacer, loginButton);

        return header;
    }

}
