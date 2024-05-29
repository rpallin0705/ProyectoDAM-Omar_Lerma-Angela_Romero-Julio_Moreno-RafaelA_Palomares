package org.example.proyecto.model.user;

import org.example.proyecto.SetUpConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The UserDB class implements the UserDAO interface and provides methods to interact with user data in the database.
 *
 * @version 1.0
 * @since 2024-05-28
 * @author Omar
 */
public class UserDB implements UserDAO {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    /**
     * Constructs a new UserDB object.
     *
     * @throws SQLException If an SQL exception occurs.
     * @throws IOException  If an IO exception occurs.
     */
    public UserDB() throws SQLException, IOException {
        connection = SetUpConnection.getInstance().getConnection();
    }

    /**
     * Retrieves a list of all users from the database.
     *
     * @return A list of UserDTO objects representing the users retrieved from the database.
     * @throws SQLException If an error occurs while accessing the database.
     */
    @Override
    public List<UserDTO> getUsers() throws SQLException {
        List<UserDTO> users = new ArrayList<>();
        String sql = "SELECT * FROM vista_usuarios ;";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        UserDTO userDTO = null;
        while (resultSet.next()) {
            int id_cuenta = resultSet.getInt("id_cuenta");
            String email = resultSet.getString("email");
            String contrasena = resultSet.getString("contrasena");
            String nombreApellidos = resultSet.getString("nombre_apellidos");
            boolean admin = resultSet.getBoolean("admin");
            userDTO = new UserDTO(id_cuenta, email, nombreApellidos, admin, contrasena);
            users.add(userDTO);
        }
        return users;
    }

    /**
     * Performs user login authentication by verifying the provided email and password against the database.
     *
     * @param userEmail  The email address of the user trying to log in.
     * @param userPasswd The password of the user trying to log in.
     * @return A UserDTO object representing the authenticated user if login is successful, or null if authentication fails.
     * @throws SQLException If an error occurs while accessing the database.
     */
    @Override
    public UserDTO userLogin(String userEmail, String userPasswd) throws SQLException {
        String sql = "SELECT * FROM vista_usuarios WHERE email = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userEmail);
        ResultSet resultSet = preparedStatement.executeQuery();
        UserDTO logedUser = null;
        if (resultSet.next() && resultSet.getString("contrasena").equals(userPasswd)) {
            int id_cuenta = resultSet.getInt("id_cuenta");
            String email = resultSet.getString("email");
            String contrasena = resultSet.getString("contrasena");
            String nombreApellidos = resultSet.getString("nombre_apellidos");
            boolean admin = resultSet.getBoolean("admin");
            logedUser = new UserDTO(id_cuenta, email, nombreApellidos, admin, contrasena);
        } else
            throw new SQLException("Contraseña o Email incorrectos");
        return logedUser;

    }

    /**
     * Inserts a new user into the database.
     *
     * @param newUser A UserDTO object representing the user to be inserted.
     * @return true if the user is successfully inserted, false otherwise.
     * @throws SQLException If an error occurs while accessing the database.
     */
    @Override
    public boolean insertUser(UserDTO newUser) throws SQLException {
        //cuentas
        String sql = "INSERT INTO cuentas (email, nombre_apellidos) VALUES (?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, newUser.getEmail());
        preparedStatement.setString(2, newUser.getNombre_apellidos());
        int rowsAffected = preparedStatement.executeUpdate();
        //conseguir id_cuenta de la cuenta insertada
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        int idConseguido = generatedKeys.getInt(1);
        //usuario
        String sql2 = "INSERT INTO usuarios (id_cuenta, contrasena, admin) VALUES (?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setInt(1, idConseguido);
        preparedStatement.setString(2, newUser.getContrasena());
        preparedStatement.setBoolean(3, newUser.isAdmin());
        rowsAffected += preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }

    /**
     * Deletes a user from the database using its email address as identifier.
     *
     * @param deletedUser The user to be deleted.
     * @return true if the user is successfully deleted, false otherwise.
     * @throws SQLException If an error occurs while accessing the database.
     */
    @Override
    public boolean deleteUser(UserDTO deletedUser) throws SQLException {
        //usuarios && cuentas
        String sql = "DELETE FROM usuarios WHERE id_cuenta = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, deletedUser.getId_cuenta());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }

    /**
     * Updates user information in the database.
     *
     * @param updatedUser A UserDTO object representing the updated user information.
     * @return true if the user information is successfully updated, false otherwise.
     * @throws SQLException If an error occurs while accessing the database.
     */
    @Override
    public boolean updateUser(UserDTO updatedUser) throws SQLException {
        //cuentas
        String sql = "UPDATE cuentas SET email = ?, nombre_apellidos = ? WHERE id_cuenta = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, updatedUser.getEmail());
        preparedStatement.setString(2, updatedUser.getNombre_apellidos());
        preparedStatement.setInt(3, updatedUser.getId_cuenta());
        int rowsAffected = preparedStatement.executeUpdate();
        //usuarios
        String sql2 = "UPDATE usuarios SET admin = ?, contrasena = ? WHERE id_cuenta = ?";
        preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setBoolean(1, updatedUser.isAdmin());
        preparedStatement.setString(2, updatedUser.getContrasena());
        preparedStatement.setInt(3, updatedUser.getId_cuenta());
        rowsAffected += preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }
}
