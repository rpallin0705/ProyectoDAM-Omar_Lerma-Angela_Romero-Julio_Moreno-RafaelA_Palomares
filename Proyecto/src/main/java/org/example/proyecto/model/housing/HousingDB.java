package org.example.proyecto.model.housing;

import org.example.proyecto.SetUpConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for accessing housing data from a database.
 * This class implements the HousingDAO interface and provides methods to retrieve a list of housings and update a housing record.
 *
 * @version 1.0
 * @since 2024-05-28
 *
 * @Author Omar
 */
public class HousingDB implements HousingDAO {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    /**
     * Constructs a new HousingDB object and establishes a connection to the database.
     *
     * @throws SQLException if an SQL exception occurs while establishing the database connection.
     * @throws IOException  if an I/O exception occurs.
     */
    public HousingDB() throws SQLException, IOException {
        connection = SetUpConnection.getInstance().getConnection();
    }

    /**
     * Retrieves a list of housings from the database.
     *
     * @return a list of HousingDTO objects representing the housings.
     * @throws SQLException if an error occurs while accessing the database.
     */
    @Override
    public List<HousingDTO> getHousings() throws SQLException {
        List<HousingDTO> housings = new ArrayList<>();
        String sql = "SELECT * FROM alojamientos;";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        HousingDTO housingDTO = null;
        while (resultSet.next()) {
            int id_alojamiento = resultSet.getInt("id_alojamiento");
            String nombre = resultSet.getString("nombre");
            String calle = resultSet.getString("calle");
            housingDTO = new HousingDTO(id_alojamiento, nombre, calle);
            housings.add(housingDTO);
        }
        return housings;
    }

    /**
     * Updates a housing record in the database.
     *
     * @param updatedHousing the HousingDTO object containing the updated information.
     * @return true if the update operation is successful, false otherwise.
     * @throws SQLException if an error occurs while accessing the database.
     */
    @Override
    public boolean updateHousing(HousingDTO updatedHousing) throws SQLException {
        String sql = "UPDATE alojamientos SET  nombre = ?, calle = ? WHERE id_alojamiento = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, updatedHousing.getNombre());
        preparedStatement.setString(2, updatedHousing.getCalle());
        preparedStatement.setInt(3, updatedHousing.getHousingId());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }
}
