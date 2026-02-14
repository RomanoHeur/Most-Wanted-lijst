package nl.ad.mostwantedlijst.view.admin;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import nl.ad.mostwantedlijst.Application;
import nl.ad.mostwantedlijst.controller.LoginController;
import nl.ad.mostwantedlijst.model.type.LoginResult;

/**
 * View voor de inlog pagina voor een admin.
 */
public class LoginView extends StackPane {

    public LoginView() {
        initialize();

        this.getStylesheets().add(Application.class.getResource("css/admin/login.css").toString());
    }

    // Bouwt volledigeUI op en zet het in de stackpane.
    private void initialize() {
        BorderPane form = createLoginCard();
        this.getChildren().add(form);
    }

    private BorderPane createLoginCard() {
        // Maakt de card aan voor de form.
        BorderPane card = new BorderPane();
        card.setPrefSize(450, 450);
        card.setMaxSize(450, 450);
        card.setPadding(new Insets(10));
        card.getStyleClass().add("login-card");

        card.setTop(createCardHeader());
        card.setCenter(createFormFields());

        return card;
    }

    private HBox createCardHeader() {

        // Icon maken.
        ImageView icon = new ImageView(
                Application.class.getResource("images/logo-icon.png").toString()
        );
        icon.setPreserveRatio(true);
        icon.setFitWidth(30);

        // Achtergrond voor de icon.
        StackPane iconBox = new StackPane(icon);
        iconBox.setPrefSize(50, 50);
        iconBox.getStyleClass().add("icon-box");

        // Titel aanmaken
        Label title = new Label("Admin-Panel");
        title.setStyle("-fx-font-size: 20; -fx-font-weight: bold");

        // Subtitle aanmaken.
        Label subtitle = new Label("Most-Wanted Nederland - Beheer");
        subtitle.setStyle("-fx-font-size: 15;");

        // Voeg alle titles toe.
        VBox titleBox = new VBox(title, subtitle);
        titleBox.setSpacing(4);

        // De icon en titel toevoegen aan de header van de form.
        HBox headerBox = new HBox(iconBox, titleBox);
        headerBox.setSpacing(20);
        headerBox.setAlignment(Pos.TOP_LEFT);
        headerBox.setPadding(new Insets(30));

        return headerBox;
    }

    private VBox createFormFields() {

        // Root node aanmaken.
        VBox root = new VBox(20);
        root.setPadding(new Insets(40, 30, 0, 30));

        Label username = new Label("Gebruikersnaam"); // Label maken voor de username
        username.getStyleClass().add("label-name");
        TextField usernameField = new TextField(); // Input field voor de username
        usernameField.setPromptText("Vul je gebruikersnaam in..."); // Prompt text
        usernameField.getStyleClass().add("input-field");

        // Gebruikersnaam label en field toevoegen aan box.
        VBox usernameBox = new VBox(username, usernameField);
        usernameBox.setSpacing(4);

        Label password = new Label("Wachtwoord"); // Label maken voor het wachtwoord.
        password.getStyleClass().add("label-name");
        PasswordField passwordField = new PasswordField(); // Input field voor het wachtwoord.
        passwordField.setPromptText("Vul je wachtwoord in..."); // Prompt text.
        passwordField.getStyleClass().add("input-field");

        // Wachtwoord label en field toevoegen aan box.
        VBox passwordBox = new VBox(password, passwordField);
        passwordBox.setSpacing(5);

        //
        Label messageLabel = new Label();
        messageLabel.getStyleClass().add("message-label");

        // Login button aanmaken
        Button loginButton = new Button("Inloggen");
        loginButton.setMaxWidth(Double.MAX_VALUE);
        loginButton.getStyleClass().add("login-button");

        // Actie toevoegen aan de login button
        loginButton.setOnAction(_ -> {

            LoginController loginController = new LoginController();

            LoginResult loginResult = loginController.login(
                    usernameField.getText(),
                    passwordField.getText()
            );

            switch (loginResult) {
                case SUCCESS -> {
                    messageLabel.setText("Login succesvol!");
                    messageLabel.setStyle("-fx-text-fill: green;");

                    DashboardView dashboardView = new DashboardView();
                    Application.setStage(dashboardView);
                }
                case INVALID_CREDENTIALS -> {
                    messageLabel.setText("Ongeldig wachtwoord!");
                    messageLabel.setStyle("-fx-text-fill: red;");
                }
                case EMPTY_FIELD -> {
                    messageLabel.setText("Vul alle velden in!");
                    messageLabel.setStyle("-fx-text-fill: red;");
                }
                case USER_NOT_FOUND -> {
                    messageLabel.setText("Gebruiker niet gevonden");
                    messageLabel.setStyle("-fx-text-fill: red");
                }
            }
        });

        // Alles toevoegen aan de root.
        root.getChildren().addAll(usernameBox, passwordBox, loginButton, messageLabel);

        return root;
    }


}
