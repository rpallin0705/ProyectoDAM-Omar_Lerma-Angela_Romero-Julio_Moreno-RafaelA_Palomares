package org.example.proyecto.model.touristApartment;
import java.sql.SQLException;
import java.util.List;

public interface TouristApartmentDAO {
    /**
     * INTERFACE - TOURIST APARTMENTS
     * method that connects with the DataBase and gets a list of apartments, looking in a view, not directly the tables
     * @return list of tourist apartments
     * @throws SQLException
     */
    List<TouristApartmentDTO> getTouristApartments() throws SQLException;
    /**
     * INTERFACE - TOURIST APARTMENTS
     * method that connects with the DataBase and updates a concrete record in the housing table
     * and then updates a concrete record in the tourist apartments table (both searching by ID)
     * @return true if the rows affected are not 0
     * @throws SQLException
     */
    boolean updateTourisApartment(TouristApartmentDTO updatedApartment) throws SQLException;
    /**
     * INTERFACE - TOURIST APARTMENTS
     * method that connects with the DataBase and deletes a concrete apartment from the housing table (on cascade) searching by ID
     * @return true if the rows affected are not 0
     * @throws SQLException
     */
    boolean deleteTouristApartment(TouristApartmentDTO deletedApartment) throws SQLException;
    /**
     * INTERFACE - TOURIST APARTMENTS
     * method that connects with the DataBase and insert a record into the housing table, then takes de ID
     * (autoincremental) and insert a record into the tourist apartments table
     * @return true if the rows affected are not 0
     * @throws SQLException
     */
    boolean insertTouristApartment(TouristApartmentDTO insertedApartment) throws SQLException;
}
