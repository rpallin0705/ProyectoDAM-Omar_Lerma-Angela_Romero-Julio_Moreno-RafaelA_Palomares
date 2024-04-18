package org.example.proyecto.model.usuario;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO {
    List<UsuarioDTO> getUsuarios() throws SQLException;
    UsuarioDTO loginUsuario(String email, String contrasenha) throws SQLException;
    boolean insertUsuario(UsuarioDTO usuarioNuevo) throws SQLException;
    boolean deleteUsuariobyEmail(String emailUsuarioABorrar) throws SQLException;
    boolean updateUsuario(UsuarioDTO usuarioAActualizado);
}
