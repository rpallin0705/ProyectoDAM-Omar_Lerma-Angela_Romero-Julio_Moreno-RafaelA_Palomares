package org.example.proyecto.model.booking;

public class BookingDTO {
    private String fechaInicio;
    private String fechaFin;
    private String email;
    private String telefono;
    private String codAlojamiento;

    public BookingDTO(String fechaInicio, String fechaFin, String email, String telefono, String codAlojamiento) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.email = email;
        this.telefono = telefono;
        this.codAlojamiento = codAlojamiento;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
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
