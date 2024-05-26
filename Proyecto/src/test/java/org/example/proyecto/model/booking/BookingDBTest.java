package org.example.proyecto.model.booking;

import org.example.proyecto.model.touristApartment.TouristApartmentDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookingDBTest {
    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }
    @Test
    void insertBooking() throws SQLException, IOException {
        BookingDTO bookingDTO = new BookingDTO(LocalDate.of(2004,12,8),LocalDate.of(2004,12,12),1,1,1);
        BookingDB bookingDB = new BookingDB();
        bookingDB.insertBooking(bookingDTO);
    }
}