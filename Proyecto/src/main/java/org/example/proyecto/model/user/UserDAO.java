package org.example.proyecto.model.user;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<UserDTO> getUsers() throws SQLException;
    UserDTO userLogin(UserDTO user) throws SQLException;
    boolean insertUser(UserDTO newUser) throws SQLException;
    boolean deleteUser(UserDTO deletedUser) throws SQLException;
    boolean updateUser(UserDTO updatedUser) throws SQLException;
}
