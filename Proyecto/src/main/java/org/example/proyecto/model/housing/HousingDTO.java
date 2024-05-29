package org.example.proyecto.model.housing;

import java.util.Objects;

/**
 * Class representing housing information.
 * This class encapsulates the data of a housing and provides methods for accessing and manipulating this data.
 *
 * @version 1.0
 * @since 2024-05-28
 *
 * @Author Omar
 */
public class HousingDTO {
    /* Attributes */
    private int housingId;
    private String nombre;
    private String calle;

    /**
     * Constructs a new HousingDTO object with the specified details.
     *
     * @param housingId the unique identifier of the housing.
     * @param nombre    the name of the housing.
     * @param calle     the address of the housing.
     */
    public HousingDTO(int housingId, String nombre, String calle) {
        this.housingId = housingId;
        this.nombre = nombre;
        this.calle = calle;
    }

    /**
     * Constructs a new HousingDTO object with the specified name and address.
     *
     * @param housingName    the name of the housing.
     * @param housingAddress the address of the housing.
     */
    public HousingDTO(String housingName, String housingAddress) {
        this.nombre = housingName;
        this.calle = housingAddress;
    }

    /* Getters */

    /**
     * Gets the ID of the housing.
     *
     * @return the housing ID.
     */
    public int getHousingId() {
        return housingId;
    }

    /**
     * Gets the name of the housing.
     *
     * @return the housing name.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Gets the address of the housing.
     *
     * @return the housing address.
     */
    public String getCalle() {
        return calle;
    }

    /* Setters */

    /**
     * Sets the name of the housing.
     *
     * @param nombre the new housing name.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return true if this housing ID is the same as the housing ID of the specified object; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HousingDTO that = (HousingDTO) o;
        return housingId == that.housingId;
    }

    /**
     * Returns a hash code value for the housing.
     *
     * @return a hash code value for the housing.
     */
    @Override
    public int hashCode() {
        return Objects.hash(housingId);
    }
}
