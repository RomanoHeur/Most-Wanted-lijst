package nl.ad.mostwantedlijst.service;

import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

/**
 * Service klasse voor het selecteren van een afbeelding.
 */
public class FileChooserService {

    public String chooseImage(Window window) {

        // Maakt een filechooser aan.
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");

        // Voeg filters toe voor alleen afbeeldingen.
        fileChooser.getExtensionFilters().add(
          new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg")
        );

        // Opent de filechooser.
        File file = fileChooser.showOpenDialog(window);

        // Als er een bestand is geselecteerd, gaat die naar het absolute pad.
        if (file != null) {
            return file.getAbsolutePath();
        }

        // Anders wordt er null terug gegeven.
        return null;
    }

}
