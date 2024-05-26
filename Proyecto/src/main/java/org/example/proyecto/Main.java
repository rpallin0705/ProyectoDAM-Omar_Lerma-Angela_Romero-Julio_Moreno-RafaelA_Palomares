package org.example.proyecto;

import org.example.proyecto.model.booking.BookingDB;
import org.example.proyecto.model.booking.BookingDTO;
import org.example.proyecto.model.user.UserDB;
import org.example.proyecto.model.user.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            BookingDB bookingDB = new BookingDB();
            List<BookingDTO> lista = bookingDB.getBookings();

        } catch (SQLException e) {
            System.err.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
