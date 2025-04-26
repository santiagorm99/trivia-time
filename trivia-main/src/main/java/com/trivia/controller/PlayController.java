package com.trivia.controller;

import com.trivia.controller.screen.ScreenController;
import com.trivia.model.Question;
import com.trivia.model.QuestionModel;
import com.trivia.model.User;
import com.trivia.model.UserModel;
import com.trivia.model.session.SessionModel;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * @author santiago
 * @version 1.0
 */
public class PlayController extends ScreenController {

    @FXML
    private Text textPoint;
    @FXML
    private Text textTime;
    @FXML
    private Text textQuestion;
    @FXML
    private Button buttonAnswer1;
    @FXML
    private Button buttonAnswer2;
    @FXML
    private Button buttonAnswer3;
    @FXML
    private Button buttonAnswer4;
    @FXML
    private Button buttonReturn;

    private Integer answers;
    private Integer hits;
    private QuestionModel questionModel;
    private Question question;
    private Timeline timeline;
    private int timeRemaining;

    public PlayController() {
        this.answers = 0;
        this.hits = 0;
    }

    @FXML
    public void initialize() {
        timeRemaining = 0;
        questionModel = new QuestionModel();
        question = questionModel.getRandomQuestion();
        textPoint.setText("Respuestas: " + answers.toString() + "/10");
        textQuestion.setText(question.getQuestionText());
        buttonAnswer1.setText(question.getAnswer1());
        buttonAnswer2.setText(question.getAnswer2());
        buttonAnswer3.setText(question.getAnswer3());
        buttonAnswer4.setText(question.getAnswer4());

        switch (SessionModel.getLevel().toLowerCase()) {
            case "easy":
                timeRemaining = 60;
                break;
            case "medium":
                timeRemaining = 40;
                break;
            case "hard":
                timeRemaining = 20;
                break;
            default:
                timeRemaining = 40;
                break;
        }

        textTime.setText("Tiempo: " + timeRemaining + "s");

        timeline = new Timeline(new javafx.animation.KeyFrame(
                Duration.seconds(1),
                event -> {
                    timeRemaining--;
                    textTime.setText("Tiempo: " + timeRemaining + "s");

                    if (timeRemaining <= 0) {
                        timeline.stop();
                        endGame(false);
                    }
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    private void buttonAnswer1Click() {
        checkAnswer(1);
    }

    @FXML
    private void buttonAnswer2Click() {
        checkAnswer(2);
    }

    @FXML
    private void buttonAnswer3Click() {
        checkAnswer(3);
    }

    @FXML
    private void buttonAnswer4Click() {
        checkAnswer(4);
    }

    @FXML
    private void buttonReturnClick() {
        SessionModel.setLevel("Medium");
        levelScreen(buttonReturn);
    }

    /**
     * Metodo que dependiendo a la respuesta llega hasta diez
     * 
     * @param selectAnswer 
     */
    private void checkAnswer(int selectAnswer) {
        answers++;
        textPoint.setText("Respuestas: " + answers.toString() + "/10");
        if (question.getCorrectAnswer() == selectAnswer) {
            hits++;
        }
        if (answers == 10) {
            timeline.stop();
            updateUser();
            endGame(true);
        } else {
            question = questionModel.getRandomQuestion();
            textQuestion.setText(question.getQuestionText());
            buttonAnswer1.setText(question.getAnswer1());
            buttonAnswer2.setText(question.getAnswer2());
            buttonAnswer3.setText(question.getAnswer3());
            buttonAnswer4.setText(question.getAnswer4());
        }
    }

    /**
     * Metodo para actualiazar usuario
     */
    private void updateUser() {
        UserModel userModel = new UserModel();
        User user = new User(SessionModel.getUser().getName(), SessionModel.getUser().getPassword(),
                SessionModel.getUser().getAnswers() + answers, SessionModel.getUser().getHits() + hits);
        userModel.updateUser(SessionModel.getUser(), user);
        SessionModel.startSesion(user);
    }

    /**
     * Metodo que finaliza cuando el jugador gana
     * 
     * @param victory 
     */
    private void endGame(boolean victory) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            if (victory) {
                alert.setTitle("¡Juego terminado!");
                alert.setHeaderText(null);
                alert.setContentText("Respuestas correctas: " + hits);
            } else {
                alert.setTitle("¡Tiempo agotado!");
                alert.setHeaderText(null);
                alert.setContentText("Has perdido. Respuestas correctas: " + hits);
            }
            alert.showAndWait();
            buttonReturnClick();
        });
    }

}