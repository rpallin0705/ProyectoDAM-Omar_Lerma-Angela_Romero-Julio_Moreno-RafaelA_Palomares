package org.example.proyecto.model.booking;

import java.time.LocalDate;

public class BookingDataHelper {
    private BookingDTO booking;
    private String email;

    public BookingDataHelper(BookingDTO booking, String email) {
        this.booking = booking;
        this.email = email;
    }

    public int getBookingId() {
        return booking.getBookingId();
    }

    public LocalDate getCheckInDate() {
        return booking.getCheckInDate();
    }

    public LocalDate getCheckOutDate() {
        return booking.getCheckOutDate();
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "BookingWithEmailDTO{" +
                "booking=" + booking +
                ", email='" + email + '\'' +
                '}';
    }
}
