package org.example.proyecto.model.housing;

import org.example.proyecto.model.hotel_aps.Hotel_apsDTO;
import org.example.proyecto.model.hotel_aps.HousingType;

public class HousingDTO extends Hotel_apsDTO {
    private int id_alojamiento;

    public HousingDTO(int id, String nombre, String direccion, int clasificacion) {
        super(id, nombre, direccion, null, clasificacion);
    }

    public int getId_alojamiento() {
        return id_alojamiento;
    }

    @Override
    public String toString() {
        return String.format("%d,%d,%s,%s,%s",id_alojamiento,super.getId(), super.getNombre(), super.getDireccion(), super.getTipo());
    }
}
