package org.example.proyecto.model.user;

import org.example.proyecto.SetUpConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDB implements UserDAO {
    Connection connection;
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
        List<UserDTO> users = new ArrayList<>();
        String sql = "SELECT * FROM vista_usuarios ;";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        UserDTO userDTO = null;
        while (resultSet.next()){
            int id_cuenta = resultSet.getInt("id_cuenta");
            String email = resultSet.getString("email");
            String contrasena = resultSet.getString("contrasena");
            String nombreApellidos = resultSet.getString("nombre_apellidos");
            boolean admin = resultSet.getBoolean("admin");
            userDTO = new UserDTO(id_cuenta,email,contrasena,nombreApellidos,admin);
            users.add(userDTO);
        }
        return users;
    }

    /**
     * Function that works on the user login process.
     *
     * @param user user who try to log in
     * @return los datos del usuario que ha iniciado sesion, menos la contraseña
     * @throws SQLException Si el email no está en la base de datos o la contraseña es incorrecta
     * @todo excepciones propias para cuando se introduzca la contraseña incorrecta o no exista el usuario
     */
    @Override
    public UserDTO userLogin(UserDTO user) throws SQLException {
        String sql = "SELECT * FROM vista_usuarios WHERE email = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getEmail());
        ResultSet resultSet =  preparedStatement.executeQuery();
        UserDTO logedUser = null;
        if (resultSet.next() && resultSet.getString("contrasena").equals(user.getContrasena())) {
            int id_cuenta = resultSet.getInt("id_cuenta");
            String email = resultSet.getString("email");
            String contrasena = resultSet.getString("contrasena");
            String nombreApellidos = resultSet.getString("nombre_apellidos");
            boolean admin = resultSet.getBoolean("admin");
            logedUser = new UserDTO(id_cuenta,email,contrasena,nombreApellidos,admin);
        } else
            throw new SQLException("Contraseña o Email incorrectos");
        return logedUser;

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
        //cuentas
        String sql = "INSERT INTO cuentas (email, contrasena, nombre_apellidos) VALUES (?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, newUser.getEmail());
        preparedStatement.setString(2, newUser.getContrasena());
        preparedStatement.setString(3, newUser.getNombre_apellidos());
        int rowsAffected = preparedStatement.executeUpdate();
        //conseguir id_cuenta de la cuenta insertada
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        int idConseguido = generatedKeys.getInt(1);
        //usuario
        String sql2 = "INSERT INTO usuarios (id_cuenta, admin) VALUES (?, ?)";
        preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setInt(1,idConseguido);
        preparedStatement.setBoolean(2,newUser.isAdmin());
        rowsAffected += preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }

    /**
     * Delete a user from the database using its email address as identifer
     *
     * @param deletedUser user who is going to be deleted.
     * @return true si el usuario fue eliminado exitosamente, false en caso contrario.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     * @todo Cuando se actualice el script de la base de datos hacer la logica para que sea por id
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
     * Update the information of user saved in database
     * @param updatedUser UserDTO with the new information of the user to be updated
     * @return true if user is updated, false if not
     * @throws SQLException if an error occurs during the execution of the query.
     */
    @Override
    public boolean updateUser(UserDTO updatedUser) throws SQLException {
        //cuentas
        String sql = "UPDATE cuentas SET email = ?, contrasena = ?, nombre_apellidos = ? WHERE id_cuenta = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, updatedUser.getEmail());
        preparedStatement.setString(2, updatedUser.getContrasena());
        preparedStatement.setString(3, updatedUser.getNombre_apellidos());
        preparedStatement.setInt(4, updatedUser.getId_cuenta());
        int rowsAffected = preparedStatement.executeUpdate();
        //usuarios
        String sql2 = "UPDATE usuarios SET admin = ? WHERE id_cuenta = ?";
        preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setBoolean(1, updatedUser.isAdmin());
        preparedStatement.setInt(2, updatedUser.getId_cuenta());
        rowsAffected += preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }

}
