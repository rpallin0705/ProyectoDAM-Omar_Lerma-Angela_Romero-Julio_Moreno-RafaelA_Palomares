package org.example.proyecto.model.housing;

import java.sql.SQLException;
import java.util.List;

public interface HousingDAO {
    List<HousingDTO> getHousings() throws SQLException;
    boolean insertHousing(HousingDTO newHousing) throws SQLException;
    boolean updateHousing(HousingDTO updatedHousing) throws SQLException;
}
