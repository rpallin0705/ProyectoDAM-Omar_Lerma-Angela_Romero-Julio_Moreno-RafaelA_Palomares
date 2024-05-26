package org.example.proyecto.model.booking;

import org.example.proyecto.model.client.ClientDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

class BookingDBTest {



    @BeforeEach
    void setUp() throws SQLException, IOException {
        BookingDB bookingDB = new BookingDB();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getBookings() {
        List<BookingDB> BookingList = bookingDB.getBookings();
    }

    @Test
    void insertBooking() throws SQLException, IOException {
        BookingDB bookingDB = new BookingDB();
        BookingDTO bookingDTO = new BookingDTO(LocalDate.of(2004,12,8),LocalDate.of(2004,12,12),1,1,1);
        bookingDB.insertBooking(bookingDTO);
    }
}