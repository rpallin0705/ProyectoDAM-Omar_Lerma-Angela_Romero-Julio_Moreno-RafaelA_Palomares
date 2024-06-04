package org.example.proyecto.model.touristApartment;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface representing operations for interacting with tourist apartments in the database.
 *
 * @version 1.0
 * @since 2024-05-28
 *
 * @Author Omar
 */
public interface TouristApartmentDAO {
    /**
     * Retrieves a list of tourist apartments from the database.
     *
     * @return a list of tourist apartments.
     * @throws SQLException if an SQL exception occurs.
     */
    List<TouristApartmentDTO> getTouristApartments() throws SQLException;

    /**
     * Updates a specific tourist apartment in the database.
     *
     * @param updatedApartment the updated tourist apartment.
     * @return true if the update is successful, false otherwise.
     * @throws SQLException if an SQL exception occurs.
     */
    boolean updateTourisApartment(TouristApartmentDTO updatedApartment) throws SQLException;

    /**
     * Deletes a specific tourist apartment from the database.
     *
     * @param deletedApartment the tourist apartment to delete.
     * @return true if the deletion is successful, false otherwise.
     * @throws SQLException if an SQL exception occurs.
     */
    boolean deleteTouristApartment(TouristApartmentDTO deletedApartment) throws SQLException;

    /**
     * Inserts a new tourist apartment into the database.
     *
     * @param insertedApartment the tourist apartment to insert.
     * @return true if the insertion is successful, false otherwise.
     * @throws SQLException if an SQL exception occurs.
     */
    boolean insertTouristApartment(TouristApartmentDTO insertedApartment) throws SQLException;
}
