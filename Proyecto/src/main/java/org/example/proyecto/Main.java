package org.example.proyecto;

import org.example.proyecto.model.client.ClientDB;
import org.example.proyecto.model.client.ClientDTO;
import org.example.proyecto.model.hotel.HotelDB;
import org.example.proyecto.model.hotel.HotelDTO;
import org.example.proyecto.model.hotel.RoomType;
import org.example.proyecto.model.touristApartment.TouristApartmentDB;
import org.example.proyecto.model.touristApartment.TouristApartmentDTO;
import org.example.proyecto.model.user.UserDB;
import org.example.proyecto.model.user.UserDTO;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            ClientDB clientDB = new ClientDB();
            clientDB.getClients().forEach(clientDTO -> {
                try {
                    clientDB.deleteClient(clientDTO);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());;
                }
            });

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
