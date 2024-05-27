package org.example.proyecto.model.touristApartment;

import org.example.proyecto.model.housing.HousingDTO;

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
    public TouristApartmentDTO(int housingId, String nombre, String calle, int downtTownDistance) {
        super(housingId, nombre, calle);
        this.downtownDistance = downtTownDistance;
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
}
