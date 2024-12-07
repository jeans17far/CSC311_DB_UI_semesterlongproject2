package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Person;
import service.MyLogger;
import at.favre.lib.crypto.bcrypt.BCrypt;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles Students cookies database operations for the Students Management System.
 */
public class DbConnectivityClass {

    private static final String DB_NAME = "CSC311_BD_TEMP2";
    private static final String SQL_SERVER_URL = "jdbc:mysql://csc311server.mysql.database.azure.com/";
    private static final String DB_URL = "jdbc:mysql://csc311server.mysql.database.azure.com/"+ DB_NAME;
    private static final String USERNAME = "csc311admin";
    private static final String PASSWORD = "Samtall302";
    private final ObservableList<Person> data = FXCollections.observableArrayList();
    private final MyLogger logger = new MyLogger();

    /**
     * Connects to the database and ensures tables exist.
     * @return true if users are registered, false otherwise.
     */
    public boolean connectToDatabase() {
        boolean hasRegistredUsers = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(SQL_SERVER_URL, USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            statement.close();
            conn.close();

            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "first_name VARCHAR(200) NOT NULL," +
                    "last_name VARCHAR(200) NOT NULL," +
                    "username VARCHAR(200) NOT NULL UNIQUE," +
                    "password VARCHAR(200) NOT NULL," +
                    "grade VARCHAR(200)," +
                    "performance_rating DOUBLE," +
                    "email VARCHAR(200) NOT NULL UNIQUE," +
                    "profile_picture LONGBLOB)";
            statement.executeUpdate(sql);

            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users");
            if (resultSet.next() && resultSet.getInt(1) > 0) {
                hasRegistredUsers = true;
            }
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasRegistredUsers;
    }

    /**
     * Retrieves all users from the database.
     * @return ObservableList of Person objects.
     */
    public ObservableList<Person> getData() {
        connectToDatabase();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM users";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                logger.makeLog("No data");
            }
            while (resultSet.next()) {
                Person person = new Person(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("grade"),
                        resultSet.getDouble("performance_rating"),
                        resultSet.getString("email")
                );
                person.setProfilePicture(resultSet.getBytes("profile_picture"));
                data.add(person);
            }
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Registers a new user in the database.
     * @return true if successful, false otherwise.
     */
    public boolean registerUser(String firstName, String lastName, String username, String email, String password, byte[] profilePicture) {
        connectToDatabase();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            conn.setAutoCommit(false);



            String sql = "INSERT INTO users (first_name, last_name, username, email, password, profile_picture) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, email);

            String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
            preparedStatement.setString(5, hashedPassword);

            if (profilePicture != null) {
                preparedStatement.setBytes(6, profilePicture);
            } else {
                preparedStatement.setNull(6, java.sql.Types.BLOB);
            }

            int affectedRows = preparedStatement.executeUpdate();
            conn.commit();
            return affectedRows > 0;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Checks if a username exists.
     * @param username Username to check.
     * @return true if exists, false otherwise.
     */
    public boolean verifyUsername(String username) {
        connectToDatabase();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            preparedStatement.close();
            conn.close();
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean verifyUser(String username, String password) {
        return false;
    }
}
