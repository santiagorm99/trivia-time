package com.trivia.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trivia.model.database.DatabaseModel;

/**
 * @author santiago
 * @version 1.0
 */
public class QuestionModel extends DatabaseModel {

    /**
     * Se crea el constructor general
     */
    public QuestionModel() {
        super();
    }

    /**
     * Metodo que devuelve una pregunta aleatoria de la base de datos
     * 
     * @return devuelve una pregunta aleatoria
     */
    public Question getRandomQuestion() {
        String query = "SELECT id, question, answer1, answer2, answer3, answer4, correct_answer FROM questions ORDER BY RANDOM() LIMIT 1";
        try (Connection connection = createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return new Question(
                        resultSet.getInt("id"),
                        resultSet.getString("question"),
                        resultSet.getString("answer1"),
                        resultSet.getString("answer2"),
                        resultSet.getString("answer3"),
                        resultSet.getString("answer4"),
                        resultSet.getInt("correct_answer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}