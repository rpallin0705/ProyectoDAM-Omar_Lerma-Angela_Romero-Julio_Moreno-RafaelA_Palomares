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
        if (resultSet.next() && resultSet.getString("contrasnha").equals(contrasena)) {
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

    @Override
    public boolean insertUsuario(UsuarioDTO usuarioNuevo) throws SQLException {
        String sql = "INSERT INTO usuarios VALUES ('" + usuarioNuevo.getEmail() + "', '" + usuarioNuevo.getEmail() + "', '"
                + usuarioNuevo.getNombreApellidos() + "', '" + usuarioNuevo.getContrasenha() + "', '" + usuarioNuevo.getDireccion() + "');";
        statement = connection.createStatement();
        int usuarioInsertado = statement.executeUpdate(sql);
        return usuarioInsertado != 0;
    }

    @Override
    public boolean deleteUsuariobyEmail(String emailUsuarioABorrar) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE email = '" + emailUsuarioABorrar + "';";
        statement = connection.createStatement();
        int result = statement.executeUpdate(sql);
        return result != 0;
    }

    @Override
    public boolean updateUsuario(UsuarioDTO usuarioAActualizado) {
        return false;
    }
}
