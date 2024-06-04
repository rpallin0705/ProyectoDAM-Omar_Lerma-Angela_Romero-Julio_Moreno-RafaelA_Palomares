package org.example.proyecto.model.client;

import java.sql.SQLException;
import java.util.List;

/**
 * Data Access Object (DAO) interface for client operations.
 * This interface defines methods for performing CRUD operations on clients in the database.
 *
 * @version 1.0
 * @since 2024-05-28
 *
 * @Author Omar
 */
public interface ClientDAO {

    /**
     * Retrieves a list of all clients from the database.
     *
     * @return a List of ClientDTO objects representing all clients in the database.
     * @throws SQLException if a database access error occurs.
     */
    List<ClientDTO> getClients() throws SQLException;

    /**
     * Inserts a new client into the database.
     *
     * @param newClient the ClientDTO object representing the new client to be inserted.
     * @return true if the client was successfully inserted, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    boolean insertClient(ClientDTO newClient) throws SQLException;

    /**
     * Deletes a client from the database.
     *
     * @param deletedClient the ClientDTO object representing the client to be deleted.
     * @return true if the client was successfully deleted, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    boolean deleteClient(ClientDTO deletedClient) throws SQLException;

    /**
     * Updates the information of an existing client in the database.
     *
     * @param updatedClient the ClientDTO object representing the client with updated information.
     * @return true if the client was successfully updated, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    boolean updateClient(ClientDTO updatedClient) throws SQLException;
}
