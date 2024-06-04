package org.example.proyecto.model.booking;

import org.example.proyecto.model.client.ClientDB;
import org.example.proyecto.model.client.ClientDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingDBTest {
    BookingDB bookingDB = new BookingDB();

    BookingDBTest() throws SQLException, IOException {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void deleteBookingByID() throws SQLException {
        BookingDTO bookingDTO = new BookingDTO(LocalDate.of(2024,5,28),LocalDate.of(2024,5,30),1,1,1);
        assertFalse(bookingDB.deleteBookingByID(bookingDTO));

        List<BookingDTO> bookingDTOList = bookingDB.getBookings();
        assertTrue(bookingDTOList.isEmpty());
    }

    @Test
    void updateBooking() throws SQLException {
        BookingDTO updateBookingDTO = new BookingDTO(LocalDate.of(2024,4,28),LocalDate.of(2024,7,30),1,1,1);
        assertFalse(bookingDB.updateBooking(updateBookingDTO));
    }
}