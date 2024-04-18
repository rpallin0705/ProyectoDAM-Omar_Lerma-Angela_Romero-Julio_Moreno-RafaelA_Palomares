package org.example.proyecto;

import org.example.proyecto.model.usuario.UsuarioDB;
import org.example.proyecto.model.usuario.UsuarioDTO;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            UsuarioDB usuarioDB = new UsuarioDB();
            //UsuarioDTO usuario = usuarioDB.loginUsuario("usuaro1@example.com", "contraseña1");
            //System.out.println(usuario.toString());

            /*UsuarioDTO usuarioDTO = new UsuarioDTO("hola", "asios", "adfadfad", "adgad", "dagdg");
            System.out.printf("usuario insertado -> %B",usuarioDB.insertUsuario(usuarioDTO));*/

            System.out.printf("usuario borrado -> %B", usuarioDB.deleteUsuariobyEmail("hola"));
        } catch (SQLException e) {
            System.err.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
