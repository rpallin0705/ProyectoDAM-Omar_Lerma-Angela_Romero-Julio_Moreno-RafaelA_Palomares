package org.example.proyecto.model.user;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<UserDTO> getUsers() throws SQLException;
    UserDTO userLogin(String email, String password) throws SQLException;
    boolean insertUser(UserDTO newUser) throws SQLException;
    boolean deleteUserByID(String userEmailDelete) throws SQLException;
    boolean updateUser(UserDTO updatedUser) throws SQLException;
}
