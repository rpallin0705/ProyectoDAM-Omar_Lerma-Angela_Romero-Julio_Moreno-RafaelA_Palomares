package org.example.proyecto.model.booking;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for booking information.
 * This class encapsulates the data of a booking and provides methods for accessing and manipulating this data.
 *
 * @version 1.0
 * @since 2024-05-28
 *
 * @Author Omar, Rafael
 */
public class BookingDTO {
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int bookingId;
    private int countId;
    private int housingId;

    /**
     * Constructs a {@code BookingDTO} object with the specified details.
     *
     * @param checkInDate the starting date of the booking.
     * @param checkOutDate the ending date of the booking.
     * @param bookingId the ID of the booking.
     * @param countId the ID of the account associated with the booking.
     * @param housingId the ID of the housing associated with the booking.
     */
    public BookingDTO(LocalDate checkInDate, LocalDate checkOutDate, int bookingId, int countId, int housingId) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingId = bookingId;
        this.countId = countId;
        this.housingId = housingId;
    }

    /* GETTERS */

    /**
     * Gets the check-in date of the booking.
     *
     * @return the check-in date.
     */
    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    /**
     * Gets the check-out date of the booking.
     *
     * @return the check-out date.
     */
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * Gets the ID of the booking.
     *
     * @return the booking ID.
     */
    public int getBookingId() {
        return bookingId;
    }

    /**
     * Gets the ID of the account associated with the booking.
     *
     * @return the account ID.
     */
    public int getCountId() {
        return countId;
    }

    /**
     * Gets the ID of the housing associated with the booking.
     *
     * @return the housing ID.
     */
    public int getHousingId() {
        return housingId;
    }

    /* SETTERS */

    /**
     * Sets the check-in date of the booking.
     *
     * @param checkInDate the new check-in date.
     */
    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    /**
     * Sets the check-out date of the booking.
     *
     * @param checkOutDate the new check-out date.
     */
    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    /**
     * Sets the ID of the booking.
     *
     * @param bookingId the new booking ID.
     */
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * Sets the ID of the account associated with the booking.
     *
     * @param countId the new account ID.
     */
    public void setCountId(int countId) {
        this.countId = countId;
    }

    /**
     * Sets the ID of the housing associated with the booking.
     *
     * @param housingId the new housing ID.
     */
    public void setHousingId(int housingId) {
        this.housingId = housingId;
    }

    /**
     * Returns a string representation of the booking.
     * The string contains the check-in date, check-out date, booking ID, and account ID, separated by commas.
     *
     * @return the string representation of the booking.
     */
    @Override
    public String toString() {
        return String.format("%s,%s,%d,%d", checkInDate, checkOutDate, bookingId, countId);
    }
}
