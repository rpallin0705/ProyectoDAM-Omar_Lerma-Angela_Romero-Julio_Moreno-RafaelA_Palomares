package org.example.proyecto.model.touristApartment;

import org.example.proyecto.model.housing.HousingDTO;

public class TouristApartmentDTO extends HousingDTO {
<<<<<<< HEAD
    private float downtownDistance;

    public TouristApartmentDTO(String codAlojamiento, String nombreAlojamiento, String direccionAlojamiento, int numeHuespedes, float downtownDistance) {
        super(codAlojamiento, nombreAlojamiento, direccionAlojamiento, numeHuespedes, HousingType.TOURIST_APARTMENT);
        this.downtownDistance = downtownDistance;
=======
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
>>>>>>> a3722196b5bd945b50e0c8b9c391e23138175651
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
        return String.format("%d,%s,%s,%s",super.getHousingId(),super.getNombre(),super.getCalle(),distanciaCentros);
    }
}
