package org.example.proyecto.model.hotel;

import java.sql.SQLException;
import java.util.List;

public interface HotelDAO {
    /**
     * INTERFACE - HOTEL
     * method that connects with the DataBase and gets a list of hotels, looking in a view, not directly the tables
     * @return list of hotels
     * @throws SQLException
     */
    List<HotelDTO> getHotels() throws SQLException;
    /**
     * INTERFACE - HOTEL
     * method that connects with the DataBase and updates a concrete record in the housing table
     * and then updates a concrete record in the hotel table (both searching by ID)
     * @return true if the rows affected are not 0
     * @throws SQLException
     */
    boolean updateHotel(HotelDTO updatedHotel) throws SQLException;
    /**
     * INTERFACE - HOTEL
     * method that connects with the DataBase and deletes a concrete hotel from the housing table (on cascade) searching by ID
     * @return true if the rows affected are not 0
     * @throws SQLException
     */
    boolean deleteHotel(HotelDTO deletedHotel) throws SQLException;
    /**
     * INTERFACE - HOTEL
     * method that connects with the DataBase and insert a record into the housing table, then takes de ID
     * (autoincremental) and insert a record into the hotels apartments table
     * @return true if the rows affected are not 0
     * @throws SQLException
     */
    boolean insertHotel(HotelDTO insertedHotel) throws SQLException;
}
