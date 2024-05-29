package org.example.proyecto.model.client;

import org.example.proyecto.SetUpConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Database Access Object (DAO) implementation for client operations.
 * This class provides methods to interact with the database for CRUD operations on clients.
 *
 * @version 1.0
 * @since 2024-05-28
 *
 * @Author Omar
 */
public class ClientDB implements ClientDAO {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    /**
     * Constructs a {@code ClientDB} object and establishes a connection to the database.
     *
     * @throws SQLException if a database access error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public ClientDB() throws SQLException, IOException {
        connection = SetUpConnection.getInstance().getConnection();
    }

    /**
     * Retrieves a list of all clients from the database.
     *
     * @return a List of ClientDTO objects representing all clients in the database.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public List<ClientDTO> getClients() throws SQLException {
        List<ClientDTO> clients = new ArrayList<>();
        String sql = "SELECT * FROM vista_clientes;";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ClientDTO clientDTO = null;
        while (resultSet.next()) {
            int id_cuenta = resultSet.getInt("id_cuenta");
            String email = resultSet.getString("email");
            String nombre_apellidos = resultSet.getString("nombre_apellidos");
            String direccion = resultSet.getString("direccion");
            clientDTO = new ClientDTO(id_cuenta, email, nombre_apellidos, direccion);
            clients.add(clientDTO);
        }
        return clients;
    }

    /**
     * Inserts a new client into the database.
     *
     * @param newClient the ClientDTO object representing the new client to be inserted.
     * @return true if the client was successfully inserted, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public boolean insertClient(ClientDTO newClient) throws SQLException {
        // cuentas
        String sql = "INSERT INTO cuentas (email, nombre_apellidos) VALUES (?, ?)";
        preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, newClient.getEmail());
        preparedStatement.setString(2, newClient.getNombre_apellidos());
        int rowsAffected = preparedStatement.executeUpdate();
        // get the id_cuenta of the inserted account
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        generatedKeys.next();
        int idConseguido = generatedKeys.getInt(1);
        // clientes
        String sql2 = "INSERT INTO clientes (id_cuenta, direccion) VALUES (?, ?)";
        preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setInt(1, idConseguido);
        preparedStatement.setString(2, newClient.getDireccion());
        rowsAffected += preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }

    /**
     * Deletes a client from the database using its ID as identifier.
     *
     * @param deletedClient the ClientDTO object representing the client to be deleted.
     * @return true if the client was successfully deleted, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public boolean deleteClient(ClientDTO deletedClient) throws SQLException {
        // clientes && cuentas
        String sql = "DELETE FROM clientes WHERE id_cuenta = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, deletedClient.getId_cuenta());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }

    /**
     * Updates the information of an existing client in the database.
     *
     * @param updatedClient the ClientDTO object representing the client with updated information.
     * @return true if the client information was successfully updated, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public boolean updateClient(ClientDTO updatedClient) throws SQLException {
        // cuentas
        String sql = "UPDATE cuentas SET email = ?, nombre_apellidos = ? WHERE id_cuenta = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, updatedClient.getEmail());
        preparedStatement.setString(2, updatedClient.getNombre_apellidos());
        preparedStatement.setInt(3, updatedClient.getId_cuenta());
        int rowsAffected = preparedStatement.executeUpdate();
        // clientes
        String sql2 = "UPDATE clientes SET direccion = ? WHERE id_cuenta = ?";
        preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setString(1, updatedClient.getDireccion());
        preparedStatement.setInt(2, updatedClient.getId_cuenta());
        rowsAffected += preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }
}
