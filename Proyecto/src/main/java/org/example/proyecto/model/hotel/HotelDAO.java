package org.example.proyecto.model.hotel;

import java.sql.SQLException;
import java.util.List;

/**
 * Data Access Object (DAO) interface for hotel operations.
 * This interface defines methods for performing CRUD operations on hotels in the database.
 *
 * @version 1.0
 * @since 2024-05-28
 *
 * @Author Omar
 */
public interface HotelDAO {

    /**
     * Retrieves a list of all hotels from the database.
     *
     * @return a List of HotelDTO objects representing all hotels in the database.
     * @throws SQLException if a database access error occurs.
     */
    List<HotelDTO> getHotels() throws SQLException;

    /**
     * Updates a specific hotel in the database.
     *
     * @param updatedHotel the HotelDTO object representing the updated hotel.
     * @return true if the update operation was successful, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    boolean updateHotel(HotelDTO updatedHotel) throws SQLException;

    /**
     * Deletes a specific hotel from the database.
     *
     * @param deletedHotel the HotelDTO object representing the hotel to be deleted.
     * @return true if the delete operation was successful, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    boolean deleteHotel(HotelDTO deletedHotel) throws SQLException;

    /**
     * Inserts a new hotel into the database.
     *
     * @param insertedHotel the HotelDTO object representing the new hotel to be inserted.
     * @return true if the insert operation was successful, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    boolean insertHotel(HotelDTO insertedHotel) throws SQLException;
}
