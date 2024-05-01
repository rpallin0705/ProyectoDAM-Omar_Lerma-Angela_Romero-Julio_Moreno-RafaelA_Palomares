package org.example.proyecto.model.housing;

import java.sql.SQLException;
import java.util.List;

public interface HousingDAO {
    /**
     * INTERFACE - HOUSING
     * method that connects with the DataBase and gets a list of housings
     * @return list of housings
     * @throws SQLException
     */
    List<HousingDTO> getHousings() throws SQLException;
    /**
     * INTERFACE - HOUSING
     * method that connects with the DataBase and updates a concrete Housing searching by ID
     * @return true if the rows affected are not 0
     * @throws SQLException
     */
    boolean updateHousing(HousingDTO updatedHousing) throws SQLException;
}
