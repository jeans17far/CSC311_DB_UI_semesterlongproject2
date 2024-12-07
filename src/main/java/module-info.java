module com.example.csc311_db_ui_semesterlongproject2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.prefs;
    requires com.azure.storage.blob;
    requires com.opencsv;
    requires bcrypt;
    requires itextpdf;

    opens viewmodel;
    exports viewmodel;
    opens dao;
    exports dao;
    opens model;
    exports model;
}