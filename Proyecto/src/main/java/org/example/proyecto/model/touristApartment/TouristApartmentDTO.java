package org.example.proyecto.model.touristApartment;

import org.example.proyecto.model.housing.HousingDTO;

import java.util.Objects;

public class TouristApartmentDTO extends HousingDTO {

    private float downtownDistance;

    /*attribute*/
    /**
     * TouristApartmentDTO builder
     * @param housingId id
     * @param nombre name
     * @param calle address
     * @param downtTownDistance distance to the downtown
     */
    public TouristApartmentDTO(int housingId, String nombre, String calle, float downtTownDistance) {
        super(housingId, nombre, calle);
        this.downtownDistance = downtTownDistance;
    }

    public TouristApartmentDTO(String nombre, String calle, float dowTownDistance) {
        super(nombre, calle);
        this.downtownDistance = dowTownDistance;
    }

    /*getter*/
    public float getDowntownDistance() {
        return downtownDistance;
    }

    /**
     * to string
     * @return String with the attributes of a tourist apartment in CSV format
     */
    @Override
    public String toString() {
        return String.format("%d,%s,%s,%f",super.getHousingId(),super.getNombre(),super.getCalle(), downtownDistance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TouristApartmentDTO that)) return false;
        if (!super.equals(o)) return false;
        return Float.compare(downtownDistance, that.downtownDistance) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), downtownDistance);
    }
}
