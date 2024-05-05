package org.example.proyecto.model.user;

import org.example.proyecto.SetUpConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDB implements UserDAO {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public UserDB() throws SQLException, IOException {
        connection = SetUpConnection.getInstance().getConnection();
    }

    /**
     * Get list of all users/workers from the database.
     *
     * @return List<UserDTO> Of all users/workers in the database.
     * @throws SQLException if an error occurs during the execution of the query.
     */
    @Override
    public List<UserDTO> getUsers() throws SQLException {
        List<UserDTO> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios ;";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        UserDTO usuariosDTO = null;
        while (resultSet.next()) {
            String email = resultSet.getString("email");
            String telefono = resultSet.getString("telefono");
            String nombreApellidos = resultSet.getString("nombre_apellidos");
            String direccion = resultSet.getString("direccion");
            usuariosDTO = new UserDTO(email, telefono, nombreApellidos, direccion);
            usuarios.add(usuariosDTO);
        }
        return usuarios;
    }

    /**
     * Function that works on the user login process.
     *
     * @param userEmail from the user loging in
     * @param userPassword from the user loging in
     * @return data from the user loged in, excluding the password
     * @throws SQLException if email is not registeres or password is incorrect
     * @todo own exceptions when password is incorrect or email not registered
     */
    @Override
    public UserDTO userLogin(String userEmail, String userPassword) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userEmail);
        ResultSet resultSet =  preparedStatement.executeQuery();
        UserDTO usuarioLoged = null;
        if (resultSet.next() && resultSet.getString("contrasena").equals(userPassword)) {
            userEmail = resultSet.getString("email");
            String telefono = resultSet.getString("telefono");
            String nombreApellidos = resultSet.getString("nombre_apellidos");
            String direccion = resultSet.getString("direccion");
            usuarioLoged = new UserDTO(userEmail, telefono, nombreApellidos, direccion);
        } else
            throw new SQLException("Contraseña o Email incorrectos");
        return usuarioLoged;

    }

    /**
     * Insert a new user in the database
     *
     * @param newUser UserDTO whith new user data
     * @return true if user is inserted, false if not
     * @throws SQLException if an error occurs during the execution of the query.
     */
    @Override
    public boolean insertUser(UserDTO newUser) throws SQLException {
        String sql = "INSERT INTO usuarios (email, telefono, nombre_apellidos, contrasena, direccion) VALUES (?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, newUser.getUserEmail());
        preparedStatement.setString(2, newUser.getUserPhoneNumber());
        preparedStatement.setString(3, newUser.getUserFullName());
        preparedStatement.setString(4, newUser.getUserPassword());
        preparedStatement.setString(5, newUser.getDireccion());
        int usuarioInsertado = preparedStatement.executeUpdate();
        return usuarioInsertado != 0;
    }

    /**
     * Delete a user from the database using its email address as identifer
     *
     * @param userEmailDelete Email from user to be deleted
     * @return true if user is deleted, false if not
     * @throws SQLException if an error occurs during the execution of the query.
     * @todo Cuando se actualice el script de la base de datos hacer la logica para que sea por id
     */
    @Override
    public boolean deleteUserByID(String userEmailDelete) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userEmailDelete);
        int result = preparedStatement.executeUpdate();
        return result != 0;
    }


    /**
     * Update the information of user saved in database
     * @param updatedUser UserDTO with the new information of the user to be updated
     * @return true if user is updated, false if not
     * @throws SQLException if an error occurs during the execution of the query.
     */
    @Override
    public boolean updateUser(UserDTO updatedUser) throws SQLException {
        String sql = "UPDATE usuarios SET telefono = ?, nombre_apellidos = ?, contrasena = ?, direccion = ? WHERE email = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, updatedUser.getUserPhoneNumber());
        preparedStatement.setString(2, updatedUser.getUserFullName());
        preparedStatement.setString(3, updatedUser.getUserPassword());
        preparedStatement.setString(4, updatedUser.getDireccion());
        preparedStatement.setString(5, updatedUser.getUserEmail());
        int result = preparedStatement.executeUpdate();
        return result != 0;
    }

}
