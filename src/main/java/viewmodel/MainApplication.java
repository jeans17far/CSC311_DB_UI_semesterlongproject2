package viewmodel;

import dao.DbConnectivityClass;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Main class for the Student Database Manager.
 */
public class MainApplication extends Application {

    private static Scene scene;
    private static DbConnectivityClass cnUtil;
    private Stage primaryStage;

    /**
     * Main method. Launches the application.
     */
    public static void main(String[] args) {
        cnUtil = new DbConnectivityClass();
        launch(args);
    }

    /**
     * Starts the application.
     */
    public void start(Stage primaryStage) {
        Image icon = new Image(getClass().getResourceAsStream("/images/DollarClouddatabase.png"));
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Student Cookies Database Manager");
        showSplashScreen();
    }

    /**
     * Displays the splash screen.
     */
    private void showSplashScreen() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/splashscreen.fxml"));
            Scene scene = new Scene(root, 500, 600);
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource("/css/darkTheme.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
            transitionToLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Transitions from splash to login screen with a fade-out effect.
     */
    public void transitionToLogin() {
        try {
            Parent newRoot = FXMLLoader.load(getClass().getResource("/view/login.fxml").toURI().toURL());
            Scene currentScene = primaryStage.getScene();
            Parent currentRoot = currentScene.getRoot();
            currentScene.getStylesheets().clear();
            currentScene.getStylesheets().add(getClass().getResource("/css/darkTheme.css").toExternalForm());
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), currentRoot);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(e -> {
                Scene newScene = new Scene(newRoot, 500, 600);
                newScene.getStylesheets().add(getClass().getResource("/css/darkTheme.css").toExternalForm());
                primaryStage.setScene(newScene);
                primaryStage.show();
            });
            fadeOut.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
