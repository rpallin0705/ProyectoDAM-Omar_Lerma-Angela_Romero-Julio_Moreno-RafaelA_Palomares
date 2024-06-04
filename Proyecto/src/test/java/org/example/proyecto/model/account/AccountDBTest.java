package org.example.proyecto.model.account;

import org.example.proyecto.model.hotel.HotelDB;
import org.example.proyecto.model.hotel.HotelDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountDBTest {
    AccountDB accountDB = new AccountDB();

    AccountDBTest() throws SQLException, IOException {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAccount() throws SQLException {
        List<AccountDTO> accountDTOList = accountDB.getAccount();
        assertNotNull(accountDTOList);

        if(!accountDTOList.isEmpty()){
            AccountDTO accountDTO = accountDTOList.get(0);
            assertNotNull(accountDTO.getEmail());
            assertNotNull(accountDTO.getContrasena());
            assertNotNull(accountDTO.getNombre_apellidos());
        }
    }

    @Test
    void updateAccount() throws SQLException {
        AccountDTO accountDTO = new AccountDTO(1,"ejemplo@gmail.com","1234","Pepe Perez");
        accountDB.updateAccount(accountDTO);

        AccountDTO updateAccountDTO1 = new AccountDTO(1,"ejemplo2@gmail.com","5678","Pepe Perez");

        boolean update = accountDB.updateAccount(updateAccountDTO1);
        assertFalse(update);
    }
}