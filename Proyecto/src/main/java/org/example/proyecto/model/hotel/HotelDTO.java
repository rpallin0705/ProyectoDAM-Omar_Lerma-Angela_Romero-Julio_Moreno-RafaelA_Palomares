package org.example.proyecto.model.hotel;

import org.example.proyecto.model.housing.HousingDTO;

public class HotelDTO extends HousingDTO {
    /*attributes*/
    private int hotelClassification;
    private RoomType roomType;
    private int hostNumber;

    /**
     * HotelDTO builder
     * @param id_alojamiento id
     * @param nombre name
     * @param calle address
     * @param hotelClassification rating
     * @param roomType room type
     * @param hostNumber guests number
     */
    public HotelDTO(int id_alojamiento, String nombre, String calle, int hotelClassification, RoomType roomType, int hostNumber) {
        super(id_alojamiento, nombre, calle);
        this.hotelClassification = hotelClassification;
        this.roomType = roomType;
        this.hostNumber = hostNumber;
    }

    public HotelDTO(String hotelName, String hotelAddress, Integer hotelClassification, RoomType roomType, Integer hostNumber) {
        super(hotelName, hotelAddress);
        this.hotelClassification = hotelClassification;
        this.roomType = roomType;
        this.hostNumber = hostNumber;
    }


    /*getters*/
    public int getHotelClassification() {
        return hotelClassification;
    }

    public int getHostNumber() {
        return hostNumber;
    }
    public RoomType getRoomType() {
        return roomType;
    }
    /*setters*/
    public void setHotelClassification(int hotelClassification) {
        this.hotelClassification = hotelClassification;

    }
    public void setHostNumber(int hostNumber) {
        this.hostNumber = hostNumber;
    }

    /**
     * to string
     * @return String with the attributes of a hotel in CSV format
     */
    @Override
    public String toString() {
        return String.format("%d,%s,%s,%d,%s,%d",super.getHousingId(),super.getNombre(),super.getCalle(), hotelClassification, roomType, hostNumber);
    }
}
