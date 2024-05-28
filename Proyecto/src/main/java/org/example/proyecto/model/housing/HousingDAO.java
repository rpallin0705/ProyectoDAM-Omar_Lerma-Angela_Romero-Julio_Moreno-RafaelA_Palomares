package org.example.proyecto.model.housing;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for accessing housing data from a database.
 * This interface provides methods to retrieve a list of housings and to update a housing record.
 *
 * @version 1.0
 * @since 2024-05-28
 *
 * @Author Omar
 */
public interface HousingDAO {
    /**
     * Retrieves a list of housings from the database.
     *
     * @return a list of HousingDTO objects representing the housings.
     * @throws SQLException if an error occurs while accessing the database.
     */
    List<HousingDTO> getHousings() throws SQLException;

    /**
     * Updates a housing record in the database.
     *
     * @param updatedHousing the HousingDTO object containing the updated information.
     * @return true if the update operation is successful, false otherwise.
     * @throws SQLException if an error occurs while accessing the database.
     */
    boolean updateHousing(HousingDTO updatedHousing) throws SQLException;
}
