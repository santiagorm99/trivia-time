package com.trivia.controller;

import com.trivia.controller.screen.ScreenController;
import com.trivia.model.session.SessionModel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

/**
 * @author santiago
 * @version 1.0
 */
public class LevelController extends ScreenController {

    @FXML
    private Text textUser;
    @FXML
    private Text textAnswers;
    @FXML
    private Text textHits;
    @FXML
    private ComboBox<String> comboBoxLevel;
    @FXML
    private Button buttonPlay;
    @FXML
    private Button buttonProfile;
    @FXML
    private Button buttonStart;

    /**
     * Metodo nada mas iniciarse
     */
    @FXML
    public void initialize() {
        if (SessionModel.getUser() != null) {
            textUser.setText(SessionModel.getUser().getName());
            textAnswers.setText("Respuestas realizadas: " + SessionModel.getUser().getAnswers());
            textHits.setText("Respuestas correctas: " + SessionModel.getUser().getHits());
        } else {
            textUser.setText("Usuario no identificado");
        }
        comboBoxLevel.getItems().addAll("Easy", "Medium", "Hard");
        comboBoxLevel.setValue("Medium");
    }

    /**
     * Metodo que cambia la dificultad
     */
    @FXML
    public void comboBoxLevelClick() {
        SessionModel.setLevel(comboBoxLevel.getValue());
        textAnswers.setText("Respuestas realizadas: " + SessionModel.getUser().getAnswers());
        textHits.setText("Respuestas correctas: " + SessionModel.getUser().getHits());
    }

    /**
     * Metodo que cambia la pantalla a Jugar
     */
    @FXML
    public void buttonPlayClick() {
        playScreen(buttonPlay);
    }

    /**
     * Metodo que cambia a la pantalla de perfil
     */
    @FXML
    public void buttonProfileClick() {
        profileScreen(buttonProfile);
    }

    /**
     * Metodo que cambia a la pantalla Iniciar
     */
    @FXML
    public void buttonStartClick() {
        startScreen(buttonStart);
    }

}