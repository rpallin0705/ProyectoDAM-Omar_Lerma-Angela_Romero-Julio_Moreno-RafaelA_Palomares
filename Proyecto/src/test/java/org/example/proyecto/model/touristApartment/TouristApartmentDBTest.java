package org.example.proyecto.model.touristApartment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TouristApartmentDBTest {
    TouristApartmentDB touristApartmentDB = new TouristApartmentDB();
    TouristApartmentDTO touristApartmentDTO = new TouristApartmentDTO(1,"ejemplo","c/ ejemplo1",200);
    TouristApartmentDTO updateTouristApartmentDTO = new TouristApartmentDTO(1,"ejemplo2","c/ ejempl3",1);

    TouristApartmentDBTest() throws SQLException, IOException {
    }

    @BeforeEach
    void setUp() throws SQLException {
        touristApartmentDB.insertTouristApartment(touristApartmentDTO);
    }

    @AfterEach
    void tearDown() throws SQLException {
        touristApartmentDB.deleteTouristApartment(touristApartmentDTO);
    }

    @Test
    void getTouristApartments() throws SQLException {
        List<TouristApartmentDTO> apartmentDTOList = touristApartmentDB.getTouristApartments();
        assertNotNull(apartmentDTOList);
        assertFalse(apartmentDTOList.isEmpty());

        for (TouristApartmentDTO apartmentDTO : apartmentDTOList) {
            System.out.println(apartmentDTO);
        }

        TouristApartmentDTO firstApartment = apartmentDTOList.get(0);
        assertEquals("ejemplo", firstApartment.getNombre());
    }

    @Test
    void updateTourisApartment() throws SQLException {
        assertFalse(touristApartmentDB.updateTourisApartment(updateTouristApartmentDTO));
    }

    @Test
    void deleteTouristApartment() throws SQLException {
        assertFalse(touristApartmentDB.deleteTouristApartment(touristApartmentDTO));
        assertFalse(touristApartmentDB.deleteTouristApartment(updateTouristApartmentDTO));
    }
}