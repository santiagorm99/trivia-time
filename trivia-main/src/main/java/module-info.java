module com.trivia {
    requires javafx.fxml;
    requires javafx.controls;
    requires transitive java.sql;
    requires org.controlsfx.controls;
    requires transitive javafx.graphics;

    exports com.trivia;
    exports com.trivia.model;
    exports com.trivia.controller;

    opens com.trivia to javafx.fxml;
    opens com.trivia.model to javafx.fxml;
    opens com.trivia.controller to javafx.fxml;
}