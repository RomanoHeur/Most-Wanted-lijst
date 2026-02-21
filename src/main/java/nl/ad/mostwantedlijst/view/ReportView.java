package nl.ad.mostwantedlijst.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nl.ad.mostwantedlijst.Application;
import nl.ad.mostwantedlijst.controller.report.CreateReportController;

import java.time.LocalDate;

public class ReportView extends BorderPane {

    private final int criminalId;

    public ReportView(int criminalId) {
        this.criminalId = criminalId;

        initialize();

        this.getStylesheets().add(Application.class.getResource("css/report.css").toString());
    }

    private void initialize() {

        this.setTop(createHeader());
        this.setCenter(createReportContainer(criminalId));

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
        Button backButton = new Button("Terug naar crimineel detail overzicht", leftArrow);
        backButton.getStyleClass().add("back-button");

        // Actie toevoegen aan de knop.
        backButton.setOnAction(_ -> {
            CriminalDetailsOverview criminalDetailsOverview = new CriminalDetailsOverview(criminalId);
            Application.setStage(criminalDetailsOverview);
        });

        // Titel maken voor de header.
        Label title = new Label("Melding maken");
        title.getStyleClass().add("title");

        // Terugknop en titleBox toevoegen aan andere node.
        VBox headerBox = new VBox(backButton, title);
        headerBox.setSpacing(5);

        // Voeg alles toe aan de header.
        header.getChildren().addAll(headerBox);

        return header;
    }

    private HBox createReportContainer(int criminalId) {
        HBox reportContainer = new HBox(100);
        reportContainer.setAlignment(Pos.TOP_LEFT);
        reportContainer.setPadding(new Insets(75, 0, 0, 100));
        reportContainer.getStyleClass().add("report-container");

        // Privacy box maken
        VBox privacyBox = new VBox(10);
        privacyBox.setAlignment(Pos.TOP_CENTER);
        privacyBox.setPrefSize(350,550);
        privacyBox.setMaxHeight(550);
        privacyBox.getStyleClass().add("privacy-box");
        privacyBox.setPadding(new Insets(15, 10, 15, 10));

        // Titel maken.
        Label privacyTitle = new Label("Uw Privacy");
        privacyTitle.getStyleClass().add("privacy-title");

        // Regels maken voor in de privacy card.
        Label firstRule = new Label("1. Uw melding wordt vertrouwelijk behandeld");
        firstRule.getStyleClass().add("privacy-rules");
        firstRule.setWrapText(true);
        firstRule.setMaxWidth(Double.MAX_VALUE);

        Label secondRule = new Label("2. U kunt volledig anoniem blijven");
        secondRule.getStyleClass().add("privacy-rules");
        secondRule.setWrapText(true);
        secondRule.setMaxWidth(Double.MAX_VALUE);

        Label thirdRule = new Label("3. Uw gegevens worden nooit gedeeld");
        thirdRule.getStyleClass().add("privacy-rules");
        thirdRule.setWrapText(true);
        thirdRule.setMaxWidth(Double.MAX_VALUE);

        // Regels toevoegen aan box.
        VBox ruleBox = new VBox(firstRule, secondRule, thirdRule);
        ruleBox.setSpacing(8);

        // Alle onderdelen voor de privacy box toevoegen.
        privacyBox.getChildren().addAll(privacyTitle, ruleBox);

        // Report card maken.
        VBox reportBox = new VBox(10);
        reportBox.setAlignment(Pos.TOP_LEFT);
        reportBox.setPrefSize(800,550);
        reportBox.setMaxHeight(550);
        reportBox.getStyleClass().add("report-box");
        reportBox.setPadding(new Insets(25, 25, 25, 25));

        // Titel maken voor de report box.
        Label reportTitle = new Label("Kan je ons helpen?");
        reportTitle.getStyleClass().add("report-title");

        // Subtitel maken voor de report box.
        Label reportSubTitle = new Label("Contactgegevens");
        reportSubTitle.getStyleClass().add("report-subTitle");

        // Titels toevoegen aan een eigen box.
        VBox titleBox = new VBox(reportTitle, reportSubTitle);
        titleBox.setSpacing(10);

        // Label maken voor de naam.
        Label nameTitle = new Label("Naam");
        nameTitle.getStyleClass().add("form-labels");

        // Field maken voor de naam.
        TextField nameField = new TextField();
        nameField.setPromptText("Uw naam (Kan ook anoniem invullen)");

        // Naam onderdelen toevoegen aan box.
        VBox nameBox = new VBox(nameTitle, nameField);
        nameBox.setSpacing(6);

        // Label maken voor de locatie.
        Label locationTitle = new Label("Locatie");
        locationTitle.getStyleClass().add("form-labels");

        // Textfield maken voor de locatie
        TextField locationField = new TextField();
        locationField.setPromptText("Laatste locatie gezien");

        // Locatie onderdelen toevoegen aan box.
        VBox locationBox = new VBox(locationTitle, locationField);
        locationBox.setSpacing(6);

        // Label maken voor de datum.
        Label dateLabel = new Label("Datum");
        dateLabel.getStyleClass().add("form-labels");

        // Datepicker maken met de datum van vandaag.
        DatePicker datePicker = new DatePicker(LocalDate.now());

        // Datum onderdelen toevoegen aan box.
        VBox dateBox = new VBox(dateLabel, datePicker);
        dateBox.setSpacing(6);

        // Label maken voor een beschrijving.
        Label descriptionLabel = new Label("Beschrijving");
        descriptionLabel.getStyleClass().add("form-labels");

        // Texarea maken voor de beschrijving.
        TextArea descriptionField = new TextArea();
        descriptionField.setWrapText(true);
        descriptionField.setPromptText("Beschrijving van de melding");

        // Beschrijving onderdelen toevoegen aan box.
        VBox descriptionBox = new VBox(descriptionLabel, descriptionField);
        descriptionBox.setSpacing(6);

        Button reportButton = new Button("Melding Versturen");
        reportButton.getStyleClass().add("report-button");
        reportButton.setPadding(new Insets(6, 25, 6, 25));

        // Actie toevoegen aan de report button.
        reportButton.setOnAction(_ -> {

            CreateReportController createReportController = new CreateReportController();

            // Melding aanmaken via de controller.
            createReportController.createReport(
                    descriptionField.getText(),
                    locationField.getText(),
                    datePicker.getValue(),
                    nameField.getText(),
                    criminalId
            );

            // Na het aanmaken wordt je naar de crimineel detail pagina verstuurd.
            Application.setStage(new CriminalDetailsOverview(criminalId));
        });

        // Alle onderdelen toevoegen aan de report box.
        reportBox.getChildren().addAll(titleBox, nameBox, locationBox, dateBox, descriptionBox, reportButton);

        // Alle onderdelen toevoegen aan de report container.
        reportContainer.getChildren().addAll(privacyBox, reportBox);

        return reportContainer;
    }

}
