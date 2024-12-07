package viewmodel;

import com.azure.storage.blob.BlobClient;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.DbConnectivityClass;
import dao.UpdateStorage;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Person;
import service.MyLogger;

import java.io.*;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * Controller for the Student Data Management System GUI.
 */
public class DB_GUI_Controller implements Initializable {

    UpdateStorage store = new UpdateStorage();
    @FXML private MenuItem importCSVMenuItem, exportCSVMenuItem;
    @FXML private ProgressBar progressBar;
    @FXML private TextField first_name, last_name, email, studentCookiehold;
    @FXML private ComboBox<grade> gradeComboBox;
    @FXML private Label statusLabel;
    @FXML private ImageView img_view;
    @FXML private MenuBar menuBar;
    @FXML private TableView<Person> tv;
    @FXML private TableColumn<Person, Integer> tv_id;
    @FXML private TableColumn<Person, String> tv_fn, tv_ln, tv_grade, tv_studentCookiehold, tv_email;
    @FXML private Button addBtn, deleteBtn, editBtn, addImageBttn, deleteImageBttn;
    @FXML private MenuItem editItem, deleteItem;

    private final DbConnectivityClass cnUtil = new DbConnectivityClass();
    private final ObservableList<Person> data = cnUtil.getData();
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z\\s]{1,50}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@(gmail|yahoo|hotmail|outlook|aol|icloud|protonmail|zoho|yandex|mail)\\.(com|edu|gov|org|net|io|co)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern PERFORMANCE_RATING_PATTERN = Pattern.compile("^(10(\\.0{1,2})?|[1-9](\\.\\d{1,2})?)$");

    /**
     * Initializes the GUI controller.
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            tv_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            tv_fn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            tv_ln.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            tv_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
            tv_studentCookiehold.setCellValueFactory(new PropertyValueFactory<>("studentCookiehold"));
            tv_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            tv.setItems(data);

            gradeComboBox.setItems(FXCollections.observableArrayList(grade.values()));

            editBtn.disableProperty().bind(tv.getSelectionModel().selectedItemProperty().isNull());
            deleteBtn.disableProperty().bind(tv.getSelectionModel().selectedItemProperty().isNull());
            editItem.disableProperty().bind(tv.getSelectionModel().selectedItemProperty().isNull());
            deleteItem.disableProperty().bind(tv.getSelectionModel().selectedItemProperty().isNull());
            addImageBttn.disableProperty().bind(tv.getSelectionModel().selectedItemProperty().isNull());
            deleteImageBttn.disableProperty().bind(tv.getSelectionModel().selectedItemProperty().isNull());

            validationPlus(first_name, NAME_PATTERN);
            validationPlus(last_name, NAME_PATTERN);
            validationPlus(email, EMAIL_PATTERN);
            validationPlus(studentCookiehold, PERFORMANCE_RATING_PATTERN);

            addBtn.disableProperty().bind(
                    Bindings.isEmpty(first_name.textProperty())
                            .or(Bindings.isEmpty(last_name.textProperty()))
                            .or(gradeComboBox.valueProperty().isNull())
                            .or(Bindings.isEmpty(email.textProperty()))
                            .or(Bindings.isEmpty(studentCookiehold.textProperty()))
                            .or(first_name.styleProperty().isEqualTo("-fx-border-color: red;"))
                            .or(last_name.styleProperty().isEqualTo("-fx-border-color: red;"))
                            .or(email.styleProperty().isEqualTo("-fx-border-color: red;"))
                            .or(studentCookiehold.styleProperty().isEqualTo("-fx-border-color: red;"))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles CSV import.
     */
    @FXML
    private void importCSV() {
        // Implementation truncated for brevity
    }

    /**
     * Handles CSV export.
     */
    @FXML
    private void exportCSV() {
        // Implementation truncated for brevity
    }

    /**
     * Generates a grade report in PDF format.
     */
    @FXML
    private void displayGrade() {
        // Implementation truncated for brevity
    }

    /**
     * Adds a new record.
     */
    @FXML
    protected void addNewRecord() {
        // Implementation truncated for brevity
    }

    /**
     * Clears input fields.
     */
    @FXML
    protected void clearForm() {
        first_name.clear();
        last_name.clear();
        gradeComboBox.getSelectionModel().selectFirst();
        email.clear();
        studentCookiehold.clear();
        tv.getSelectionModel().clearSelection();

    }

    /**
     * Logs out the user.
     */
    @FXML
    protected void logOut(ActionEvent actionEvent) {
        // Implementation truncated for brevity
    }

    /**
     * Closes the application.
     */
    @FXML
    protected void closeApplication() {
        System.exit(0);
    }

    /**
     * Displays the "About" dialog.
     */
    @FXML
    protected void displayAbout() {
        // Implementation truncated for brevity
    }

    /**
     * Edits an existing record.
     */
    @FXML
    protected void editRecord() {
        // Implementation truncated for brevity
    }

    /**
     * Deletes a record.
     */
    @FXML
    protected void deleteRecord() {
        // Implementation truncated for brevity
    }

    /**
     * Adds a validation listener to a text field.
     */
    private void validationPlus(TextField textField, Pattern pattern) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (pattern.matcher(newValue).matches()) {
                textField.setStyle("-fx-border-color: green;");
            } else {
                textField.setStyle("-fx-border-color: red;");
            }
        });
    }

    /**
     * Enum for grade types.
     */
    public enum grade {
        firstgrade, Secondgrade, thirdgrade, fourthgrade, fifthgrade;
    }

    /**
     * Inner class for new user dialog results.
     */
    private static class Results {
        String fname, lname;
        grade major;

        public Results(String name, String date, grade venue) {
            this.fname = name;
            this.lname = date;
            this.major = venue;
        }
    }
}
