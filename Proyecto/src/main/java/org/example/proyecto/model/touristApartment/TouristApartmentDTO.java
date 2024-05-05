package org.example.proyecto.model.touristApartment;

import org.example.proyecto.model.hotel_aps.HousingType;
import org.example.proyecto.model.housing.HousingDTO;

public class TouristApartmentDTO extends HousingDTO {
    private float downtownDistance;

    public TouristApartmentDTO(String codAlojamiento, String nombreAlojamiento, String direccionAlojamiento, int numeHuespedes, float downtownDistance) {
        super(codAlojamiento, nombreAlojamiento, direccionAlojamiento, numeHuespedes, HousingType.TOURIST_APARTMENT);
        this.downtownDistance = downtownDistance;
    }
}
