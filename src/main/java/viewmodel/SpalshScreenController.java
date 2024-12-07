package viewmodel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller class for the splash screen view.
 * Displays a welcome message and handles interactions on the splash screen.
 */
public class SpalshScreenController {

    @FXML
    private Label welcomeText;

    /**
     * Handles the button click event on the splash screen.
     * Updates the welcome text when the button is clicked.
     */
    @FXML
    protected void onButtonClick() {
        welcomeText.setText("Welcome to the Student Cookie Management application!");
    }
}
