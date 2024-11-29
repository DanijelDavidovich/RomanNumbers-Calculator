module com.example.romannumberscalculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;  // Ovdje dodajemo javafx.media za rad sa zvukom/video

    opens com.example.romannumberscalculator to javafx.fxml;
    exports com.example.romannumberscalculator;
}
