package com.trivia.controller;

import com.trivia.controller.screen.ScreenController;
import com.trivia.model.User;
import com.trivia.model.UserModel;
import com.trivia.model.session.SessionModel;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * @author santiago
 * @version 1.0
 */
public class ProfileController extends ScreenController {

    @FXML
    private Text textUser;
    @FXML
    private TextField textFieldUser;
    @FXML
    private PasswordField passwordFieldPassword;
    @FXML
    private Text textMessage;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonClose;
    @FXML
    private Button buttonReturn;
    @FXML
    private Button buttonDelete;

    /**
     * Metodo para iniciar el usuario
     */
    @FXML
    public void initialize() {
        if (SessionModel.getUser() != null) {
            textUser.setText(SessionModel.getUser().getName());
        } else {
            textUser.setText("Usuario no identificado");
        }
    }

    /**
     * Metodo para actualizar los datos del usuario
     */
    @FXML
    public void buttonUpdateClick() {
        if (!validateCredentials()) {
            textMessage.setText("Credenciales invalidas");
            return;
        }
        UserModel userModel = new UserModel();
        User user = new User(textFieldUser.getText(), passwordFieldPassword.getText(),
                SessionModel.getUser().getAnswers(), SessionModel.getUser().getHits());
        userModel.updateUser(SessionModel.getUser(), user);
        textMessage.setText("Usuario actualizado");
    }

    /**
     * Metodo para cerrar sesion de usuario
     */
    @FXML
    public void buttonCloseClick() {
        SessionModel.endSesion();
        startScreen(buttonClose);
    }

    /**
     * Metodo que vuelve a la pantalla del nivel
     */
    @FXML
    public void buttonReturnClick() {
        levelScreen(buttonReturn);
    }

    /**
     * Metodo para eliminar la cuenta del usuario
     */
    @FXML
    public void buttonDeleteClick() {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar eliminación");
        alert.setHeaderText("¿Estás seguro de que deseas eliminar tu cuenta?");
        alert.setContentText("Esta acción no se puede deshacer.");

       
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                
                UserModel userModel = new UserModel();
                userModel.deleteUser(SessionModel.getUser());
                startScreen(buttonDelete); 
            } else {
                
                alert.close();
            }
        });
    }

    /**
     * Metodo para validar el usuario y contrasenia
     * 
     * @return 
     */
    private boolean validateCredentials() {
        return (textFieldUser != null && textFieldUser.getText() != null && !textFieldUser.getText().isBlank() &&
                passwordFieldPassword != null && passwordFieldPassword.getText() != null
                && !passwordFieldPassword.getText().isBlank());
    }

}