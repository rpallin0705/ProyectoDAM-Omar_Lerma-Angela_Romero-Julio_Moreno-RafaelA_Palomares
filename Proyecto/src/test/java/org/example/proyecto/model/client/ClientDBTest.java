package org.example.proyecto.model.client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientDBTest {
    ClientDB clientDB = new ClientDB();
    ClientDTO clientDTO = new ClientDTO(1,"correo@gmail.com","123qw","nombre","C/asa");
    ClientDTO clientDTO1 = new ClientDTO(2,"ejemplo@gmail.com","1234a","ejemplo","C/calle");
    ClientDTO clientDTO2 = new ClientDTO(3,"ejemplo@gmail.com","1234a","ejemplo","C/calle");

    ClientDBTest() throws SQLException, IOException {
    }

    @BeforeEach
    void setUp(){
    }

    @AfterEach
    void tearDown() throws SQLException {
        clientDB.deleteClient(clientDTO);
        clientDB.deleteClient(clientDTO1);
        clientDB.deleteClient(clientDTO2);
    }

    @Test
    void getClients() throws SQLException {
        List<ClientDTO> clientDTOList = clientDB.getClients();
        assertNotNull(clientDTOList);
        assertFalse(clientDTOList.isEmpty());
        for (ClientDTO clientDTO1 : clientDTOList){
            System.out.println(clientDTO1);
        }

        ClientDTO clientDTO1 = clientDTOList.get(0);
        assertEquals("correo@gmail.com", clientDTO1.getEmail());
    }

    @Test
    void insertClient() throws SQLException {
        assertTrue(clientDB.insertClient(clientDTO));
        assertTrue(clientDB.insertClient(clientDTO1));

        clientDB.deleteClient(clientDTO1);
    }

    @Test
    void deleteClient() throws SQLException {
        assertFalse(clientDB.deleteClient(clientDTO));
        assertFalse(clientDB.deleteClient(clientDTO2));

        List<ClientDTO> clientDTOList = clientDB.getClients();
        assertFalse(clientDTOList.contains(clientDTO));
    }

    @Test
    void updateClient() throws SQLException {
        ClientDTO clientDTO1 = new ClientDTO(3,"ejemplo1@gmail.com","1234a2","ejemplo2","C/calle");
        assertFalse(clientDB.updateClient(clientDTO1));
        assertEquals("ejemplo1@gmail.com", clientDTO1.getEmail());
    }
}