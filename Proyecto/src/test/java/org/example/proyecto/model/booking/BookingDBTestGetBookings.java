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

class BookingDBTestGetBookings {

    BookingDB bookingDB = new BookingDB();
    ClientDB clientDB = new ClientDB();
    ClientDTO clientDTO = new ClientDTO(1,"correo@gmail.com","123qw","nombre","C/asa");
    BookingDTO bookingDTO = new BookingDTO(LocalDate.of(2024,5,28),LocalDate.of(2024,5,30),1,1,1);

    BookingDBTestGetBookings() throws SQLException, IOException {
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
    void getBookings() {
        try {
            BookingDB BookingDB = new BookingDB();
            List<BookingDTO> bookingsList = BookingDB.getBookings();
            for (BookingDTO bookingDTO : bookingsList) {
                System.out.println(bookingDTO.toString());
            }
            assertTrue(!bookingsList.isEmpty());
        } catch (SQLException |  IOException e) {
            throw new RuntimeException(e);
        }
    }
}