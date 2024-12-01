package com.example.romannumberscalculator;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


import java.io.IOException;

public class RomanNumbersCalculator extends Application {

    @FXML
    private AnchorPane rnbackground;
    @FXML
    private ImageView spqr; // Za prikaz gornjeg grba
    @FXML
    private ImageView coatofarms; // Za prikaz donjeg grba
    @FXML
    private ImageView title; // Naslov: Numeri Romani Sum
    @FXML
    private ImageView note; // Za prikaz note
    @FXML
    private TextField numOne; // Polje za upis prvog broja
    @FXML
    private TextField numTwo; // Polje za upis drugog broja
    @FXML
    private Label messageOne; // Za ispis poruke ispod prvog polja
    @FXML
    private Label messageTwo; // Za ispis poruke ispod drugog polja
    @FXML
    private Label sumMessage; // Za ispis poruke ispod polja gdje se prikazuje suma
    @FXML
    private Label result; // Za ispis rezultata
    @FXML
    private Button sumbtn; // Button za sumiranje
    @FXML
    private Button musicbtn; // Button za pause i play muzike
    @FXML
    private ImageView btnicon; // Ikonica koja se nalazi u plaz/pause button-u

    private MediaPlayer mediaPlayer; // Kreira se muzicki player
    private boolean isPlaying = true; // Setujemo da muzika pocinje pri pokretanju aplikacije

    private Image playImage = new Image(getClass().getResourceAsStream("/images/play.png"));
    private Image pauseImage = new Image(getClass().getResourceAsStream("/images/pause.png"));



    @Override
    public void start(Stage stage) throws IOException {
        // Eksplicitno postavljamo kontroler pri učitavanju FXML-a
        FXMLLoader fxmlLoader = new FXMLLoader(RomanNumbersCalculator.class.getResource("roman-numbers-app.fxml"));
        fxmlLoader.setController(this); // Postavljamo kontroler na trenutni objekat

        AnchorPane root = fxmlLoader.load(); // Učitaj FXML fajl

//  UCITAVAMO RIMSKE BROJEVE

        RomanNumber rnOne = new RomanNumber("");
        RomanNumber rnTwo = new RomanNumber("");

    // Poziv metode koja kupi unos u polje pri svakom keystroke-u, probjerava validnost. Ako je ok, setuj objekat
        // i ispisi decimalnu vrijednost ispod, a ako nije ok, ispisi poruku ispod polje
    numOne.textProperty().addListener((observable, oldValue, newValue) -> {
        if(RomanNumber.isValidRomanNumber(newValue) && !RomanNumber.lesThenMChecker(newValue)) {
            messageOne.setText("The value must be up to M (1000)");
        }
        else if(RomanNumber.isValidRomanNumber(newValue)) {
            rnOne.setRomanNumber(newValue);
            int rnValue = RomanNumber.romanToDecimal(rnOne.getRomanNumber());
            messageOne.setText(String.valueOf(rnValue));
        } else{
            rnOne.setRomanNumber(newValue);
            messageOne.setText("Invalid Roman Number!");
        }

    });
    // Za drugi broj
    numTwo.textProperty().addListener((observable, oldValue, newValue) -> {
            if(RomanNumber.isValidRomanNumber(newValue) && !RomanNumber.lesThenMChecker(newValue)) {
                messageTwo.setText("The value must be up to M (1000)");
            }
            else if(RomanNumber.isValidRomanNumber(newValue)) {
                rnTwo.setRomanNumber(newValue);
                int rnValue = RomanNumber.romanToDecimal(rnTwo.getRomanNumber());
                messageTwo.setText(String.valueOf(rnValue));
            } else{
                rnTwo.setRomanNumber(newValue);
                messageTwo.setText("Invalid Roman Number!");
            }
        });

//    SUMIRANJE

//        Klikom na button Sum, trigger-uje se metoda. Provjerava da li brojevi postoje. Ako ne, ispise poruku.
//        Ako da, provjerava validnost. Ako bar jedan nije validan, ispise poruku.
//        Ako postoje i ako su validni, poziva se staticka metoda za sumiranje, dobijamo String koji ispisemo
//        a ispod polja se ispise i njegova decimalna vrijednost.
    sumbtn.setOnAction(event -> {
        if(rnOne.getRomanNumber() == "" || rnTwo.getRomanNumber() == "") {
            sumMessage.setText("Numbers must exist!");
        }else if(!(RomanNumber.isValidRomanNumber(rnOne.getRomanNumber()) && RomanNumber.isValidRomanNumber(rnTwo.getRomanNumber()))) {
            sumMessage.setText("Numbers must be valid!");
        }
        else{
            String romanNumberResult = RomanNumber.romanNumberSum(rnOne, rnTwo);
            sumMessage.setText(String.valueOf(RomanNumber.romanToDecimal(romanNumberResult)));
            result.setText(romanNumberResult);
        }
    });




//  POSTAVKA SCENE

        Scene scene = new Scene(root, 400, 550); // Kreira se scena sa dimenzijama prozora
        Image icon = new Image(getClass().getResource("/images/icon.png").toExternalForm()); // Nova ikonica prozora

        stage.getIcons().add(icon); // Postavljanje ikonice

        stage.setResizable(false); // Zabrana minenjanja dimenzija prozora
        stage.setTitle("Numeri Romani Sum"); // Naslov
        scene.getStylesheets().add(getClass().getResource("/css/numeriRomaniSum.css").toExternalForm()); // Povezivanje sa CSS file-om
        stage.setScene(scene); // Setovanje scene
        stage.show(); // Prikaz scene
    }

    // Ova metoda se automatski poziva nakon što je FXML učitan
    @FXML
    public void initialize() {

        btnicon.setImage(pauseImage);

        // Ovdje možemo postaviti stil pozadine nakon što su svi objekti povezani
        if (rnbackground != null) {
            rnbackground.setStyle("-fx-background-color: #800000;");  // Postavite bordo boju pozadine
        } else {
            System.out.println("AnchorPane nije učitan.");
        }

        // Postavite sliku na ImageView sa fx:id="spqr"
        if (spqr != null) {
            Image spqrimg = new Image(getClass().getResource("/images/spqr-01.png").toExternalForm());
            spqr.setImage(spqrimg);  // Postavljanje slike na ImageView
        } else {
            System.out.println("ImageView 'spqr' nije učitan.");
        }

        if (coatofarms != null) {
            Image coatofarmsimg = new Image(getClass().getResource("/images/coatofarms.png").toExternalForm());
            coatofarms.setImage(coatofarmsimg);  // Postavljanje slike na ImageView
        } else {
            System.out.println("ImageView 'spqr' nije učitan.");
        }

        if (title != null) {
            Image titleimg = new Image(getClass().getResource("/images/title.png").toExternalForm());
            title.setImage(titleimg);  // Postavljanje slike na ImageView
        } else {
            System.out.println("ImageView 'spqr' nije učitan.");
        }

        if (note != null) {
            Image noteIcon = new Image(getClass().getResource("/images/sound.png").toExternalForm());
            note.setImage(noteIcon);  // Postavljanje slike na ImageView
        } else {
            System.out.println("ImageView 'spqr' nije učitan.");
        }

// PODZADINSKA MUZIKA

        String audioFilePath = getClass().getResource("/audio/romanaudio.mp3").toExternalForm(); // Putanja do audio fajla
        Media media = new Media(audioFilePath);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        // Kada muzika zavrsi, pokreni ponovo
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
        });

// Muzika pocinje sa pokretanjem aplikacije. Klikom na dugme play/pause trigger-uje se ova metoda
        musicbtn.setOnAction(event -> {
            if (isPlaying) {
                // stop
                mediaPlayer.pause(); // zaustavlja muziku
                btnicon.setImage(playImage); // mijenja ikonicu na play
            } else {
                // start
                btnicon.setImage(pauseImage); // pusta muziku
                mediaPlayer.play(); // mijenja ikonicu na pause
            }
            isPlaying = !isPlaying; // mijenja stanje pri svakom kliku na button
        });
    }

    public static void main(String[] args) {
        launch();
    }
}

