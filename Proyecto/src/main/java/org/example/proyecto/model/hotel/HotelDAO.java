package org.example.proyecto.model.hotel;

import org.example.proyecto.model.housing.HousingDTO;

import java.sql.SQLException;
import java.util.List;

public interface HotelDAO {
    List<HotelDTO> getHotels() throws SQLException;
    boolean updateHotel(HotelDTO updatedHotel) throws SQLException;
    boolean deleteHotel(HotelDTO deletedHotel) throws SQLException;
    boolean insertHotel(HotelDTO insertedHotel) throws SQLException;
}
