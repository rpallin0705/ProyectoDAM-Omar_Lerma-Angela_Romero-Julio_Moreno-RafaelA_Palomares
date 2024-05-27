package org.example.proyecto;

import org.example.proyecto.model.booking.BookingDB;
import org.example.proyecto.model.booking.BookingDTO;
import org.example.proyecto.model.client.ClientDB;
import org.example.proyecto.model.client.ClientDTO;
import org.example.proyecto.model.user.UserDB;
import org.example.proyecto.model.user.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            ClientDB clientDB = new ClientDB();
            List<ClientDTO> lista = clientDB.getClients();
            for (ClientDTO client: lista)
                System.out.println(client.toString());
            BookingDB bookingDB = new BookingDB();
            BookingDTO bookingDTO = new BookingDTO(LocalDate.of(2024,12,1), LocalDate.of(2024,12,12),17,12);

            bookingDB.insertBooking(bookingDTO);
        } catch (SQLException e) {
            System.err.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
