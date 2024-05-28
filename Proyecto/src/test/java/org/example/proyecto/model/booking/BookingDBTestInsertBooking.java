package org.example.proyecto.model.booking;

import org.example.proyecto.model.client.ClientDB;
import org.example.proyecto.model.client.ClientDTO;
import org.example.proyecto.model.touristApartment.TouristApartmentDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookingDBTestInsertBooking {

    BookingDB bookingDB = new BookingDB();
    ClientDB clientDB = new ClientDB();
    ClientDTO clientDTO = new ClientDTO(1,"correo@gmail.com","123qw","nombre","C/asa");
    BookingDTO bookingDTO = new BookingDTO(LocalDate.of(2024,5,28),LocalDate.of(2024,5,30),1,1,1);

    BookingDBTestInsertBooking() throws SQLException, IOException {
    }


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() throws SQLException {
        clientDB.deleteClient(clientDTO);
        bookingDB.deleteBookingByID(bookingDTO);

    }

    @Test
    void insertBooking() throws SQLException {
            assertTrue(clientDB.insertClient(clientDTO));
            assertTrue(bookingDB.insertBooking(bookingDTO));

    }
}