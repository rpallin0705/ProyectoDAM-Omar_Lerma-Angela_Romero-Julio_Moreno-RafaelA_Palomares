package org.example.proyecto.model.touristApartment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class TouristApartmentDBTest {

    TouristApartmentDTO touristApartmentDTO = new TouristApartmentDTO(1, "Apt 1", "C/ Ruiz Jimenez", 100);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void insertTouristApartment() throws SQLException, IOException {
        TouristApartmentDB touristApartmentDB = new TouristApartmentDB();
        touristApartmentDB.insertTouristApartment(touristApartmentDTO);
    }
}