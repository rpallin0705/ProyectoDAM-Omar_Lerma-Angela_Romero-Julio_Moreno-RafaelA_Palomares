package org.example.proyecto.model.usuario;

import org.example.proyecto.SetUpConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDB implements UsuarioDAO{
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public UsuarioDB() throws SQLException, IOException {
        connection = SetUpConnection.getInstance().getConnection();
    }

    /**
     * Obtiene la lista de todos los usuarios almacenados en la base de datos.
     * @return Lista de objetos UsuarioDTO que representan a los usuarios almacenados en la base de datos.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
    @Override
    public List<UsuarioDTO> getUsuarios() throws SQLException {
        List<UsuarioDTO> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios ;";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        UsuarioDTO usuariosDTO = null;
        while (resultSet.next()) {
            String email = resultSet.getString("email");
            String telefono = resultSet.getString("telefono");
            String nombreApellidos = resultSet.getString("nombre_apellidos");
            String direccion = resultSet.getString("direccion");
            usuariosDTO = new UsuarioDTO(email, telefono, nombreApellidos, direccion);
            usuarios.add(usuariosDTO);
        }
        return usuarios;
    }

    /**
     * Funcion que realiza la lógica del inicio de sesion de un usuario
     * @param email del usuario que va a iniciar sesion
     * @param contrasena contraseña del usuario que va a iniciar sesion
     * @return los datos del usuario que ha iniciado sesion, menos la contraseña
     * @throws SQLException Si el email no está en la base de datos o la contraseña es incorrecta
     * @todo excepciones propias para cuando se introduzca la contraseña incorrecta o no exista el usuario
     */
    @Override
    public UsuarioDTO loginUsuario(String email, String contrasena) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet =  preparedStatement.executeQuery();
        UsuarioDTO usuarioLoged = null;
        if (resultSet.next() && resultSet.getString("contrasena").equals(contrasena)) {
            email = resultSet.getString("email");
            System.out.println("hola" + email);
            String telefono = resultSet.getString("telefono");
            String nombreApellidos = resultSet.getString("nombre_apellidos");
            String direccion = resultSet.getString("direccion");
            usuarioLoged = new UsuarioDTO(email, telefono, nombreApellidos, direccion);
        } else
            throw new SQLException("Contraseña o Email incorrectos");
        return usuarioLoged;

    }

    /**
     * Inserta un nuevo usuario en la base de datos.
     * @param usuarioNuevo Objeto UsuarioDTO que representa al nuevo usuario a insertar.
     * @return true si el usuario fue insertado exitosamente, false en caso contrario.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
    @Override
    public boolean insertUsuario(UsuarioDTO usuarioNuevo) throws SQLException {
        String sql = "INSERT INTO usuarios (email, telefono, nombre_apellidos, contrasena, direccion) VALUES (?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, usuarioNuevo.getEmail());
        preparedStatement.setString(2, usuarioNuevo.getTelefono());
        preparedStatement.setString(3, usuarioNuevo.getNombreApellidos());
        preparedStatement.setString(4, usuarioNuevo.getContrasena());
        preparedStatement.setString(5, usuarioNuevo.getDireccion());
        int usuarioInsertado = preparedStatement.executeUpdate();
        return usuarioInsertado != 0;
    }

    /**
     * Elimina un usuario de la base de datos utilizando su email como identificador.
     * @param emailUsuarioABorrar Email del usuario que se va a eliminar.
     * @return true si el usuario fue eliminado exitosamente, false en caso contrario.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
    @Override
    public boolean deleteUsuariobyEmail(String emailUsuarioABorrar) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE email = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, emailUsuarioABorrar);
        int result = preparedStatement.executeUpdate();
        return result != 0;
    }


    /**
     * Actualiza la información de un usuario en la base de datos.
     * @param usuarioAActualizar Objeto UsuarioDTO que contiene la información actualizada del usuario.
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
    @Override
    public boolean updateUsuario(UsuarioDTO usuarioAActualizar) throws SQLException {
        String sql = "UPDATE usuarios SET telefono = ?, nombre_apellidos = ?, contrasena = ?, direccion = ? WHERE email = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, usuarioAActualizar.getTelefono());
        preparedStatement.setString(2, usuarioAActualizar.getNombreApellidos());
        preparedStatement.setString(3, usuarioAActualizar.getContrasena());
        preparedStatement.setString(4, usuarioAActualizar.getDireccion());
        preparedStatement.setString(5, usuarioAActualizar.getEmail());
        int result = preparedStatement.executeUpdate();
        return result != 0;
    }

}
