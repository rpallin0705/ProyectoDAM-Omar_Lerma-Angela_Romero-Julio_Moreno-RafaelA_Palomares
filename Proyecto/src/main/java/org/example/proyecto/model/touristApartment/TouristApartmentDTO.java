package org.example.proyecto.model.touristApartment;

import org.example.proyecto.model.housing.HousingDTO;

public class TouristApartmentDTO extends HousingDTO {
    /*attribute*/
    private int distanciaCentros;
    /**
     * TouristApartmentDTO builder
     * @param id_alojamiento id
     * @param nombre name
     * @param calle address
     * @param distanciaCentros distance to the downtown
     */
    public TouristApartmentDTO(int id_alojamiento, String nombre, String calle, int distanciaCentros) {
        super(id_alojamiento, nombre, calle);
        this.distanciaCentros = distanciaCentros;
    }

    /*getter*/
    public int getDistanciaCentros() {
        return distanciaCentros;
    }

    /**
     * to string
     * @return String with the attributes of a tourist apartment in CSV format
     */
    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s",super.getId_alojamiento(),super.getNombre(),super.getCalle(),distanciaCentros);
    }
}
