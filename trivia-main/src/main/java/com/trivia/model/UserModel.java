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
public class UserModel extends DatabaseModel {

    /**
     * Se crea el constructor general
     */
    public UserModel() {
        super();
    }

    /**
     * Metodo que agrega un nuevo usuario a la bbdd
     * 
     * @param user 
     * @return devuelve a true si ha registrado correctamente
     */
    public boolean createUser(User user) {
        String query = "INSERT INTO users(name, password) VALUES (?, ?)";
        try (Connection connection = createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Metodo que encuentra un usuario en la bbdd 
     * 
     * @param user 
     * @return devuelve el usuario buscado
     */
    public User readUser(User user) {
        String query = "SELECT name, password, answers, hits FROM users WHERE name = ? AND password = ?";
        try (Connection connection = createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String password = resultSet.getString("password");
                    Integer answers = resultSet.getInt("answers");
                    Integer hits = resultSet.getInt("hits");
                    return new User(name, password, answers, hits);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo que actualiza el usuario en la bbdd
     * 
     * @param user       
     * @param updateUser 
     * @return 
     */
    public boolean updateUser(User user, User updateUser) {
        String query = "UPDATE users SET name = ?, password = ?, answers = ?, hits = ? WHERE name = ? AND password = ? ";
        try (Connection connection = createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, updateUser.getName());
            preparedStatement.setString(2, updateUser.getPassword());
            preparedStatement.setInt(3, updateUser.getAnswers());
            preparedStatement.setInt(4, updateUser.getHits());
            preparedStatement.setString(5, user.getName());
            preparedStatement.setString(6, user.getPassword());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Metodo que elimina el usuario de la bbdd
     * 
     * @param user 
     * @return 
     */
    public boolean deleteUser(User user) {
        String query = "DELETE FROM users WHERE name = ? AND password = ?";
        try (Connection connection = createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}