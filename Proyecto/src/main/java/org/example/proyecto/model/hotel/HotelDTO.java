package org.example.proyecto.model.hotel;

import org.example.proyecto.model.housing.HousingDTO;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) for hotel information.
 * This class extends the HousingDTO class and represents hotel-specific data, including hotel classification, room type, and host number.
 *
 * @version 1.0
 * @since 2024-05-28
 * @Author Omar
 */
public class HotelDTO extends HousingDTO {
    /*attributes*/
    private int hotelClassification;
    private RoomType roomType;
    private int hostNumber;

    /**
     * Constructs a HotelDTO object with the specified details.
     *
     * @param id_alojamiento       The ID of the hotel.
     * @param nombre               The name of the hotel.
     * @param calle                The address of the hotel.
     * @param hotelClassification  The classification rating of the hotel.
     * @param roomType             The type of room available in the hotel.
     * @param hostNumber           The number of guests the hotel can accommodate.
     */
    public HotelDTO(int id_alojamiento, String nombre, String calle, int hotelClassification, RoomType roomType, int hostNumber) {
        super(id_alojamiento, nombre, calle);
        this.hotelClassification = hotelClassification;
        this.roomType = roomType;
        this.hostNumber = hostNumber;
    }

    /**
     * Constructs a HotelDTO object with the specified details.
     *
     * @param hotelName            The name of the hotel.
     * @param hotelAddress         The address of the hotel.
     * @param hotelClassification  The classification rating of the hotel.
     * @param roomType             The type of room available in the hotel.
     * @param hostNumber           The number of guests the hotel can accommodate.
     */
    public HotelDTO(String hotelName, String hotelAddress, Integer hotelClassification, RoomType roomType, Integer hostNumber) {
        super(hotelName, hotelAddress);
        this.hotelClassification = hotelClassification;
        this.roomType = roomType;
        this.hostNumber = hostNumber;
    }

    /**
     * Copy constructor for HotelDTO.
     *
     * @param hotelToCopy The HotelDTO object to be copied.
     */
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
     * Returns a string representation of the hotel.
     * The string contains the ID, name, address, classification, room type, and host number, separated by commas.
     *
     * @return the string representation of the hotel.
     */
    @Override
    public String toString() {
        return String.format("%d,%s,%s,%d,%s,%d", super.getHousingId(), super.getNombre(), super.getCalle(), hotelClassification, roomType, hostNumber);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * This method compares the current object with the specified object and returns true if they are equal, false otherwise.
     *
     * @param o The object to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HotelDTO hotelDTO)) return false;
        if (!super.equals(o)) return false;
        return hotelClassification == hotelDTO.hotelClassification && hostNumber == hotelDTO.hostNumber && roomType == hotelDTO.roomType;
    }

    /**
     * Returns a hash code value for the object.
     * This method returns an integer hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hotelClassification, roomType, hostNumber);
    }

}
