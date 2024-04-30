package org.example.proyecto.model.hotel;

import org.example.proyecto.model.hotel_aps.HousingType;
import org.example.proyecto.model.housing.HousingDTO;

public class HotelDTO extends HousingDTO {
    private String tipoHabitacion;
    private int clasificacion;

    public HotelDTO(String codAlojamiento, String nombreAlojamiento, String direccionAlojamiento, int numeHuespedes, String tipoHabitacion, int clasificacion) {
        super(codAlojamiento, nombreAlojamiento, direccionAlojamiento, numeHuespedes, HousingType.HOTELES);
        this.tipoHabitacion = tipoHabitacion;
        this.clasificacion = clasificacion;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public int getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    @Override
    public String toString() {
        return String.format("%s,%s %s", super.toString(), tipoHabitacion, clasificacion);
    }
}
