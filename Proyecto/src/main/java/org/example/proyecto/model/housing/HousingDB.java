package org.example.proyecto.model.housing;

import org.example.proyecto.SetUpConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HousingDB implements HousingDAO
{
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public HousingDB() throws SQLException, IOException {
        connection = SetUpConnection.getInstance().getConnection();
    }

    /**
<<<<<<< HEAD
     * Get a list of housings from the database.
     * @return List<HousingDTO> stored in the database.
     * @throws SQLException if an error occurs duringq the execution of the query.
=======
     * HousingDB
     * method that connects with the DataBase and gets a list of housings
     * @return list of housings
     * @throws SQLException
>>>>>>> a3722196b5bd945b50e0c8b9c391e23138175651
     */
    @Override
    public List<HousingDTO> getHousings() throws SQLException {
        List<HousingDTO> housings = new ArrayList<>();
        String sql = "SELECT * FROM alojamientos;";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        HousingDTO housingDTO = null;
        while (resultSet.next()){
            int id_alojamiento = resultSet.getInt("id_alojamiento");
            String nombre = resultSet.getString("nombre");
            String calle = resultSet.getString("calle");
            housingDTO = new HousingDTO(id_alojamiento,nombre,calle);
            housings.add(housingDTO);
        }
        return housings;
    }
<<<<<<< HEAD

    /**
     * Insert a new housing in the database.
     * @param newHousing the housing information to insert.
     * @return true if the housing was inserted successfully, false otherwise.
     * @throws SQLException if an error occurs duringq the execution of the query.
     */
    @Override
    public boolean insertHousing(HousingDTO newHousing) throws SQLException {
        String sql = "INSERT INTO alojamientos (cod_alojamiento, nom_alojamiento, direccion_alojamiento, num_huespedes, tipo_alojamiento) VALUES (?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, newHousing.getId_alojamiento());
        preparedStatement.setString(2, newHousing.getNombreAlojamiento());
        preparedStatement.setString(3, newHousing.getDireccionAlojamiento());
        preparedStatement.setInt(4, newHousing.getNumHuespedes());
        preparedStatement.setString(5, newHousing.getTipoAlojamiento().toString());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }

    /**
     * Update an existing housing in the database.
     * @param updatedHousing the new housing information to update.
     * @return true if the housing was updated successfully, false otherwise.
     * @throws SQLException if error occurs duringq the execution of the query.
=======
    /**
     * HousingDB
     * method that connects with the DataBase and updates a concrete Housing searching by ID
     * @return true if the rows affected are not 0
     * @throws SQLException
>>>>>>> a3722196b5bd945b50e0c8b9c391e23138175651
     */
    @Override
    public boolean updateHousing(HousingDTO updatedHousing) throws SQLException {
        String sql = "UPDATE alojamientos SET  nombre = ?, calle = ? WHERE id_alojamiento = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,updatedHousing.getNombre());
        preparedStatement.setString(2,updatedHousing.getCalle());
        preparedStatement.setInt(3,updatedHousing.getId_alojamiento());
        preparedStatement = connection.prepareStatement(sql);
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected != 0;

    }

}
