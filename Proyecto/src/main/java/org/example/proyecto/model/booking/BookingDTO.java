package org.example.proyecto.model.booking;

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
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%d,%d",fechaInicio,fechaFin,id_reserva,id_cuenta);
    }
}
