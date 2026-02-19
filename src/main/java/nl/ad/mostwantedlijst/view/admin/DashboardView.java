package nl.ad.mostwantedlijst.view.admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import nl.ad.mostwantedlijst.Application;
import nl.ad.mostwantedlijst.controller.criminal.CreateCriminalController;
import nl.ad.mostwantedlijst.controller.criminal.CriminalController;
import nl.ad.mostwantedlijst.controller.criminal.DeleteCriminalController;
import nl.ad.mostwantedlijst.model.management.Criminal;
import nl.ad.mostwantedlijst.model.management.CriminalStatus;
import nl.ad.mostwantedlijst.service.FileChooserService;
import nl.ad.mostwantedlijst.view.CriminalOverview;

import java.io.File;

public class DashboardView extends BorderPane {

    private VBox criminalTableContainer;

    public DashboardView() {
        initialize();

        this.getStylesheets().add(Application.class.getResource("css/admin/dashboard.css").toString());
    }

    private void initialize() {

        // Scrollpane maken om over de pagina te kunnen scrollen.
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Center container voor het overzicht box van criminelen.
        VBox centerContainer = new VBox();
        centerContainer.setPadding(new Insets(30, 100, 30, 100));
        centerContainer.setAlignment(Pos.TOP_CENTER);
        centerContainer.getChildren().add(createOverviewBox());

        // Alle onderdelen toevoegen aan de root.
        VBox root = new VBox(20);
        root.getChildren().addAll(
                createHeader(),
                createStatusBar(),
                centerContainer
        );

        // Root toevoegen aan de scrollpane.
        scrollPane.setContent(root);

        this.setCenter(scrollPane);
    }

    private HBox createHeader() {
        HBox header = new HBox();
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
        Label title = new Label("Admin Panel");
        title.getStyleClass().add("title");

        // Subtitel toevoegen voor de header.
        Label subTitle = new Label("Beheer gezochte personen en meldingen");
        subTitle.getStyleClass().add("subtitle");

        // Titels toevoegen aan een box.
        VBox titleBox = new VBox(title, subTitle);
        titleBox.setSpacing(4);

        // Terugknop en titleBox toevoegen aan andere node.
        VBox headerBox = new VBox(backButton, titleBox);
        headerBox.setSpacing(5);

        // Voeg alles toe aan de header.
        header.getChildren().addAll(headerBox);

        return header;
    }

    private HBox createStatusBar() {

        // Statistiekenbalk maken.
        HBox statsBar = new HBox(50);
        statsBar.setAlignment(Pos.CENTER);
        statsBar.setPadding(new Insets(25, 0, 0, 0));

        // Voegt meerdere statistieken eraan toe.
        //TODO: Moet nog dynamisch worden gemaakt.
        statsBar.getChildren().addAll(
                createCriminalStatusBox(
                        "Actief gezochte criminelen",
                        "5"
                ),
                createReportStatusBox(
                        "Meldingen",
                        "2"
                )
        );

        return statsBar;
    }

    private VBox createCriminalStatusBox(String criminalTitle, String criminalValue) {

        // Card aanmaken voor de statussen.
        VBox criminalCard = new VBox(20);
        criminalCard.setPadding(new Insets(15));
        criminalCard.setPrefSize(400, 115);
        criminalCard.getStyleClass().add("card");

        // Titel aanmaken.
        Label titleLabel = new Label(criminalTitle);
        titleLabel.getStyleClass().add("card-title");

        // Value aanmaken.
        Label valueLabel = new Label(criminalValue);
        valueLabel.getStyleClass().add("card-value");

        // Spacer maken
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Icon aanmaken.
        ImageView personIcon = new ImageView(
                Application.class.getResource("images/person-icon.png").toString()
        );
        personIcon.setPreserveRatio(true);
        personIcon.setFitHeight(50);

        // Value, spacer en icon toevoegen aan een box.
        HBox valueBox = new HBox(valueLabel, spacer, personIcon);

        // Alle onderdelen toevoegen aan de criminal card.
        criminalCard.getChildren().addAll(titleLabel , valueBox);

        return criminalCard;
    }

    private VBox createReportStatusBox(String reportTitle, String reportValue) {

        // Card aanmaken voor de statussen.
        VBox reportCard = new VBox(20);
        reportCard.setPadding(new Insets(15));
        reportCard.setPrefSize(400, 115);
        reportCard.getStyleClass().add("card");

        // Titel aanmaken.
        Label titleLabel = new Label(reportTitle);
        titleLabel.getStyleClass().add("card-title");

        // Value aanmaken.
        Label valueLabel = new Label(reportValue);
        valueLabel.getStyleClass().add("card-value");

        // Spacer maken
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Icon aanmaken.
        ImageView personIcon = new ImageView(
                Application.class.getResource("images/note-icon.png").toString()
        );
        personIcon.setPreserveRatio(true);
        personIcon.setFitHeight(50);

        // Value, spacer en icon toevoegen aan een box.
        HBox valueBox = new HBox(valueLabel, spacer, personIcon);

        // Alle onderdelen toevoegen aan de reportCard.
        reportCard.getChildren().addAll(titleLabel , valueBox);

        return reportCard;
    }

    private VBox createOverviewBox() {

        // Overview card maken.
        VBox overviewBox = new VBox();
        overviewBox.setAlignment(Pos.TOP_CENTER);
        overviewBox.setPrefSize(800, 500);
        overviewBox.setPadding(new Insets(20));
        overviewBox.getStyleClass().add("card");

        // Label maken.
        Label title = new Label("Gezochte personen beheren");
        title.getStyleClass().add("overview-title");

        // Spacer maken.
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        ImageView plusIcon = new ImageView(
                Application.class.getResource("images/plus-icon.png").toString()
        );
        plusIcon.setPreserveRatio(true);
        plusIcon.setFitHeight(20);

        // Toevoeg knop maken.
        Button criminalButton = new Button("Persoon Toevoegen", plusIcon);
        criminalButton.getStyleClass().add("add-button");

        // Actie toevoegen om de form te openen.
        criminalButton.setOnAction(_ -> {
            createCriminalForm();
        });

        // Alles toevoegen aan de headerBox.
        HBox headerBox = new HBox(title, spacer, criminalButton);

        // Container met de criminelen.
        criminalTableContainer = new VBox(10);
        criminalTableContainer.setPadding(new Insets(20, 0,0, 0));

        refreshCriminalList();

        // Alle onderdelen toevoegen aan de overviewBox.
        overviewBox.getChildren().addAll(headerBox, criminalTableContainer);

        return overviewBox;
    }

    private void createCriminalForm() {

        // Node aanmaken.
        VBox root = new  VBox(5);
        root.setPadding(new Insets(20));

        // Nieuwe stage aanmaken om een soort modal te krijgen.
        Stage stage = new Stage();
        stage.setResizable(false);
        Scene scene = new Scene(root, 800, 800);
        stage.setScene(scene);
        scene.getStylesheets().add(
                Application.class.getResource("css/admin/dashboard.css").toString()
        );

        // Form titel maken.
        Label createTitle = new Label("Persoon toevoegen");
        createTitle.getStyleClass().add("form-title");

        // Form subtitel maken.
        Label createSubtitle = new Label("Maak hier een crimineel aan om toe te voegen");
        createSubtitle.getStyleClass().add("form-subtitle");

        VBox titleBox = new VBox(createTitle, createSubtitle);
        titleBox.setSpacing(10);

        // Velden aanmaken.
        TextField firstnameField = new TextField();
        TextField surnameField = new TextField();
        TextField lastnameField = new TextField();
        DatePicker dateOfBirthPicker = new DatePicker();

        ComboBox<Object> genderBox = new ComboBox<>();
        genderBox.getItems().addAll("Man", "Vrouw", "Anders");

        TextField nationalityField = new TextField();
        TextArea noteArea = new TextArea();

        ComboBox<CriminalStatus> statusBox = new ComboBox<>();
        statusBox.getItems().addAll(CriminalStatus.values());
        statusBox.getSelectionModel().selectFirst();

        Button imageButton = new Button("Select Image");
        final String[] selectedImagePath = {null}; // Checkt welk bestand geselecteerd is.
        Label imagePathLabel = new Label("No image selected");

        // Actie toevoegen aan de image knop.
        imageButton.setOnAction(_ -> {
            FileChooserService fileChooserService = new FileChooserService();

            // Opent de filechooser en geef het pad van geselecteerd bestand terug.
            String path = fileChooserService.chooseImage(stage);

            // Als er een pad is gevonden slaat die het bestand op en wordt het bestandsnaam weergegeven in de label.
            if (path != null) {
                selectedImagePath[0] = path;
                imagePathLabel.setText(new File(path).getName());
            }
        });

        // Image knop en label toevoegen aan box.
        VBox imageBox = new VBox(imageButton, imagePathLabel);
        imageBox.setSpacing(5);
        imageBox.setPadding(new Insets(15, 0, 15, 0));

        // Knop voor het opslaan maken.
        Button saveButton = new Button("Opslaan");
        saveButton.getStyleClass().add("form-button");

        // Actie toevoegen aan de opslaan knop.
        saveButton.setOnAction(_ -> {
            CreateCriminalController createCriminalController = new CreateCriminalController();

            createCriminalController.createCriminal(
                    firstnameField.getText(),
                    surnameField.getText(),
                    lastnameField.getText(),
                    dateOfBirthPicker.getValue(),
                    (String) genderBox.getValue(),
                    nationalityField.getText(),
                    noteArea.getText(),
                    statusBox.getValue(),
                    selectedImagePath[0]
            );

            refreshCriminalList(); // Opnieuw de lijst inladen.
            stage.close(); // Sluit de form
        });

        // Alle onderdelen toevoegen aan de root.
        root.getChildren().addAll(
                titleBox,
                new Label("Voornaam: *"), firstnameField,
                new Label("Tussenvoegsel:"), surnameField,
                new Label("Achternaam: *"), lastnameField,
                new Label("Geboortedatum: *"), dateOfBirthPicker,
                new Label("Geslacht: *"), genderBox,
                new Label("Nationaliteit: *"), nationalityField,
                new Label("Notities: *"), noteArea,
                new Label("Status: *"), statusBox,
                imageBox,
                saveButton
        );

        stage.show();
    }

    private void refreshCriminalList() {
        criminalTableContainer.getChildren().clear(); // Alles leeg maken.

        CriminalController criminalController = new CriminalController();

        // Haalt alle criminelen op via de controller.
        for (Criminal criminal : criminalController.getAllCriminals()) {
            HBox criminalBox = new HBox(20);
            criminalBox.setPadding(new Insets(10));
            criminalBox.getStyleClass().add("criminal-card");

            // Profiel foto van de crimineel wordt opgehaald als die bestaat.
            ImageView criminalProfile = new ImageView();
            if (criminal.getImageLink() != null) {
                criminalProfile.setImage(new Image("file:" + criminal.getImageLink()));
                criminalProfile.setFitHeight(50);
                criminalProfile.setFitWidth(50);
                criminalProfile.setPreserveRatio(true);
            }

            // Info over de crimineel
            VBox infoBox = new VBox(5);
            Label nameLabel = new Label(criminal.getFirstname() + " " + criminal.getSurname() + " " + criminal.getLastname());
            Label statusLabel = new Label("Status: " + criminal.getCriminalStatus());
            Label notesLabel = new Label("Notes: " + criminal.getNotes());

            // Voeg alle labels toe aan de infoBox.
            infoBox.getChildren().addAll(nameLabel, statusLabel, notesLabel);

            // Spacer maken zodat knoppen aan de rechterkant komen.
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            // Edit icon maken
            ImageView editIcon = new ImageView(
                    Application.class.getResource("images/edit-icon.png").toString()
            );
            editIcon.setPreserveRatio(true);
            editIcon.setFitHeight(15);

            Button editButton = new Button("", editIcon); // Alleen het icoontje tonen.
            editButton.setMaxWidth(35);

            // Delete icon maken.
            ImageView deleteIcon = new ImageView(
                    Application.class.getResource("images/delete-icon.png").toString()
            );
            deleteIcon.setPreserveRatio(true);
            deleteIcon.setFitHeight(15);

            Button deleteButton = new Button("", deleteIcon); // Alleen het icoontje tonen.
            deleteButton.setMaxWidth(35);

            // Actie toevoegen aan de verwijder knop.
            deleteButton.setOnAction(_ -> {
                DeleteCriminalController deleteCriminalController = new DeleteCriminalController();

                // Crimineel wordt verwijderd via de controller
                deleteCriminalController.deleteCriminal(criminal.getId());

                // Opnieuw lijst met criminelen laden.
                refreshCriminalList();
            });

            // Button toevoegen aan box.
            HBox iconBox = new HBox(editButton, deleteButton);
            iconBox.setSpacing(5);
            iconBox.setAlignment(Pos.CENTER);

            // Foto met info toevoegen aan criminalBox
            criminalBox.getChildren().addAll(criminalProfile, infoBox, spacer, iconBox);

            // Alles aan de tablecontainer toevoegen.
            criminalTableContainer.getChildren().add(criminalBox);
        }
    }

}
