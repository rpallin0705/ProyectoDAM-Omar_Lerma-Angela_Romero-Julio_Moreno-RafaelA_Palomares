package org.example.proyecto.model.hotel;

import org.example.proyecto.model.hotel_aps.HousingType;
import org.example.proyecto.model.housing.HousingDTO;

public class HotelDTO extends HousingDTO {
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
    }

    @Override
    public String toString() {
        return String.format("%s,%s %s", super.toString(), roomType, classification);
    }
}
