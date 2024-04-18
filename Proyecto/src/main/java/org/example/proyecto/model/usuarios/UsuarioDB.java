package org.example.proyecto.model.usuarios;

import org.example.proyecto.SetUpConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
     * @param contrasenha contraseña del usuario que va a iniciar sesion
     * @return los datos del usuario que ha iniciado sesion, menos la contraseña
     * @throws SQLException Si el email no está en la base de datos o la contraseña es incorrecta
     * @todo excepciones propias para cuando se introduzca la contraseña incorrecta o no exista el usuario
     */
    @Override
    public UsuarioDTO loginUsuario(String email, String contrasenha) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet =  preparedStatement.executeQuery();
        UsuarioDTO usuarioLoged = null;
        if (resultSet.next() && resultSet.getString("contrasnha").equals(contrasenha)) {
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
    public boolean insertUsuario(UsuarioDTO usuarioNuevo) {
        return false;
    }

    @Override
    public boolean deleteUsuariobyEmail(UsuarioDTO usuarioABorrar) {
        return false;
    }

    @Override
    public boolean updateUsuario(UsuarioDTO usuarioAActualizado) {
        return false;
    }

   /*

    *//**
     * Crea una lista con todos los usuarios registrados de la base de datos
     * @return objeto List<UsuariosDTO> con todos los usuarios de la base de datos
     * @throws SQLException Si la consulta diera algun error lanzaría una excepcion
     *//*
    public List<UsuariosDTO> getUsuariosDTOs () throws SQLException {
        List<UsuariosDTO> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios ;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        UsuariosDTO usuariosDTO = null;
        while (resultSet.next()) {
            String email = resultSet.getString("email");
            String telefono = resultSet.getString("telefono");
            String nombreApellidos = resultSet.getString("nombre_apellidos");
            String direccion = resultSet.getString("direccion");
            usuariosDTO = new UsuariosDTO(email, telefono, nombreApellidos, direccion);
            usuarios.add(usuariosDTO);
        }
        return usuarios;
    }

    *//**
     * Funcion para realizar el login de un usuario
     * @param emailLog
     * @param passwd
     * @return
     * @throws SQLException
     * @throws IOException
     *//*
    public UsuariosDTO loginUsuario(String emailLog, String passwd) throws SQLException, IOException {
        String sql = "SELECT * FROM usuarios WHERE email = ?;";// And contrasena = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, emailLog);
        //statement.setString(2, passwd);
        ResultSet resultSet =  statement.executeQuery();
        UsuariosDTO usuariosLoged = null;
        while (resultSet.next()) {
            String email = resultSet.getString("email");
            System.out.println("hola" + email);
            String telefono = resultSet.getString("telefono");
            String nombreApellidos = resultSet.getString("nombre_apellidos");
            String direccion = resultSet.getString("direccion");
            usuariosLoged = new UsuariosDTO(email, telefono, nombreApellidos, direccion);

        }
        return usuariosLoged;
    }*/
}
