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
        String sql = "SELECT * FROM vista_clientes;";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ClientDTO clientDTO = null;
        while (resultSet.next()) {
            int id_cuenta = resultSet.getInt("id_cuenta");
            String email = resultSet.getString("email");
            String contrasena = resultSet.getString("contrasena");
            String nombre_apellidos = resultSet.getString("nombre_apellidos");
            String direccion = resultSet.getString("direccion");
            clientDTO = new ClientDTO(id_cuenta,email,contrasena,nombre_apellidos,direccion);
            clients.add(clientDTO);
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
        //cuentas
        String sql = "INSERT INTO cuentas (email, contrasena, nombre_apellidos) VALUES (?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, newClient.getEmail());
        preparedStatement.setString(2, newClient.getContrasena());
        preparedStatement.setString(3, newClient.getNombre_apellidos());
        int rowsAffected = preparedStatement.executeUpdate();
        //conseguir id_cuenta de la cuenta insertada
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        int idConseguido = generatedKeys.getInt(1);
        //clientes
        String sql2 = "INSERT INTO clienes (id_cuenta, direccion) VALUES (?, ?)";
        preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setInt(idConseguido, newClient.getId_cuenta());
        rowsAffected += preparedStatement.executeUpdate();
        //vista_clientes
        String sql3 = "INSERT INTO vista_clientes (id_cuenta, email, contrasena, nombre_apellidos, direccion) VALUES (?, ?, ?, ?, ?);";
        preparedStatement = connection.prepareStatement(sql3);
        preparedStatement.setInt(1, idConseguido);
        preparedStatement.setString(2, newClient.getEmail());
        preparedStatement.setString(3, newClient.getContrasena());
        preparedStatement.setString(4, newClient.getNombre_apellidos());
        preparedStatement.setString(5, newClient.getDireccion());
        rowsAffected += preparedStatement.executeUpdate();
        connection.commit();
        connection.setAutoCommit(true);
        return rowsAffected != 0;
    }

    /**
     * Elimina un cliente de la base de datos utilizando su email como identificador. Delete a client from database using its email as identifier.
     *
     * @param deletedClient client who is going to be deleted.
     * @return true si el cliente fue eliminado exitosamente, false en caso contrario.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     * @todo Cuando se actualice el script de la base de datos hacer la logica para que sea por id
     */
    @Override
    public boolean deleteClient(ClientDTO deletedClient) throws SQLException {
        //clientes && cuentas
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, deletedClient.getId_cuenta());
        int rowsAffected = preparedStatement.executeUpdate();
        //vista_clientes
        String sql2 = "DELETE FROM vista_clientes WHERE id_cliente = ?";
        preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setInt(1, deletedClient.getId_cuenta());
        rowsAffected += preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }


    /**
     * Update client information in database.
     * @param updatedClient ClientDTO object with the new information.
     * @return true if the client information was successfully updated, false otherwise.
     * @throws SQLException if an error occurs while executing the SQL query.
     */
    @Override
    public boolean updateClient(ClientDTO updatedClient) throws SQLException {
        //cuentas
        String sql = "UPDATE cuentas SET email = ?, contrasena = ?, nombre_apellidos = ? WHERE id_cuenta = ?";
        preparedStatement.setString(1,updatedClient.getEmail());
        preparedStatement.setString(2,updatedClient.getContrasena());
        preparedStatement.setString(3,updatedClient.getNombre_apellidos());
        preparedStatement.setInt(4,updatedClient.getId_cuenta());
        int rowsAffected = preparedStatement.executeUpdate();
        //clientes
        String sql2 = "UPDATE clientes SET direccion = ? WHERE id_cuenta = ?";
        preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setString(1, updatedClient.getDireccion());
        preparedStatement.setInt(2,updatedClient.getId_cuenta());
        rowsAffected = preparedStatement.executeUpdate();
        //vista_clientes
        String sql3 = "UPDATE vista_clientes SET email = ?, contrasena = ?, nombre_apellidos = ?, direccion = ? WHERE id_cuenta = ?;";
        preparedStatement = connection.prepareStatement(sql3);
        preparedStatement.setString(1, updatedClient.getEmail());
        preparedStatement.setString(2, updatedClient.getContrasena());
        preparedStatement.setString(3, updatedClient.getNombre_apellidos());
        preparedStatement.setString(4, updatedClient.getDireccion());
        preparedStatement.setInt(5, updatedClient.getId_cuenta());
        rowsAffected += preparedStatement.executeUpdate();
        connection.commit();
        connection.setAutoCommit(true);
        return rowsAffected != 0;
    }

}
