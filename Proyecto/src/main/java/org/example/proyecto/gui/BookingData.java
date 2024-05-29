package org.example.proyecto.gui;

import org.example.proyecto.model.client.ClientDTO;
import org.example.proyecto.model.hotel.HotelDTO;
import org.example.proyecto.model.touristApartment.TouristApartmentDTO;

public class BookingData {
    private ClientDTO clientForBooking;
    private HotelDTO hotelForBooking;
    private TouristApartmentDTO touristApartmentForBooking;

    public ClientDTO getClientForBooking() {
        return clientForBooking;
    }

    public void setClientForBooking(ClientDTO clientForBooking) {
        this.clientForBooking = clientForBooking;
    }

    public HotelDTO getHotelForBooking() {
        return hotelForBooking;
    }

    public void setHotelForBooking(HotelDTO hotelForBooking) {
        this.hotelForBooking = hotelForBooking;
    }

    public TouristApartmentDTO getTouristApartmentForBooking() {
        return touristApartmentForBooking;
    }

    public void setTouristApartmentForBooking(TouristApartmentDTO touristApartmentForBooking) {
        this.touristApartmentForBooking = touristApartmentForBooking;
    }
}
