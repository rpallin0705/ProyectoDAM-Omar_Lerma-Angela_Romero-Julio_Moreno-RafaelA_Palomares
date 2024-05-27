package org.example.proyecto.model.housing;

import java.util.Objects;

public class HousingDTO{
    /*attributes*/
    private int housingId;
    private String nombre;
    private String calle;

    /**
     * HousingDTO builder
     * @param housingId id
     * @param nombre name
     * @param calle address
     */
    public HousingDTO(int housingId, String nombre, String calle) {
        this.housingId = housingId;
        this.nombre = nombre;
        this.calle = calle;
    }

    public HousingDTO(String housingName, String housingAddress) {
        this.nombre = housingName;
        this.calle = housingAddress;
    }

    /*getters*/
    public int getHousingId() {
        return housingId;
    }

    public String getNombre() {
        return nombre;

    }

    public String getCalle() {
        return calle;
    }

    /*setters*/
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * equals
     * @param o -> housing id
     * @return true if both of the housing compared have the same id
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HousingDTO that = (HousingDTO) o;
        return housingId == that.housingId;
    }

    /**
     * hashCode
     * @return hashCode associated with the id
     */
    @Override
    public int hashCode() {
        return Objects.hash(housingId);
    }

}
