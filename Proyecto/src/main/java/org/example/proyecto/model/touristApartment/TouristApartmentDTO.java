package org.example.proyecto.model.touristApartment;

import org.example.proyecto.model.housing.HousingType;
import org.example.proyecto.model.housing.HousingDTO;

public class TouristApartmentDTO extends HousingDTO {
    private float distanciaCentros;

    public TouristApartmentDTO(String codAlojamiento, String nombreAlojamiento, String direccionAlojamiento, int numeHuespedes, float distanciaCentros) {
        super(codAlojamiento, nombreAlojamiento, direccionAlojamiento, numeHuespedes, HousingType.APARTAMENTOS_TURISTICOS);
        this.distanciaCentros = distanciaCentros;
    }
}
