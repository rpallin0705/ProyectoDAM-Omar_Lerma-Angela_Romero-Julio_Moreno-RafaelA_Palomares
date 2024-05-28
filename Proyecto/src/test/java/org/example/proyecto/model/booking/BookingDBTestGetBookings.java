package org.example.proyecto.model.booking;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingDBTestGetBookings {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getBookings() {
        try {
            BookingDB BookingDB = new BookingDB();
            List<BookingDTO> bookingsList = BookingDB.getBookings();
            for (BookingDTO bookingDTO : bookingsList) {
                System.out.println(bookingDTO.toString());
            }
            assertTrue(bookingsList.isEmpty());
        } catch (SQLException |  IOException e) {
            throw new RuntimeException(e);
        }
    }
}