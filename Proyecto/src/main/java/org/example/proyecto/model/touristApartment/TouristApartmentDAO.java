package org.example.proyecto.model.touristApartment;

import org.example.proyecto.model.hotel.HotelDTO;

import java.sql.SQLException;
import java.util.List;

public interface TouristApartmentDAO {
    List<TouristApartmentDTO> getHotels() throws SQLException;
    boolean updateTourisApartment(TouristApartmentDTO updatedApartment) throws SQLException;
    boolean deleteTouristApartment(TouristApartmentDTO deletedApartment) throws SQLException;
    boolean insertTouristApartment(TouristApartmentDTO insertedApartment) throws SQLException;
}
