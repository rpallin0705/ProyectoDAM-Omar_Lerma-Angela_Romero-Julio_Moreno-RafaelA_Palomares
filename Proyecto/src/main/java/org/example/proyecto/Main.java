package org.example.proyecto;

import org.example.proyecto.model.usuario.UsuarioDB;
import org.example.proyecto.model.usuario.UsuarioDTO;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            UsuarioDB usuarioDB = new UsuarioDB();
            /*UsuarioDTO usuario = usuarioDB.loginUsuario("usuario1@example.com", "contraseña1");
            System.out.println(usuario.toString());*/
            //System.out.println(usuario.toString());

            UsuarioDTO usuarioDTO = new UsuarioDTO("hola", "asios", "adfadfad", "adgad", "dagdg");
            System.out.printf("usuario insertado -> %B%n",usuarioDB.insertUsuario(usuarioDTO));
            System.out.printf("usuario actualizado -> %B%n", usuarioDB.updateUsuario(usuarioDTO));

            System.out.printf("usuario borrado -> %B%n", usuarioDB.deleteUsuariobyEmail("hola"));
        } catch (SQLException e) {
            System.err.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
