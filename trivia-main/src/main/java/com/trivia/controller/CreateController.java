package com.trivia.controller;

import com.trivia.controller.screen.ScreenController;
import com.trivia.model.User;
import com.trivia.model.UserModel;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * @author santiago
 * @version 1.0
 */
public class CreateController extends ScreenController {

    @FXML
    private TextField textFieldUser;
    @FXML
    private PasswordField passwordFieldPassword;
    @FXML
    private PasswordField passwordFieldRepeatPassword;
    @FXML
    private Text textMessage;
    @FXML
    private Button buttonAccept;
    @FXML
    private Button buttonStart;

    /**
     * Metodo para crear el usuario
     */
    @FXML
    public void buttonAcceptClick() {
        if (!validateCredentials()) {
            textMessage.setText("Credenciales invalidas");
            return;
        }
        if (!passwordFieldPassword.getText().equals(passwordFieldRepeatPassword.getText())) {
            textMessage.setText("Las contraseñas deben coincidir");
            return;
        }
        User user = new User(textFieldUser.getText(), passwordFieldPassword.getText());
        UserModel userModel = new UserModel();
        if (userModel.createUser(user)) {
            textMessage.setText("Usuario creado con exito");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("Sesión creada con éxito. Redirigiendo a la pantalla de inicio...");
            alert.showAndWait();

            startScreen(buttonStart);
            return;
        }
        textMessage.setText("Usuario no pudo crearse");
    }

    /**
     * Metodo que cambia de pantalla
     */
    @FXML
    public void buttonStartClick() {
        startScreen(buttonStart);
    }

    /**
     * Metodo que valida las credenciales del usuario
     * 
     * @return retorna true si estas son validas.
     */
    private boolean validateCredentials() {
        return (textFieldUser != null && textFieldUser.getText() != null && !textFieldUser.getText().trim().isBlank() &&
                passwordFieldPassword != null && passwordFieldPassword.getText() != null
                && !passwordFieldPassword.getText().trim().isBlank() &&
                passwordFieldRepeatPassword != null && passwordFieldRepeatPassword.getText() != null
                && !passwordFieldRepeatPassword.getText().trim().isBlank());
    }

}