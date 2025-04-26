package com.trivia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author santiago
 * @version 1.0
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/start.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Trivia");
        stage.setScene(scene);
        stage.show();
    }
    
}