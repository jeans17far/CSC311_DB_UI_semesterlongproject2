package viewmodel;

import dao.DbConnectivityClass;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.UserSession;

/**
 * Handles user login and navigation.
 */
public class LoginController {
    @FXML private VBox loginContainer;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;

    private final DbConnectivityClass cnUtil = new DbConnectivityClass();

    /**
     * Initializes the controller and sets background.
     */
    @FXML
    public void initialize() {
        if (loginContainer != null) {
            setBackground();
        }
    }

    /**
     * Sets the login background with a fade-in effect.
     */
    private void setBackground() {
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("https://edencoding.com/wp-content/uploads/2021/03/layer_06_1920x1080.png", true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        loginContainer.setBackground(new Background(backgroundImage));

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), loginContainer);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();
    }

    /**
     * Handles login action.
     */
    @FXML
    public void login(ActionEvent actionEvent) {
        String username = usernameTextField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            changeStatusmessage("Please enter both username and password");
            return;
        }

        try {
            if (verifyCredentials(username, password)) {
                UserSession userSession = UserSession.identifyInstance(username, "USER");
                userSession.saveCredentials(username, password);
                loadMainInterface(actionEvent);
            } else {
                changeStatusmessage("Invalid username or password");
            }
        } catch (Exception e) {
            changeStatusmessage("Login error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Verifies user credentials.
     * @return true if credentials are valid.
     */
    private boolean verifyCredentials(String username, String password) {
        return cnUtil.verifyUser(username, password);
    }

    /**
     * Loads the main application interface.
     */
    private void loadMainInterface(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/db_interface_gui.fxml"));
        Scene scene = new Scene(root, 950, 600);
        scene.getStylesheets().add(getClass().getResource("/css/darkTheme.css").toExternalForm());
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     * Updates the status label with fade-out effect.
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
     * Handles sign-up action.
     */
    @FXML
    public void signUp(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/signUp.fxml"));
            Scene scene = new Scene(root, 500, 650);
            scene.getStylesheets().add(getClass().getResource("/css/darkTheme.css").toExternalForm());
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            changeStatusmessage("Error loading signup page: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
