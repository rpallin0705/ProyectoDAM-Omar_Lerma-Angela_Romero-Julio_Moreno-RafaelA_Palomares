package org.example.proyecto.model.alojamientos;

public class AlojamientosDTO {
    private String codAlojamiento;
    private String nombreAlojamiento;
    private String direccionAlojamiento;
    private int numeHuespedes;
    private TipoAlojamiento tipoAlojamiento;

    public AlojamientosDTO(String codAlojamiento, String nombreAlojamiento, String direccionAlojamiento, int numeHuespedes, TipoAlojamiento tipoAlojamiento) {
        this.codAlojamiento = codAlojamiento;
        this.nombreAlojamiento = nombreAlojamiento;
        this.direccionAlojamiento = direccionAlojamiento;
        this.numeHuespedes = numeHuespedes;
        this.tipoAlojamiento = tipoAlojamiento;
    }

    public String getCodAlojamiento() {
        return codAlojamiento;
    }

    public void setCodAlojamiento(String codAlojamiento) {
        this.codAlojamiento = codAlojamiento;
    }

    public String getNombreAlojamiento() {
        return nombreAlojamiento;
    }

    public void setNombreAlojamiento(String nombreAlojamiento) {
        this.nombreAlojamiento = nombreAlojamiento;
    }

    public String getDireccionAlojamiento() {
        return direccionAlojamiento;
    }

    public void setDireccionAlojamiento(String direccionAlojamiento) {
        this.direccionAlojamiento = direccionAlojamiento;
    }

    public int getNumeHuespedes() {
        return numeHuespedes;
    }

    public void setNumeHuespedes(int numeHuespedes) {
        this.numeHuespedes = numeHuespedes;
    }

    public TipoAlojamiento getTipoAlojamiento() {
        return tipoAlojamiento;
    }

    public void setTipoAlojamiento(TipoAlojamiento tipoAlojamiento) {
        this.tipoAlojamiento = tipoAlojamiento;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%d,%s", codAlojamiento, nombreAlojamiento, direccionAlojamiento, numeHuespedes, tipoAlojamiento);
    }
}
