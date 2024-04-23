package org.example.proyecto.model.housing;

public class HousingDTO {
    private String codAlojamiento;
    private String nombreAlojamiento;
    private String direccionAlojamiento;
    private int numeHuespedes;
    private HousingType housingType;

    public HousingDTO(String codAlojamiento, String nombreAlojamiento, String direccionAlojamiento, int numeHuespedes, HousingType housingType) {
        this.codAlojamiento = codAlojamiento;
        this.nombreAlojamiento = nombreAlojamiento;
        this.direccionAlojamiento = direccionAlojamiento;
        this.numeHuespedes = numeHuespedes;
        this.housingType = housingType;
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

    public HousingType getTipoAlojamiento() {
        return housingType;
    }

    public void setTipoAlojamiento(HousingType housingType) {
        this.housingType = housingType;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%d,%s", codAlojamiento, nombreAlojamiento, direccionAlojamiento, numeHuespedes, housingType);
    }
}
