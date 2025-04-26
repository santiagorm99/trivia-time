package com.trivia.controller;

import com.trivia.controller.screen.ScreenController;
import com.trivia.model.User;
import com.trivia.model.UserModel;
import com.trivia.model.session.SessionModel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * @author santiago
 * @version 1.0
 */
public class StartController extends ScreenController {

    @FXML
    private TextField textFieldUser;
    @FXML
    private PasswordField passwordFieldPassword;
    @FXML
    private Text textMessage;
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonCreate;

    /**
     * Metodo para iniciar sesion de usuario
     */
    @FXML
    public void buttonStartClick() {
    if (!validateCredentials()) {
        textMessage.setText("Credenciales invalidas");
        return;
    }
    UserModel userModel = new UserModel();
    User user = new User(textFieldUser.getText(), passwordFieldPassword.getText());
    User startUser = userModel.readUser(user);
    if (startUser == null) {
        textMessage.setText("Credenciales incorrectas");
        return;
    }
    textMessage.setText("");
    SessionModel.startSesion(startUser);
    levelScreen(buttonStart);
    }

    /**
     * Metodo que cambia de pantalla
     *
     */
    @FXML
    public void buttonCreateClick() {
        createScreen(buttonCreate);
    }

    /**
     * Metodo que valida las credenciales del Usuario
     * 
     * @return retorna true si estas son validas.
     */
    private boolean validateCredentials() {
        return (textFieldUser != null && textFieldUser.getText() != null && !textFieldUser.getText().trim().isBlank() &&
                passwordFieldPassword != null && passwordFieldPassword.getText() != null
                && !passwordFieldPassword.getText().trim().isBlank());
    }

}