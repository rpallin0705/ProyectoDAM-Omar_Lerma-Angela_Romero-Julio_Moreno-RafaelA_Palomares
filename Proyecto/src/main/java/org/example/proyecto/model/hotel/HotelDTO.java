package org.example.proyecto.model.hotel;

import org.example.proyecto.model.housing.HousingDTO;

public class HotelDTO extends HousingDTO {
    /*attributes*/
    private int clasificacion;
    private TipoHabitacion tipo_habitacion;
    private int numero_huespedes;

    /**
     * HotelDTO builder
     * @param id_alojamiento id
     * @param nombre name
     * @param calle address
     * @param clasificacion rating
     * @param tipo_habitacion room type
     * @param numero_huespedes guests number
     */
    public HotelDTO(int id_alojamiento, String nombre, String calle, int clasificacion, TipoHabitacion tipo_habitacion, int numero_huespedes) {
        super(id_alojamiento, nombre, calle);
        this.clasificacion = clasificacion;
        this.tipo_habitacion = tipo_habitacion;
        this.numero_huespedes = numero_huespedes;
    }

    /*getters*/
    public int getClasificacion() {
        return clasificacion;
    }

    public int getNumero_huespedes() {
        return numero_huespedes;
    }
    public TipoHabitacion getTipo_habitacion() {
        return tipo_habitacion;
    }
    /*setters*/
    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }
    public void setNumero_huespedes(int numero_huespedes) {
        this.numero_huespedes = numero_huespedes;
    }

    /**
     * to string
     * @return String with the attributes of a hotel in CSV format
     */
    @Override
    public String toString() {
        return String.format("%d,%s,%s,%d,%s,%d",super.getId_alojamiento(),super.getNombre(),super.getCalle(),clasificacion,tipo_habitacion,numero_huespedes);
    }
}
