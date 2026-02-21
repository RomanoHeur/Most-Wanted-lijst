package nl.ad.mostwantedlijst.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import nl.ad.mostwantedlijst.Application;
import nl.ad.mostwantedlijst.controller.criminal.CriminalController;
import nl.ad.mostwantedlijst.model.management.Criminal;

/**
 * Overzichts pagina voor een specifieke crimineel.
 */
public class CriminalDetailsOverview extends BorderPane {

    private final int criminalId;

    public CriminalDetailsOverview(int criminalId) {
        this.criminalId = criminalId;

        initialize();

        this.getStylesheets().add(Application.class.getResource("css/criminal-details.css").toString());
    }

    private void initialize() {

        this.setTop(createHeader());
        this.setCenter(createDetailsContainer());

    }

    private VBox createHeader() {
        VBox header = new VBox();
        header.setAlignment(Pos.TOP_LEFT);
        header.setPadding(new Insets(20, 20, 20, 40));
        header.getStyleClass().add("header");

        // Arrow naar links aanmaken.
        ImageView leftArrow = new ImageView(
                Application.class.getResource("images/arrow-left.png").toString()
        );
        leftArrow.setPreserveRatio(true);
        leftArrow.setFitWidth(22);

        // Button aanmaken en hierbij ook de left arrow icon eraan toevoegen.
        Button backButton = new Button("Terug naar publieke overzicht", leftArrow);
        backButton.getStyleClass().add("back-button");

        // Actie toevoegen aan de knop.
        backButton.setOnAction(_ -> {
            CriminalOverview criminalOverview = new CriminalOverview();
            Application.setStage(criminalOverview);
        });

        // Titel maken voor de header.
        Label title = new Label("Gezochte persoon - Details");
        title.getStyleClass().add("title");

        // Terugknop en titleBox toevoegen aan andere node.
        VBox headerBox = new VBox(backButton, title);
        headerBox.setSpacing(5);

        // Voeg alles toe aan de header.
        header.getChildren().addAll(headerBox);

        return header;
    }

    private HBox createDetailsContainer() {
        CriminalController criminalController = new CriminalController();
        Criminal criminal = criminalController.getCriminalById(criminalId);

        // Crimineel Details container maken.
        HBox detailsContainer = new HBox();
        detailsContainer.setAlignment(Pos.TOP_LEFT);
        detailsContainer.setPadding(new Insets(75, 0, 0, 100));
        detailsContainer.getStyleClass().add("details-container");

        // Side bar maken.
        VBox sideBar = new VBox(20);
        sideBar.setAlignment(Pos.TOP_CENTER);

        ImageView criminalProfile = new ImageView();

        // Als er een image gevonden is laadt die hem in.
        if (criminal.getImageLink() != null) {
            criminalProfile.setImage(new Image("file:" + criminal.getImageLink()));
        }
        criminalProfile.setPreserveRatio(false);
        criminalProfile.setFitHeight(300);
        criminalProfile.setFitWidth(250);

        // Crimineel profiel in een image container zetten.
        StackPane imageContainer = new StackPane(criminalProfile);
        imageContainer.getStyleClass().add("image-container");

        // Melding button maken.
        Button reportButton = new Button("Melding maken");
        reportButton.setPrefWidth(criminalProfile.getFitWidth()); // Zodat het dezelfde lengte heeft als het plaatje.
        reportButton.setPrefHeight(10);
        reportButton.setAlignment(Pos.CENTER);
        reportButton.getStyleClass().add("report-button");

        reportButton.setOnAction(_ -> {
            Application.setStage(new ReportView(criminalId));
        });

        // Voeg alles toe aan de sidebar.
        sideBar.getChildren().addAll(imageContainer, reportButton);

        // Box maken voor alle info.
        VBox infoBox = new VBox(10);
        infoBox.setAlignment(Pos.TOP_LEFT);
        infoBox.setPadding(new Insets(15,30, 15, 0));

        // Alle detail labels maken van de crimineel.
        Label nameLabel = new Label(criminal.getFirstname() + " " + criminal.getSurname() + " " + criminal.getLastname());
        nameLabel.getStyleClass().add("criminal-name");

        Label crimeLabel = new Label("Misdaden: " + criminal.getCrimes());
        crimeLabel.getStyleClass().add("criminal-details");

        Label genderLabel = new Label("Geslacht: " + criminal.getGender());
        genderLabel.getStyleClass().add("criminal-details");

        Label dateOfBirthLabel = new Label("Geboortedatum: " + criminal.getDateOfBirth());
        dateOfBirthLabel.getStyleClass().add("criminal-details");

        Label nationalityLabel = new Label("Nationaliteit: " + criminal.getNationality());
        nationalityLabel.getStyleClass().add("criminal-details");

        Label statusLabel = new Label("Status: " + criminal.getCriminalStatus());
        statusLabel.getStyleClass().add("criminal-details");

        Label notesLabel = new Label("Notities: " + criminal.getNotes());
        notesLabel.setWrapText(true);
        notesLabel.setMaxWidth(800);
        notesLabel.getStyleClass().add("criminal-details");

        // Alle onderdelen toevoegen aan de infobox.
        infoBox.getChildren().addAll(
                nameLabel,
                crimeLabel,
                genderLabel,
                dateOfBirthLabel,
                nationalityLabel,
                statusLabel,
                notesLabel
        );

        // Card maken en hier de sidebar en infobox aan toevoegen.
        HBox card = new HBox(sideBar, infoBox);
        card.setSpacing(100);
        card.setAlignment(Pos.TOP_LEFT);
        card.getStyleClass().add("criminal-card");
        card.setMaxHeight(400);

        // Card toevoegen aan de details container.
        detailsContainer.getChildren().addAll(card);

        return detailsContainer;
    }

}
