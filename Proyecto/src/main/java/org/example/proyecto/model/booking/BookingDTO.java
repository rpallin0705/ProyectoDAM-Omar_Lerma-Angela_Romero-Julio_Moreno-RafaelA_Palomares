package org.example.proyecto.model.booking;

public class BookingDTO {
    private String checkInDate;
    private String checkOutDate;
    private String email;
    private String phoneNumber;
    private String housingCode;

    public BookingDTO(String checkInDate, String checkOutDate, String email, String phoneNumber, String housingCode) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.housingCode = housingCode;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHousingCode() {
        return housingCode;
    }

    public void setHousingCode(String housingCode) {
        this.housingCode = housingCode;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", checkInDate, checkOutDate, email, phoneNumber, housingCode);
    }
}
