package org.example.proyecto.model.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDBTest {
    UserDB userDB = new UserDB();
    UserDTO userDTO = new UserDTO(1,"hugo@gmail.com", "Hugo Perez",true,"1234");
    UserDTO userDTO1 = new UserDTO(2,"javi@gmail.com","Javier Paredes",false,"1234b");

    UserDBTest() throws SQLException, IOException {
    }

    @BeforeEach
    void setUp() throws SQLException {
        userDB.insertUser(userDTO);
        userDB.insertUser(userDTO1);
    }

    @AfterEach
    void tearDown() throws SQLException {
        String sql = "DELETE FROM cuentas";
        PreparedStatement preparedStatement = userDB.connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }

    @Test
    void getUsers() throws SQLException {
        List<UserDTO> userDTOList = userDB.getUsers();
        assertNotNull(userDTOList);
        assertFalse(userDTOList.isEmpty());
        for (UserDTO users : userDTOList){
            System.out.println(users);
        }
        UserDTO user1 = userDTOList.get(0);
        assertEquals("hugo@gmail.com", user1.getEmail());
    }

    @Test
    void userLogin() throws SQLException {
        UserDTO userLogin = userDB.userLogin(userDTO);
        assertNotNull(userLogin);
        assertEquals(userDTO.getEmail(), userLogin.getEmail());
        assertEquals(userDTO.getNombre_apellidos(), userLogin.getNombre_apellidos());
    }

    @Test
    void insertUser() throws SQLException {
        UserDTO user3 = new UserDTO(3,"lucia@gmail.com","1234a", "Lucia Ruiz", true);
        assertTrue(userDB.insertUser(user3));
        assertTrue(userDB.insertUser(userDTO));
        assertTrue(userDB.insertUser(userDTO1));
    }

    @Test
    void deleteUser() throws SQLException {
        assertFalse(userDB.deleteUser(userDTO1));
        assertFalse(userDB.deleteUser(userDTO));
    }

    @Test
    void updateUser() throws SQLException {
        UserDTO updatedUser = new UserDTO(userDTO.getId_cuenta(), "hugo32@gmail.com", "12ut12", "Hugo Sanchez", false);
        assertFalse(userDB.updateUser(updatedUser));
    }
}