package org.example.proyecto.model.booking;

import org.example.proyecto.model.client.ClientDTO;
import org.example.proyecto.model.hotel.HotelDTO;
import org.example.proyecto.model.touristApartment.TouristApartmentDTO;

import java.time.LocalDate;

/**
 * Helper class for booking data that includes additional information such as email and housing name.
 * This class encapsulates a {@link BookingDTO} object along with an email address and housing name.
 *
 * @version 1.0
 * @since 2024-05-28
 * @author Rafael
 */
public class BookingDataHelper {
    private BookingDTO booking;
    private String email;
    private String housingName;
    private ClientDTO clientForBooking;
    private HotelDTO hotelForBooking;
    private TouristApartmentDTO apartmentForBooking;

    /**
     * Constructs a {@code BookingDataHelper} object with the specified booking, email, and housing name.
     *
     * @param booking    a {@link BookingDTO} object representing the booking.
     * @param email      the email address associated with the booking.
     * @param housingName the name of the housing associated with the booking.
     */
    public BookingDataHelper(BookingDTO booking, String email, String housingName) {
        this.booking = booking;
        this.email = email;
        this.housingName = housingName;
    }

    /**
     * Constructs a {@code BookingDataHelper} object with the specified client and hotel for booking.
     *
     * @param clientForBooking a {@link ClientDTO} object representing the client for booking.
     * @param hotelForBooking  a {@link HotelDTO} object representing the hotel for booking.
     */
    public BookingDataHelper(ClientDTO clientForBooking, HotelDTO hotelForBooking) {
        this.clientForBooking = clientForBooking;
        this.hotelForBooking = hotelForBooking;
    }

    /**
     * Constructs a {@code BookingDataHelper} object with the specified client and apartment for booking.
     *
     * @param clientForBooking     a {@link ClientDTO} object representing the client for booking.
     * @param apartmentForBooking a {@link TouristApartmentDTO} object representing the apartment for booking.
     */
    public BookingDataHelper(ClientDTO clientForBooking, TouristApartmentDTO apartmentForBooking) {
        this.clientForBooking = clientForBooking;
        this.apartmentForBooking = apartmentForBooking;
    }

    /**
     * Constructs an empty {@code BookingDataHelper} object.
     */
    public BookingDataHelper() {}

    /**
     * Constructs a {@code BookingDataHelper} object with the same booking as the given {@code BookingDataHelper}.
     *
     * @param selectedBooking the {@code BookingDataHelper} whose booking is to be copied.
     */
    public BookingDataHelper(BookingDataHelper selectedBooking) {
        this.booking = selectedBooking.getBooking();
    }

    /**
     * Gets the booking associated with this helper.
     *
     * @return the booking.
     */
    public BookingDTO getBooking() {
        return booking;
    }

    /**
     * Gets the ID of the booking.
     *
     * @return the booking ID.
     */
    public int getBookingId() {
        return booking.getBookingId();
    }

    /**
     * Gets the check-in date of the booking.
     *
     * @return the check-in date.
     */
    public LocalDate getCheckInDate() {
        return booking.getCheckInDate();
    }

    /**
     * Gets the check-out date of the booking.
     *
     * @return the check-out date.
     */
    public LocalDate getCheckOutDate() {
        return booking.getCheckOutDate();
    }

    /**
     * Gets the email address associated with the booking.
     *
     * @return the email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the name of the housing associated with the booking.
     *
     * @return the housing name.
     */
    public String getHousingName() {
        return housingName;
    }

    /**
     * Sets the name of the housing associated with the booking.
     *
     * @param housingName the housing name to set.
     */
    public void setHousingName(String housingName) {
        this.housingName = housingName;
    }

    /**
     * Gets the hotel associated with the booking.
     *
     * @return the hotel for booking.
     */
    public HotelDTO getHotelForBooking() {
        return hotelForBooking;
    }

    /**
     * Sets the hotel associated with the booking.
     *
     * @param hotelForBooking the hotel for booking to set.
     */
    public void setHotelForBooking(HotelDTO hotelForBooking) {
        this.hotelForBooking = hotelForBooking;
    }

    /**
     * Gets the apartment associated with the booking.
     *
     * @return the apartment for booking.
     */
    public TouristApartmentDTO getApartmentForBooking() {
        return apartmentForBooking;
    }

    /**
     * Sets the apartment associated with the booking.
     *
     * @param apartmentForBooking the apartment for booking to set.
     */
    public void setApartmentForBooking(TouristApartmentDTO apartmentForBooking) {
        this.apartmentForBooking = apartmentForBooking;
    }

    /**
     * Gets the client associated with the booking.
     *
     * @return the client for booking.
     */
    public ClientDTO getClientForBooking() {
        return clientForBooking;
    }

    /**
     * Sets the client associated with the booking.
     *
     * @param clientForBooking the client for booking to set.
     */
    public void setClientForBooking(ClientDTO clientForBooking) {
        this.clientForBooking = clientForBooking;
    }

    /**
     * Returns a string representation of the booking with email.
     * The string contains the booking details and the associated email address.
     *
     * @return the string representation of the booking with email.
     */
    @Override
    public String toString() {
        return "BookingDataHelper{" +
                "booking=" + booking +
                ", email='" + email + '\'' +
                '}';
    }
}
