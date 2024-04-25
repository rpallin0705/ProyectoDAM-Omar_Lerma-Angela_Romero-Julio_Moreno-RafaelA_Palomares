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
     * Obtiene la lista de todos los clientes almacenados en la base de datos.
     *
     * @return Lista de objetos ClienteDTO que representan a los clientes almacenados en la base de datos.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
    @Override
    public List<ClientDTO> getClients() throws SQLException {
        List<ClientDTO> clientes = new ArrayList<>();
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
            clientes.add(clientesDTO);
        }
        return clientes;
    }

    /**
     * Inserta un nuevo cliente en la base de datos.
     *
     * @param newClient Objeto ClienteDTO que representa al nuevo cliente a insertar.
     * @return true si el cliente fue insertado exitosamente, false en caso contrario.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
    @Override
    public boolean insertClient(ClientDTO newClient) throws SQLException {
        String sql = "INSERT INTO clientes (email, telefono, nombre_apellidos, direccion) VALUES (?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, newClient.getEmail());
        preparedStatement.setString(2, newClient.getTelefono());
        preparedStatement.setString(3, newClient.getNombreApellidos());
        preparedStatement.setString(4, newClient.getDireccion());
        int clienteInsertado = preparedStatement.executeUpdate();
        return clienteInsertado != 0;
    }

    /**
     * Elimina un cliente de la base de datos utilizando su email como identificador.
     *
     * @param ClientEmailDelete Email del cliente que se va a eliminar.
     * @return true si el cliente fue eliminado exitosamente, false en caso contrario.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     * @todo Cuando se actualice el script de la base de datos hacer la logica para que sea por id
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
     * Actualiza la información de un cliente en la base de datos.
     * @param updatedClient Objeto ClienteDTO que contiene la información actualizada del cliente.
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
    @Override
    public boolean updateClient(ClientDTO updatedClient) throws SQLException {
        String sql = "UPDATE clientes SET telefono = ?, nombre_apellidos = ?, direccion = ? WHERE email = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, updatedClient.getTelefono());
        preparedStatement.setString(2, updatedClient.getNombreApellidos());
        preparedStatement.setString(3, updatedClient.getDireccion());
        preparedStatement.setString(4, updatedClient.getEmail());
        int result = preparedStatement.executeUpdate();
        return result != 0;
    }

}
