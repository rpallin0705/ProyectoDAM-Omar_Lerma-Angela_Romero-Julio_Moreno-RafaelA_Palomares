package org.example.proyecto.model.touristApartment;

import org.example.proyecto.model.housing.HousingDTO;

public class TouristApartmentDTO extends HousingDTO {
    private int distanciaCentros;

    public TouristApartmentDTO(int id_alojamiento, String nombre, String calle, int distanciaCentros) {
        super(id_alojamiento, nombre, calle);
        this.distanciaCentros = distanciaCentros;
    }

    public int getDistanciaCentros() {
        return distanciaCentros;
    }
}
