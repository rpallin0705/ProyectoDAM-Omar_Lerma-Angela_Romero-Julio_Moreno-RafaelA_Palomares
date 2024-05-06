package org.example.proyecto.model.client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDAO {
    List<ClientDTO> getClients() throws SQLException;
    boolean insertClient(ClientDTO newClient) throws SQLException;
    boolean deleteClient(ClientDTO deletedClient) throws SQLException;
    boolean updateClient(ClientDTO updatedClient) throws SQLException;
}

