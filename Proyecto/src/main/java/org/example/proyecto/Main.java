package org.example.proyecto;

import org.example.proyecto.model.user.UserDB;
import org.example.proyecto.model.user.UserDTO;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            UserDB usuarioDB = new UserDB();
            /*UsuarioDTO usuario = usuarioDB.loginUsuario("usuario1@example.com", "contraseña1");
            System.out.println(usuario.toString());*/
            //System.out.println(usuario.toString());

            UserDTO userDTO = new UserDTO("hola", "asios", "adfadfad", "adgad", "dagdg");
            System.out.printf("usuario insertado -> %B%n",usuarioDB.insertUser(userDTO));
            System.out.printf("usuario actualizado -> %B%n", usuarioDB.updateUser(userDTO));

            //System.out.printf("usuario borrado -> %B%n", usuarioDB.deleteUserByEmail("hola"));
        } catch (SQLException e) {
            System.err.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
