module com.example.romannumberscalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.romannumberscalculator to javafx.fxml;
    exports com.example.romannumberscalculator;
}