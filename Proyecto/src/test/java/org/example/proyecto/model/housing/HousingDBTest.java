package org.example.proyecto.model.housing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HousingDBTest {
    HousingDB housingDB = new HousingDB();
    HousingDTO housingDTO = new HousingDTO(1, "Casa1", "c/ ruiz jimenez 2");
    HousingDTO housingDTO1 = new HousingDTO(2, "Casa2", "Avenida Madrid 3");
    HousingDBTest() throws SQLException, IOException {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown(){
    }

    @Test
    void getHousings() throws SQLException {
        List<HousingDTO> housingList = housingDB.getHousings();
        assertNotNull(housingList);
        assertFalse(housingList.isEmpty());
        for (HousingDTO housing : housingList) {
            System.out.println(housing.toString());
        }

        HousingDTO firstHousing = housingList.get(0);
        assertEquals("posada", firstHousing.getNombre());
    }

    @Test
    void updateHousing() throws SQLException {
        HousingDTO updatedHousing = new HousingDTO(1, "CasaEjemplo", "c/ruiz jimenez 4");
        assertFalse(housingDB.updateHousing(updatedHousing));
    }
}