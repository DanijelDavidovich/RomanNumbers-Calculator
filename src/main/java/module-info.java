module com.example.romannumberscalculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.romannumberscalculator to javafx.fxml;
    exports com.example.romannumberscalculator;
}