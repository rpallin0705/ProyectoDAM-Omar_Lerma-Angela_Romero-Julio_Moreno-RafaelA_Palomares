package org.example.proyecto.model.touristApartment;

import org.example.proyecto.model.hotel.HotelDTO;
import org.example.proyecto.model.hotel.RoomType;
import org.example.proyecto.model.housing.HousingDTO;

/**
 * Represents a tourist apartment, extending the HousingDTO class.
 *
 * @version 1.0
 * @since 2024-05-28
 *
 * @author Omar
 */
public class TouristApartmentDTO extends HousingDTO {

    private float downtownDistance;

    /**
     * Constructs a new TouristApartmentDTO object with the specified parameters.
     *
     * @param housingId        The ID of the housing.
     * @param nombre           The name of the tourist apartment.
     * @param calle            The address of the tourist apartment.
     * @param downtownDistance The distance to the downtown for the tourist apartment.
     */
    public TouristApartmentDTO(int housingId, String nombre, String calle, float downtownDistance) {
        super(housingId, nombre, calle);
        this.downtownDistance = downtownDistance;
    }
    /**
     * Constructs a TouristApartmentDTO object with the specified details.
     *
     * @param nombre           The name of the tourist apartment.
     * @param calle            The address of the tourist apartment.
     * @param downtownDistance The distance to the downtown for the tourist apartment.
     */
    public TouristApartmentDTO(String nombre, String calle, float downtownDistance) {
        super(nombre, calle);
        this.downtownDistance = downtownDistance;
    }

    public TouristApartmentDTO(String nombre, String calle, float downtownDistance) {
        super(nombre, calle);
        this.downtownDistance = downtownDistance;
    }

    /**
     * Copy constructor for HotelDTO.
     *
     * @param touristApartmentToCopy The TouristApartmentDTO object to be copied.
     */
    public TouristApartmentDTO(TouristApartmentDTO touristApartmentToCopy) {
        super(touristApartmentToCopy.getHousingId(), touristApartmentToCopy.getNombre(), touristApartmentToCopy.getCalle());
        this.downtownDistance = touristApartmentToCopy.getDowntownDistance();
    }
    /**
     * Gets the distance to the downtown for the tourist apartment.
     *
     * @return The downtown distance.
     */
    public float getDowntownDistance() {
        return downtownDistance;
    }

    /**
     * Returns a string representation of the TouristApartmentDTO object.
     *
     * @return A string containing the attributes of the tourist apartment in CSV format.
     */
    @Override
    public String toString() {
        return String.format("%d,%s,%s,%f", super.getHousingId(), super.getNombre(), super.getCalle(), downtownDistance);
    }
}
