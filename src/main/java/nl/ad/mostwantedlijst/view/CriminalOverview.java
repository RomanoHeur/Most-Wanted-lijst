package nl.ad.mostwantedlijst.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import nl.ad.mostwantedlijst.Application;
import nl.ad.mostwantedlijst.controller.criminal.CriminalController;
import nl.ad.mostwantedlijst.model.management.Criminal;
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

        // Scrollpane maken om over de pagina te kunnen scrollen.
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.getStyleClass().add("scrollpane");

        // Alle onderdelen toevoegen aan de node.
        VBox root = new VBox(
                createHeader(),
                createSubHeader(),
                createHeroSection(),
                createCriminalTitleSection(),
                createCriminalContainer()
        );

        // Root toevoegen aan de scrollpane.
        scrollPane.setContent(root);

        this.setCenter(scrollPane);
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

        // Actie op de Login button om naar de login pagina te gaan.
        loginButton.setOnMouseClicked(_ -> {
            LoginView loginView = new LoginView();
            Application.setStage(loginView);
        });

        // Afstand tussen button en titelbox.
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Alles toevoegen aan de header.
        header.getChildren().addAll(logo, titleBox, spacer, loginButton);

        return header;
    }

    private HBox createSubHeader() {
        HBox subHeader = new HBox(20);
        subHeader.setAlignment(Pos.TOP_CENTER);
        subHeader.setPadding(new Insets(10, 10, 10, 10));
        subHeader.getStyleClass().add("subheader");

        // Waarschuwings icoontje toevoegen.
        ImageView warningIcon = new ImageView(
          Application.class.getResource("images/white-warning.png").toString()
        );
        warningIcon.setPreserveRatio(true);
        warningIcon.setFitHeight(30);

        // Titel maken voor de subheader.
        Label title = new Label("Waarschuwing: Benader deze personen niet zelf. Bel direct 112 bij waarneming.");
        title.getStyleClass().add("subheader-title");

        // Titel en icon toevoegen aan box.
        HBox titleBox = new HBox(warningIcon, title);
        titleBox.setSpacing(10);

        // Alle onderdelen toevoegen aan subheader.
        subHeader.getChildren().addAll(titleBox);

        return subHeader;
    }

    private VBox createHeroSection() {
        VBox infoBox = new VBox(10);
        infoBox.setAlignment(Pos.TOP_CENTER);
        infoBox.setPadding(new Insets(50, 0, 70, 0));
        infoBox.getStyleClass().add("infobox");

        // Titel maken voor de hero.
        Label title = new Label("Help ons deze personen op te sporen");
        title.getStyleClass().add("infobox-title");

        // Beschrijving toevoegen voor de hero.
        Label description = new Label("Hieronder vindt u een overzicht van gezochte personen in Nederland.");
        description.getStyleClass().add("infobox-description");
        description.setWrapText(true);

        // Nog een kleine beschrijving maken.
        Label subDescription = new Label("Heeft u informatie? Meld het direct.");
        subDescription.getStyleClass().add("infobox-description");

        // Beschrijvingen toevoegen aan box
        VBox descriptionBox = new VBox(description, subDescription);
        descriptionBox.setSpacing(4);
        descriptionBox.setAlignment(Pos.CENTER);

        // Icon maken voor de button.
        ImageView reportIcon = new ImageView(
                Application.class.getResource("images/white-warning.png").toString()
        );
        reportIcon.setPreserveRatio(true);
        reportIcon.setFitHeight(15);

        // Melding knop maken en hierbij de icon aan toevoegen.
        Button reportButton = new Button("Melding maken", reportIcon);
        reportButton.getStyleClass().add("infobox-report-button");
        reportButton.setPadding(new Insets(6, 18, 6, 18));

        // Beschrijvingen toevoegen aan container.
        VBox textContainer = new VBox(descriptionBox, reportButton);
        textContainer.setSpacing(30);
        textContainer.setAlignment(Pos.CENTER);
        textContainer.setMaxWidth(700);

        // Alle onderdelen toevoegen aan de info box.
        infoBox.getChildren().addAll(title, textContainer);

        return infoBox;
    }

    private HBox createCriminalTitleSection() {
        HBox titleBox = new HBox(8);
        titleBox.setAlignment(Pos.CENTER_LEFT);
        titleBox.setPadding(new Insets(20, 0, 20, 75));
        titleBox.getStyleClass().add("title-box");
        titleBox.setBorder(new Border(
                new BorderStroke(
                        Color.rgb(161, 161, 161),
                        BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY,
                        new BorderWidths(1, 0, 1, 0)
                )
        ));

        // Titel maken voor de section.
        Label title = new Label("Gezochte Personen");
        title.getStyleClass().add("criminal-section-title");

        // Titel toevoegen aan de root.
        titleBox.getChildren().add(title);

        return titleBox;
    }

    private VBox createCriminalContainer() {
        VBox container = new VBox(35);
        container.setPadding(new Insets(40, 70, 50, 70));

        //Todo: Nog dynamisch maken.
        Label criminalResult = new Label("4 Resultaten gevonden");
        criminalResult.getStyleClass().add("criminal-result");

        // Tilepane maken voor alle criminal cards.
        TilePane tilePane = new TilePane();
        tilePane.setHgap(60);
        tilePane.setVgap(45);
        tilePane.setPrefColumns(4); // Max 4 per rij.
        tilePane.getStyleClass().add("criminal-section");

        CriminalController criminalController = new CriminalController();

        // Om alle criminelen op te halen en ze opnieuw weer te geven in de criminal card.
        for (Criminal criminal : criminalController.getAllCriminals()) {
            tilePane.getChildren().add(createCriminalCard(criminal));
        }

        // Alles toevoegen aan de container node.
        container.getChildren().addAll(criminalResult, tilePane);

        return container;
    }

    private VBox createCriminalCard(Criminal criminal) {
        VBox card = new VBox(10);
        card.setPrefWidth(250);
        card.getStyleClass().add("criminal-card");
        card.setAlignment(Pos.CENTER);

        ImageView criminalProfile = new ImageView();

        // Als er een afbeelding is gevonden, wordt die opgehaald.
        if (criminal.getImageLink() != null) {
            criminalProfile.setImage(new Image("file:" + criminal.getImageLink()));
        }
        criminalProfile.setFitWidth(250);
        criminalProfile.setFitHeight(220);
        criminalProfile.setPreserveRatio(false);

        // Crimineel profiel toevoegen aan container.
        StackPane imageContainer = new StackPane(criminalProfile);
        imageContainer.setPrefHeight(220);

        // Info box maken.
        VBox infoBox = new VBox(8);
        infoBox.setPadding(new Insets(15));

        // Naam label maken voor de crimineel card.
        Label nameLabel = new Label(criminal.getFirstname() + " " + criminal.getSurname() + " " + criminal.getLastname());
        nameLabel.getStyleClass().add("criminal-name");

        // Status van de crimineel maken.
        Label statusLabel = new Label(criminal.getCriminalStatus().toString());
        statusLabel.getStyleClass().add("criminal-status");

        // Nationaliteit label maken.
        Label nationalityLabel = new Label(criminal.getNationality());
        nationalityLabel.getStyleClass().add("criminal-nationality");

        // Bekijk knop
        Button detailsButton = new Button("Bekijk details");
        detailsButton.setPadding(new Insets(6 , 25, 6, 25));
        detailsButton.setAlignment(Pos.CENTER);
        detailsButton.getStyleClass().add("criminal-button");

        // Actie toevoegen aan de knop om naar de criminal details pagina te gaan.
        detailsButton.setOnAction(_ -> {
            CriminalDetailsOverview criminalDetailsOverview = new CriminalDetailsOverview(criminal.getId());
            Application.setStage(criminalDetailsOverview);
        });

        // Alle info onderdelen toevoegen aan de info box.
        infoBox.getChildren().addAll(nameLabel, statusLabel, nationalityLabel, detailsButton);

        // Alle onderdelen toevoegen aan de card node.
        card.getChildren().addAll(imageContainer, infoBox);

        return card;
    }

}
