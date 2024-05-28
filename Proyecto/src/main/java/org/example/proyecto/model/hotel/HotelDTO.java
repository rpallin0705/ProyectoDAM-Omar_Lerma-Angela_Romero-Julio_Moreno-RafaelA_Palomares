package org.example.proyecto.model.hotel;

import org.example.proyecto.model.housing.HousingDTO;

import java.util.Objects;

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

    public HotelDTO(HotelDTO hotelToCopy) {
        super(hotelToCopy.getHousingId(), hotelToCopy.getNombre(), hotelToCopy.getCalle());
        this.hotelClassification = hotelToCopy.hotelClassification;
        this.roomType = hotelToCopy.roomType;
        this.hostNumber = hotelToCopy.getHostNumber();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HotelDTO hotelDTO)) return false;
        if (!super.equals(o)) return false;
        return hotelClassification == hotelDTO.hotelClassification && hostNumber == hotelDTO.hostNumber && roomType == hotelDTO.roomType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hotelClassification, roomType, hostNumber);
    }
}
