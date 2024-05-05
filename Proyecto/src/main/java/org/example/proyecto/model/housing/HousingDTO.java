package org.example.proyecto.model.housing;


public class HousingDTO extends Hotel_apsDTO {
    private int HousingId;

    public HousingDTO(int id, String nombre, String direccion, int clasificacion) {
        super(id, nombre, direccion, null, clasificacion);
    }

    public int getId_alojamiento() {
        return HousingId;
    }

    @Override
    public String toString() {
        return String.format("%d,%d,%s,%s,%s",HousingId,super.getId(), super.getNombre(), super.getDireccion(), super.getTipo());
    }
}
