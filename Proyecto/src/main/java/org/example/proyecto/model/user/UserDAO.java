package org.example.proyecto.model.user;

import java.sql.SQLException;
import java.util.List;

/**
 * The UserDAO interface provides methods for interacting with user data in the database.
 *
 * @version 1.0
 * @since 2024-05-28
 * @author Omar
 */
public interface UserDAO {

    /**
     * Retrieves a list of all users from the database.
     *
     * @return A list of UserDTO objects representing the users retrieved from the database.
     * @throws SQLException If an error occurs while accessing the database.
     */
    List<UserDTO> getUsers() throws SQLException;

    /**
     * Performs user login authentication by verifying the provided email and password against the database.
     *
     * @param userEmail  The email address of the user trying to log in.
     * @param userPasswd The password of the user trying to log in.
     * @return A UserDTO object representing the authenticated user if login is successful, or null if authentication fails.
     * @throws SQLException If an error occurs while accessing the database.
     */
    UserDTO userLogin(String userEmail, String userPasswd) throws SQLException;

    /**
     * Inserts a new user into the database.
     *
     * @param newUser A UserDTO object representing the user to be inserted.
     * @return true if the user is successfully inserted, false otherwise.
     * @throws SQLException If an error occurs while accessing the database.
     */
    boolean insertUser(UserDTO newUser) throws SQLException;


    /**
     * Deletes a user from the database.
     *
     * @param deletedUser A UserDTO object representing the user to be deleted.
     * @return true if the user is successfully deleted, false otherwise.
     * @throws SQLException If an error occurs while accessing the database.
     */
    boolean deleteUser(UserDTO deletedUser) throws SQLException;

    /**
     * Updates user information in the database.
     *
     * @param updatedUser A UserDTO object representing the updated user information.
     * @return true if the user information is successfully updated, false otherwise.
     * @throws SQLException If an error occurs while accessing the database.
     */

    boolean updateUser(UserDTO updatedUser) throws SQLException;
}
