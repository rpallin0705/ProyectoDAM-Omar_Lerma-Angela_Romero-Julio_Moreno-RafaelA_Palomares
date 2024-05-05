package org.example.proyecto.model.hotel;

import org.example.proyecto.model.housing.HousingDTO;

public class HotelDTO extends HousingDTO {
<<<<<<< HEAD
    private String roomType;
    private int classification;

    public HotelDTO(String codAlojamiento, String nombreAlojamiento, String direccionAlojamiento, int numeHuespedes, String roomType, int classification) {
        super(codAlojamiento, nombreAlojamiento, direccionAlojamiento, numeHuespedes, HousingType.HOTELS);
        this.roomType = roomType;
        this.classification = classification;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
=======
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
>>>>>>> a3722196b5bd945b50e0c8b9c391e23138175651
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
<<<<<<< HEAD
        return String.format("%s,%s %s", super.toString(), roomType, classification);
=======
        return String.format("%d,%s,%s,%d,%s,%d",super.getId_alojamiento(),super.getNombre(),super.getCalle(),clasificacion,tipo_habitacion,numero_huespedes);
>>>>>>> a3722196b5bd945b50e0c8b9c391e23138175651
    }
}
