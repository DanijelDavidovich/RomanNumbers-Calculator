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

public class HelloApplication extends Application {

    @FXML
    private AnchorPane rnbackground;

    // fx:id "spqr" iz SceneBuilder-a
    @FXML
    private ImageView spqr;
    @FXML
    private ImageView coatofarms;
    @FXML
    private ImageView title;
    @FXML
    private ImageView sumbutton;
    @FXML
    private ImageView note;
    @FXML
    private TextField numOne;
    @FXML
    private TextField numTwo;
    @FXML
    private Label messageOne;
    @FXML
    private Label messageTwo;
    @FXML
    private Label sumMessage;
    @FXML
    private Label result;
    @FXML
    private Button sumbtn;
    @FXML
    private Button musicbtn;
    @FXML
    private ImageView btnicon;

    private MediaPlayer mediaPlayer;
    private boolean isPlaying = true;
    private Image playImage = new Image(getClass().getResourceAsStream("/images/play.png"));
    private Image pauseImage = new Image(getClass().getResourceAsStream("/images/pause.png"));



    @Override
    public void start(Stage stage) throws IOException {
        // Eksplicitno postavite kontroler pri učitavanju FXML-a
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("roman-numbers-app.fxml"));
        fxmlLoader.setController(this); // Postavljanje kontrolera na trenutni objekat

        // Učitaj FXML fajl
        AnchorPane root = fxmlLoader.load();

//        Rimski brojevi - Logika
        RomanNumber rnOne = new RomanNumber("");
        RomanNumber rnTwo = new RomanNumber("");

    numOne.textProperty().addListener((observable, oldValue, newValue) -> {
        if(RomanNumber.isValidRomanNumber(newValue)) {
            rnOne.setRomanNumber(newValue);
            int rnValue = RomanNumber.romanToDecimal(rnOne.getRomanNumber());
            messageOne.setText(String.valueOf(rnValue));
        } else{
            rnOne.setRomanNumber(newValue);
            messageOne.setText("Invalid Roman Number!");
        }

    });

    numTwo.textProperty().addListener((observable, oldValue, newValue) -> {
            if(RomanNumber.isValidRomanNumber(newValue)) {
                rnTwo.setRomanNumber(newValue);
                int rnValue = RomanNumber.romanToDecimal(rnTwo.getRomanNumber());
                messageTwo.setText(String.valueOf(rnValue));
            } else{
                rnTwo.setRomanNumber(newValue);
                messageTwo.setText("Invalid Roman Number!");
            }
        });


    sumbtn.setOnAction(event -> {
        if(rnOne.getRomanNumber() == "" || rnTwo.getRomanNumber() == "") {
            sumMessage.setText("Numbers must exist!");
        }else if(!(RomanNumber.isValidRomanNumber(rnOne.getRomanNumber()) && RomanNumber.isValidRomanNumber(rnTwo.getRomanNumber()))) {
            sumMessage.setText("Numbers must be valid!");
        }else{
            String romanNumberResult = RomanNumber.romanNumberSum(rnOne, rnTwo);
            sumMessage.setText(String.valueOf(RomanNumber.romanToDecimal(romanNumberResult)));
            result.setText(romanNumberResult);
        }
    });


    musicbtn.setOnAction(event -> {
        if (isPlaying) {
            // Pauzira muziku
            mediaPlayer.pause();
            btnicon.setImage(playImage);
//            playPauseButton.setText("Play"); // Promena teksta na dugmetu na "Play"
        } else {
            // Pokreće muziku
            btnicon.setImage(pauseImage);
            mediaPlayer.play();
//            playPauseButton.setText("Pause"); // Promena teksta na dugmetu na "Pause"
        }

        // Promenjuje stanje (da bi se dugme promenilo pri sledećem kliku)
        isPlaying = !isPlaying;
    });


        // Postavite scenu
        Scene scene = new Scene(root, 400, 550);
        Image icon = new Image(getClass().getResource("/images/icon.png").toExternalForm());

        stage.getIcons().add(icon);

        stage.setResizable(false);
        stage.setTitle("Numeri Romani Sum");
        scene.getStylesheets().add(getClass().getResource("/css/numeriRomaniSum.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
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

//        Audio Player

        String audioFilePath = getClass().getResource("/audio/romanaudio.mp3").toExternalForm(); // Putanja do audio fajla
        Media media = new Media(audioFilePath);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);  // Postavite vreme na početak
            mediaPlayer.play();               // Ponovo pokrenite muziku
        });



    }

//    private void togglePlayPause() {
//        if (isPlaying) {
//            // Pauzira muziku
//            mediaPlayer.pause();
//            playPauseButton.setText("Play"); // Promena teksta na dugmetu na "Play"
//        } else {
//            // Pokreće muziku
//            mediaPlayer.play();
//            playPauseButton.setText("Pause"); // Promena teksta na dugmetu na "Pause"
//        }
//
//        // Promenjuje stanje (da bi se dugme promenilo pri sledećem kliku)
//        isPlaying = !isPlaying;
//    }


    public static void main(String[] args) {
        launch();
    }
}

