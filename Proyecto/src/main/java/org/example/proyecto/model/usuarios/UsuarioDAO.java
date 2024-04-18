package org.example.proyecto.model.usuarios;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO {
    List<UsuarioDTO> getUsuarios() throws SQLException;
    UsuarioDTO loginUsuario(String email, String contrasenha) throws SQLException;
    boolean insertUsuario(UsuarioDTO usuarioNuevo);
    boolean deleteUsuariobyEmail(UsuarioDTO usuarioABorrar);
    boolean updateUsuario(UsuarioDTO usuarioAActualizado);
}
