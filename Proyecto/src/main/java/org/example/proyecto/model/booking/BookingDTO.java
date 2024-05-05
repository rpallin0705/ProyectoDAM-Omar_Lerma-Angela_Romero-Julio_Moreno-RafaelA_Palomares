package org.example.proyecto.model.booking;

<<<<<<< HEAD
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
=======
import java.time.LocalDate;

public class BookingDTO {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int id_reserva;
    private int id_cuenta;

    /**
     *
     * @param fechaInicio starting date
     * @param fechaFin ending date
     * @param id_reserva booking id
     * @param id_cuenta account id
     */
    public BookingDTO(LocalDate fechaInicio, LocalDate fechaFin, int id_reserva, int id_cuenta) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.id_reserva = id_reserva;
        this.id_cuenta = id_cuenta;
    }

    /*GETTERS*/

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }
    /*SETTERS*/

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
>>>>>>> a3722196b5bd945b50e0c8b9c391e23138175651
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

<<<<<<< HEAD
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
=======
    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
>>>>>>> a3722196b5bd945b50e0c8b9c391e23138175651
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return String.format("%s,%s,%s,%s,%s", checkInDate, checkOutDate, email, phoneNumber, housingCode);
=======
        return String.format("%s,%s,%d,%d",fechaInicio,fechaFin,id_reserva,id_cuenta);
>>>>>>> a3722196b5bd945b50e0c8b9c391e23138175651
    }
}
