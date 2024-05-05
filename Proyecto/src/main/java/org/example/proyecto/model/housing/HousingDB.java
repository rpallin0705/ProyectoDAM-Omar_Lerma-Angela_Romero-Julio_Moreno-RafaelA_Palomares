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
     * Get a list of housings from the database.
     * @return List<HousingDTO> stored in the database.
     * @throws SQLException if an error occurs duringq the execution of the query.
     */
    @Override
    public List<HousingDTO> getHousings() throws SQLException {
        List<HousingDTO> housings = new ArrayList<>();
        String sql = "SELECT * FROM alojamientos;";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        HousingDTO housingDTO = null;
        while (resultSet.next()){
            String codAlojamiento = resultSet.getString("cod_alojamiento");
            String nombre = resultSet.getString("nom_alojamiento");
            String direccionAlojamiento = resultSet.getString("direccion_alojamiento");
            int numHuespedes= resultSet.getInt("num_huespedes");
            String housingType = resultSet.getString("tipo_alojamiento");
            if (housingType.equals("HOTELES"))
                //housingDTO = new HousingDTO(codAlojamiento, nombre, direccionAlojamiento, numHuespedes, HousingType.HOTELES);
            else
                //housingDTO = new HousingDTO(codAlojamiento, nombre, direccionAlojamiento, numHuespedes, HousingType.APARTAMENTOS_TURISTICOS);

            housings.add(housingDTO);
        }
        return housings;
    }

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
     */
    @Override
    public boolean updateHousing(HousingDTO updatedHousing) throws SQLException {
        String sql = "UPDATE alojamientos SET nom_alojamiento = ?, direccion_alojamiento = ?, num_huespedes = ?, tipo_alojamiento = ? WHERE cod_alojamiento = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, updatedHousing.getNombreAlojamiento());
        preparedStatement.setString(2, updatedHousing.getDireccionAlojamiento());
        preparedStatement.setInt(3, updatedHousing.getNumHuespedes());
        preparedStatement.setString(4, updatedHousing.getTipoAlojamiento().toString());
        preparedStatement.setString(5, updatedHousing.getId_alojamiento());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }

}
