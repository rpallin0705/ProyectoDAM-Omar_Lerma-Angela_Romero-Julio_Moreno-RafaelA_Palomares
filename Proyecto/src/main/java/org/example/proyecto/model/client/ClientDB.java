package org.example.proyecto.model.client;

import org.example.proyecto.SetUpConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDB implements ClientDAO {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public ClientDB() throws SQLException, IOException {
        connection = SetUpConnection.getInstance().getConnection();
    }

    /**
     * Get a list of al clients saved in database.
     *
     * @return List<ClientDTO> of saved clients.
     * @throws SQLException Error ocurred during execution of SQL query.
     */
    @Override
    public List<ClientDTO> getClients() throws SQLException {
        List<ClientDTO> clients = new ArrayList<>();
        String sql = "SELECT * FROM clientes ;";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ClientDTO clientesDTO = null;
        while (resultSet.next()) {
            String email = resultSet.getString("email");
            String telefono = resultSet.getString("telefono");
            String nombreApellidos = resultSet.getString("nombre_apellidos");
            String direccion = resultSet.getString("direccion");
            clientesDTO = new ClientDTO(email, telefono, nombreApellidos, direccion);
            clients.add(clientesDTO);
        }
        return clients;
    }

    /**
     * Insert a new client in database.
     *
     * @param newClient ClientDTO object to be saved in database.
     * @return true if inserted successfully, false otherwise.
     * @throws SQLException error ocurred during execution of SQL query.
     */
    @Override
    public boolean insertClient(ClientDTO newClient) throws SQLException {
        String sql = "INSERT INTO clientes (email, telefono, nombre_apellidos, direccion) VALUES (?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, newClient.getEmail());
        preparedStatement.setString(2, newClient.getPhoneNumber());
        preparedStatement.setString(3, newClient.getFullName());
        preparedStatement.setString(4, newClient.getClientAddress());
        int clienteInsertado = preparedStatement.executeUpdate();
        return clienteInsertado != 0;
    }

    /**
     * Elimina un cliente de la base de datos utilizando su email como identificador. Delete a client from database using its email as identifier.
     *
     * @param ClientEmailDelete Client email wich identifies the client to be deleted.
     * @return true if the client was successfully deleted, false otherwise.
     * @throws SQLException if an error occurs while executing the SQL query.
     * @todo When database updated make it delete by ID
     */
    @Override
    public boolean deleteClientByID(String ClientEmailDelete) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, ClientEmailDelete);
        int result = preparedStatement.executeUpdate();
        return result != 0;
    }


    /**
     * Update client information in database.
     * @param updatedClient ClientDTO object with the new information.
     * @return true if the client information was successfully updated, false otherwise.
     * @throws SQLException if an error occurs while executing the SQL query.
     */
    @Override
    public boolean updateClient(ClientDTO updatedClient) throws SQLException {
        String sql = "UPDATE clientes SET telefono = ?, nombre_apellidos = ?, direccion = ? WHERE email = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, updatedClient.getPhoneNumber());
        preparedStatement.setString(2, updatedClient.getFullName());
        preparedStatement.setString(3, updatedClient.getClientAddress());
        preparedStatement.setString(4, updatedClient.getEmail());
        int result = preparedStatement.executeUpdate();
        return result != 0;
    }

}
