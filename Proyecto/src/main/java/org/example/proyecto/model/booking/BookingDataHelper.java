package org.example.proyecto.model.booking;

import java.time.LocalDate;

/**
 * Helper class for booking data that includes additional information such as email.
 * This class encapsulates a {@link BookingDTO} object along with an email address.
 *
 * @version 1.0
 * @since 2024-05-28
 *
 * @Author Rafael
 */
public class BookingDataHelper {
    private BookingDTO booking;
    private String email;
    private String housingName;

    /**
     * Constructs a {@code BookingDataHelper} object with the specified booking and email.
     *
     * @param booking a {@link BookingDTO} object representing the booking.
     * @param email the email address associated with the booking.
     */
    public BookingDataHelper(BookingDTO booking, String email, String housingName) {
        this.booking = booking;
        this.email = email;
    }

    /**
     * Gets the booking ID.
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

    public String getHousingName() {
        return housingName;
    }

    public void setHousingName(String housingName) {
        this.housingName = housingName;
    }

    /**
     * Returns a string representation of the booking with email.
     * The string contains the booking details and the associated email address.
     *
     * @return the string representation of the booking with email.
     */
    @Override
    public String toString() {
        return "BookingWithEmailDTO{" +
                "booking=" + booking +
                ", email='" + email + '\'' +
                '}';
    }
}
