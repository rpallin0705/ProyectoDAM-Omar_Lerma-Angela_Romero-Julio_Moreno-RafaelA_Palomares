package org.example.proyecto.model.booking;

import org.example.proyecto.model.client.ClientDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
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
}