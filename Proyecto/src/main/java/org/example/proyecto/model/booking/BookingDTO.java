package org.example.proyecto.model.booking;

import java.time.LocalDate;

public class BookingDTO {
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int bookingId;
    private int countId;

    /**
     *
     * @param fechaInicio starting date
     * @param checkOutDate ending date
     * @param bookingId booking id
     * @param countId account id
     */
    public BookingDTO(LocalDate fechaInicio, LocalDate checkOutDate, int bookingId, int countId) {
        this.checkInDate = fechaInicio;
        this.checkOutDate = checkOutDate;
        this.bookingId = bookingId;
        this.countId = countId;
    }

    public BookingDTO(LocalDate checkInDate, LocalDate checkOutDate, int countId) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.countId = countId;
    }

    /*GETTERS*/

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getCountId() {
        return countId;
    }
    /*SETTERS*/

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }


    public void setCountId(int countId) {
        this.countId = countId;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%d,%d", checkInDate, checkOutDate, bookingId, countId);
    }
}
