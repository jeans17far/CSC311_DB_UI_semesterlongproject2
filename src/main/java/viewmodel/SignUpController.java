package viewmodel;

import dao.DbConnectivityClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import service.UserSession;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Pattern;

/**
 * Handles user registration and input validation.
 */
public class SignUpController {

    @FXML private TextField firstNameField, lastNameField, usernameField, emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;
    @FXML private ImageView profileImageView;
    @FXML private Button selectImageButton;

    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z\\s]{1,50}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@(gmail|yahoo|hotmail|outlook|aol|icloud|protonmail|zoho|yandex|mail)\\.(com|edu|gov|org|net|io|co)$",
            Pattern.CASE_INSENSITIVE
    );
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[A-Za-z0-9_]{4,20}$");

    private final DbConnectivityClass cnUtil = new DbConnectivityClass();
    private byte[] profileImageData;

    /**
     * Initializes the controller and sets up events.
     */
    @FXML
    public void initialize() {
        selectImageButton.setOnAction(event -> selectProfileImage());
    }

    /**
     * Handles sign-up process.
     */
    @FXML
    public void handleSignUp(ActionEvent event) {
        if (validateFields()) {
            try {
                if (cnUtil.verifyUsername(usernameField.getText())) {
                    changeStatusmessage("Username already exists.");
                    return;
                }



                boolean success = cnUtil.registerUser(
                        firstNameField.getText(),
                        lastNameField.getText(),
                        usernameField.getText(),
                        emailField.getText(),
                        passwordField.getText(),
                        profileImageData
                );

                if (success) {
                    UserSession userSession = UserSession.identifyInstance(usernameField.getText(), "USER");
                    userSession.saveCredentials(usernameField.getText(), passwordField.getText());
                    changeStatusmessage("Account created successfully!");
                    goBack(event);
                } else {
                    changeStatusmessage("Failed to create account.");
                }
            } catch (Exception e) {
                changeStatusmessage("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Selects a profile image.
     */
    private void selectProfileImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                profileImageData = Files.readAllBytes(selectedFile.toPath());
                Image image = new Image(selectedFile.toURI().toString());
                profileImageView.setImage(image);
            } catch (IOException e) {
                changeStatusmessage("Error reading image file.");
            }
        }
    }

    /**
     * Validates input fields.
     */
    private boolean validateFields() {
        if (isEmptyField(firstNameField) || isEmptyField(lastNameField) ||
                isEmptyField(usernameField) || isEmptyField(emailField) || isEmptyField(passwordField)) {
            changeStatusmessage("All fields are required.");
            return false;
        }

        if (!NAME_PATTERN.matcher(firstNameField.getText()).matches()) {
            changeStatusmessage("Invalid first name.");
            return false;
        }

        if (!NAME_PATTERN.matcher(lastNameField.getText()).matches()) {
            changeStatusmessage("Invalid last name.");
            return false;
        }

        if (!USERNAME_PATTERN.matcher(usernameField.getText()).matches()) {
            changeStatusmessage("Invalid username.");
            return false;
        }

        if (!EMAIL_PATTERN.matcher(emailField.getText()).matches()) {
            changeStatusmessage("Invalid email.");
            return false;
        }

        if (passwordField.getText().length() < 8) {
            changeStatusmessage("Password must be at least 8 characters.");
            return false;
        }

        return true;
    }

    /**
     * Checks if a field is empty.
     */
    private boolean isEmptyField(TextField field) {
        return field.getText().trim().isEmpty();
    }

    /**
     * Updates the status message with fade-out effect.
     */
    private void changeStatusmessage(String message) {
        statusLabel.setText(message);
        statusLabel.setOpacity(1);
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), statusLabel);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(event -> {
            statusLabel.setText("");
            statusLabel.setOpacity(0);
        });
        fadeOut.play();
    }

    /**
     * Navigates back to the login screen.
     */
    @FXML
    public void goBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            Scene scene = new Scene(root, 500, 600);
            scene.getStylesheets().add(getClass().getResource("/css/darkTheme.css").toExternalForm());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setResizable(false);
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
