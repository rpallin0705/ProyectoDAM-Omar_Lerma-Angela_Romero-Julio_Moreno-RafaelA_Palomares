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
     * Obtiene la lista de todos los usuarios almacenados en la base de datos.
     *
     * @return Lista de objetos UsuarioDTO que representan a los usuarios almacenados en la base de datos.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
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
     * Funcion que realiza la lógica del inicio de sesion de un usuario
     *
     * @param email del usuario que va a iniciar sesion
     * @param password contraseña del usuario que va a iniciar sesion
     * @return los datos del usuario que ha iniciado sesion, menos la contraseña
     * @throws SQLException Si el email no está en la base de datos o la contraseña es incorrecta
     * @todo excepciones propias para cuando se introduzca la contraseña incorrecta o no exista el usuario
     */
    @Override
    public UserDTO userLogin(String email, String password) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet =  preparedStatement.executeQuery();
        UserDTO usuarioLoged = null;
        if (resultSet.next() && resultSet.getString("contrasena").equals(password)) {
            email = resultSet.getString("email");
            System.out.println("hola" + email);
            String telefono = resultSet.getString("telefono");
            String nombreApellidos = resultSet.getString("nombre_apellidos");
            String direccion = resultSet.getString("direccion");
            usuarioLoged = new UserDTO(email, telefono, nombreApellidos, direccion);
        } else
            throw new SQLException("Contraseña o Email incorrectos");
        return usuarioLoged;

    }

    /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * @param newUser Objeto UsuarioDTO que representa al nuevo usuario a insertar.
     * @return true si el usuario fue insertado exitosamente, false en caso contrario.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
    @Override
    public boolean insertUser(UserDTO newUser) throws SQLException {
        String sql = "INSERT INTO usuarios (email, telefono, nombre_apellidos, contrasena, direccion) VALUES (?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, newUser.getEmail());
        preparedStatement.setString(2, newUser.getTelefono());
        preparedStatement.setString(3, newUser.getNombreApellidos());
        preparedStatement.setString(4, newUser.getContrasena());
        preparedStatement.setString(5, newUser.getDireccion());
        int usuarioInsertado = preparedStatement.executeUpdate();
        return usuarioInsertado != 0;
    }

    /**
     * Elimina un usuario de la base de datos utilizando su email como identificador.
     *
     * @param userEmailDelete Email del usuario que se va a eliminar.
     * @return true si el usuario fue eliminado exitosamente, false en caso contrario.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
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
     * Actualiza la información de un usuario en la base de datos.
     * @param updatedUser Objeto UsuarioDTO que contiene la información actualizada del usuario.
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
    @Override
    public boolean updateUser(UserDTO updatedUser) throws SQLException {
        String sql = "UPDATE usuarios SET telefono = ?, nombre_apellidos = ?, contrasena = ?, direccion = ? WHERE email = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, updatedUser.getTelefono());
        preparedStatement.setString(2, updatedUser.getNombreApellidos());
        preparedStatement.setString(3, updatedUser.getContrasena());
        preparedStatement.setString(4, updatedUser.getDireccion());
        preparedStatement.setString(5, updatedUser.getEmail());
        int result = preparedStatement.executeUpdate();
        return result != 0;
    }

}
