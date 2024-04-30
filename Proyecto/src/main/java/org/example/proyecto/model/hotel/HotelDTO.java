package org.example.proyecto.model.hotel;

import org.example.proyecto.model.housing.HousingDTO;

import java.util.Objects;

public class HotelDTO extends HousingDTO {
    private int clasificacion;
    private TipoHabitacion tipo_habitacion;
    private int numero_huespedes;

    public HotelDTO(int id_alojamiento, String nombre, String calle, int clasificacion, TipoHabitacion tipo_habitacion, int numero_huespedes) {
        super(id_alojamiento, nombre, calle);
        this.clasificacion = clasificacion;
        this.tipo_habitacion = tipo_habitacion;
        this.numero_huespedes = numero_huespedes;
    }

    public int getClasificacion() {
        return clasificacion;
    }

    public int getNumero_huespedes() {
        return numero_huespedes;
    }
    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    public TipoHabitacion getTipo_habitacion() {
        return tipo_habitacion;
    }

    public void setNumero_huespedes(int numero_huespedes) {
        this.numero_huespedes = numero_huespedes;
    }

}
