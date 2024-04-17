package org.example.proyecto.model.reserva;

import java.time.LocalDate;

public class ReservaDTO {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String email;
    private String telefono;
    private String codAlojamiento;

    public ReservaDTO(LocalDate fechaInicio, LocalDate fechaFin, String email, String telefono, String codAlojamiento) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.email = email;
        this.telefono = telefono;
        this.codAlojamiento = codAlojamiento;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCodAlojamiento() {
        return codAlojamiento;
    }

    public void setCodAlojamiento(String codAlojamiento) {
        this.codAlojamiento = codAlojamiento;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", fechaInicio, fechaFin, email, telefono, codAlojamiento);
    }
}
