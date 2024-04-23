package org.example.proyecto.model.housing;

public class HousingDTO {
    private String codAlojamiento;
    private String nombreAlojamiento;
    private String direccionAlojamiento;
    private int numHuespedes;
    private HousingType housingType;

    public HousingDTO(String codAlojamiento, String nombreAlojamiento, String direccionAlojamiento, int numHuespedes, HousingType housingType) {
        this.codAlojamiento = codAlojamiento;
        this.nombreAlojamiento = nombreAlojamiento;
        this.direccionAlojamiento = direccionAlojamiento;
        this.numHuespedes = numHuespedes;
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

    public int getNumHuespedes() {
        return numHuespedes;
    }

    public void setNumHuespedes(int numHuespedes) {
        this.numHuespedes = numHuespedes;
    }

    public HousingType getTipoAlojamiento() {
        return housingType;
    }

    public void setTipoAlojamiento(HousingType housingType) {
        this.housingType = housingType;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%d,%s", codAlojamiento, nombreAlojamiento, direccionAlojamiento, numHuespedes, housingType);
    }
}
