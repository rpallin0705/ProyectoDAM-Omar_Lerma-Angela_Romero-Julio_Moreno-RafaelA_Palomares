package org.example.proyecto.model.touristApartment;

import org.example.proyecto.SetUpConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the TouristApartmentDAO interface for interacting with tourist apartments in the database.
 *
 * @version 1.0
 * @since 2024-05-28
 * @author Omar
 */
public class TouristApartmentDB implements TouristApartmentDAO {
    private PreparedStatement preparedStatement;
    private Connection connection;
    private Statement statement;

    /**
     * Constructs a new TouristApartmentDB object and initializes the database connection.
     *
     * @throws SQLException if a database access error occurs.
     * @throws IOException  if an I/O error occurs.
     */
    public TouristApartmentDB() throws SQLException, IOException {
        connection = SetUpConnection.getInstance().getConnection();
    }

    /**
     * Retrieves the list of tourist apartments from the database.
     *
     * @return a list of TouristApartmentDTO objects.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public List<TouristApartmentDTO> getTouristApartments() throws SQLException {
        List<TouristApartmentDTO> apartments = new ArrayList<>();
        String sql = "SELECT * FROM vista_aps_turisticos";
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            TouristApartmentDTO touristApartmentDTO = new TouristApartmentDTO(
                    resultSet.getInt("id_alojamiento"),
                    resultSet.getString("nombre_alojamiento"),
                    resultSet.getString("calle"),
                    resultSet.getInt("dist_centro_km")
            );
            apartments.add(touristApartmentDTO);
        }
        return apartments;
    }

    /**
     * Updates a tourist apartment in the database.
     *
     * @param updatedApartment the updated tourist apartment.
     * @return true if the update was successful, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public boolean updateTourisApartment(TouristApartmentDTO updatedApartment) throws SQLException {
        String sql = "UPDATE alojamientos SET nombre = ?, calle = ? WHERE id_alojamiento = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, updatedApartment.getNombre());
        preparedStatement.setString(2, updatedApartment.getCalle());
        preparedStatement.setInt(3, updatedApartment.getHousingId());
        int rowsAffected = preparedStatement.executeUpdate();

        String sql2 = "UPDATE aps_turisticos SET dist_centro_km = ? WHERE id_alojamiento = ?";
        preparedStatement = connection.prepareStatement(sql2);
        preparedStatement.setFloat(1, updatedApartment.getDowntownDistance());
        preparedStatement.setInt(2, updatedApartment.getHousingId());
        rowsAffected += preparedStatement.executeUpdate();

        return rowsAffected != 0;
    }

    /**
     * Deletes a tourist apartment from the database.
     *
     * @param deletedApartment the tourist apartment to delete.
     * @return true if the deletion was successful, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public boolean deleteTouristApartment(TouristApartmentDTO deletedApartment) throws SQLException {
        String sql = "DELETE FROM alojamientos WHERE id_alojamiento = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, deletedApartment.getHousingId());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected != 0;
    }

    /**
     * Inserts a new tourist apartment into the database.
     *
     * @param insertedApartment the tourist apartment to insert.
     * @return true if the insertion was successful, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public boolean insertTouristApartment(TouristApartmentDTO insertedApartment) throws SQLException {
        String sql = "INSERT INTO alojamientos (nombre, calle) VALUES (?, ?)";
        preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, insertedApartment.getNombre());
        preparedStatement.setString(2, insertedApartment.getCalle());
        int rowsAffected = preparedStatement.executeUpdate();

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int idGenerado = generatedKeys.getInt(1);

            String sql2 = "INSERT INTO aps_turisticos (id_alojamiento, dist_centro_km) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setInt(1, idGenerado);
            preparedStatement.setFloat(2, insertedApartment.getDowntownDistance());
            rowsAffected += preparedStatement.executeUpdate();
        }

        return rowsAffected != 0;
    }
}
