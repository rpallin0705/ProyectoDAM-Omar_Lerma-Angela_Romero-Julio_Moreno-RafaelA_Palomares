package org.example.proyecto.model.client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDAO {
    List<ClientDTO> getClients() throws SQLException;
    boolean insertClient(ClientDTO newUser) throws SQLException;
    boolean deleteClientByID(String userEmailDelete) throws SQLException;
    boolean updateClient(ClientDTO updatedUser) throws SQLException;
}

