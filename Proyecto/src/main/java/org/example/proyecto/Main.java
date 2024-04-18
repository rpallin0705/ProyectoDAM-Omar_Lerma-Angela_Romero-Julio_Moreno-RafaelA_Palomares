package org.example.proyecto;

import org.example.proyecto.model.usuarios.UsuarioDB;
import org.example.proyecto.model.usuarios.UsuarioDTO;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            UsuarioDB usuarioDB = new UsuarioDB();
            UsuarioDTO usuario = usuarioDB.loginUsuario("usuaro1@example.com", "contraseña1");
            System.out.println(usuario.toString());
        } catch (SQLException e) {
            System.err.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
